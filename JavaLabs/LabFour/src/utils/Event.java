package utils;

public class Event {
    protected Object source;

    public Event(Object src) {
        source = src;
    }

    public Object getSource() {
        return source;
    }
}
