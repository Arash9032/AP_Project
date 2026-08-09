package controller.logic;

import controller.eventbus.EventBus;
import controller.eventbus.EventListener;
import controller.eventbus.GameEvent;
import model.GameState;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public abstract class EventListenerController {
    private GameState gameState;

    private final Map<Class<? extends GameEvent> , EventListener<? extends GameEvent>> listenerMap;

    public EventListenerController(GameState gameState) {
        this.gameState = gameState;
        listenerMap = new HashMap<>();
    }

    protected <T extends GameEvent> void subscribeEvent(Class<T> eventClass, EventListener<T> eventListener) {
        listenerMap.put(eventClass , eventListener);
        EventBus.getInstance().subscribe(eventClass, eventListener);
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public void destroy(){
        for(Map.Entry<Class<? extends GameEvent> , EventListener<? extends GameEvent>> entry : listenerMap.entrySet()){
            destroyHelper(entry.getKey(), entry.getValue());
        }
        listenerMap.clear();
    }

    @SuppressWarnings("unchecked")
    private <T extends GameEvent> void destroyHelper(Class<?> clazz , EventListener<?> eventListener){
        EventBus.getInstance().unsubscribe((Class<T>) clazz , (EventListener<T>) eventListener);
    }

    public Map<Class<? extends GameEvent>, EventListener<? extends GameEvent>> getListenerMap() {
        return Collections.unmodifiableMap(listenerMap);
    }
}
