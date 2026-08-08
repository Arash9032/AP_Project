package controller.eventbus;

public interface EventListener <T extends GameEvent> {
    void onEvent(T event);
}
