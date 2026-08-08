package controller.eventbus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventBus {
    private static EventBus instance;

    private final Map<Class<? extends GameEvent> , List<EventListener<? extends GameEvent>>> listenersMap;

    private EventBus(){
        listenersMap = new HashMap<>();
    }

    public static EventBus getInstance(){
        if(instance == null)
            instance = new EventBus();
        return instance;
    }

    public <T extends GameEvent> void subscribe(Class<T> eventClass , EventListener<T> listener){
        listenersMap.computeIfAbsent(eventClass , k -> new ArrayList<>()).add(listener);
    }

    public <T extends GameEvent> void unsubscribe(Class<T> eventClass, EventListener<T> listener){
        if(!listenersMap.containsKey(eventClass)) return;
        listenersMap.get(eventClass).remove(listener);
    }

    @SuppressWarnings("unchecked")
    public <T extends GameEvent> void publish(T event){
        if(!listenersMap.containsKey(event.getClass())) return;
        List<EventListener<? extends GameEvent>> listeners = new ArrayList<>(listenersMap.get(event.getClass()));
        for(EventListener<? extends GameEvent> listener : listeners){
            ((EventListener<T>) listener).onEvent(event);
        }
    }

}
