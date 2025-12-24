package observer;

public class GameEvent {
    public enum EventType {
        SCORE_CHANGED,
        GAME_OVER,
        FOOD_EATEN,
        LEVEL_UP
    }

    private EventType type;
    private Object data;

    public GameEvent(EventType type, Object data) {
        this.type = type;
        this.data = data;
    }

    public EventType getType() { return type; }
    public Object getData() { return data; }
}