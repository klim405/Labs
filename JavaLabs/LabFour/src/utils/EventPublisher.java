package utils;


import java.util.ArrayList;

public abstract class EventPublisher {
    private final ArrayList<EventListener> eventListeners = new ArrayList<>();

    public final void publishEvent(Event e) {
        for (EventListener listener : eventListeners) {
            listener.actionPerformed(e);
        }
    }

    public final void addEventListener(EventListener eventListener) {
        eventListeners.add(eventListener);
    }

    public final void removeEventListener(EventListener eventListener) {
        eventListeners.remove(eventListener);
    }
}
