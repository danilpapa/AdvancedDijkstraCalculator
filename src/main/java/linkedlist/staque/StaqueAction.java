package linkedlist.staque;

public class StaqueAction<T> {
    public enum ActionType {
        ADD,        // add a value
        GET,        // get/take the value
        EXTRACT     // get the value and remove it from the collection
    }

    public ActionType type;
    public T value;

    public StaqueAction(ActionType actionType) {
        this.type = actionType;
        this.value = null;
    }

    public StaqueAction(ActionType actionType, T value) {
        this.type = actionType;
        this.value = value;
    }

    public static <T> StaqueAction<T> add(T value) {
        return new StaqueAction<T>(ActionType.ADD, value);
    }

    public static <T> StaqueAction<T> get() {
        return new StaqueAction<T>(ActionType.GET);
    }

    public static <T> StaqueAction<T> extract() {
        return new StaqueAction<T>(ActionType.EXTRACT);
    }
}
