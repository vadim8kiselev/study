package message;

/**
 * Created by mark on 18.11.15.
 */
public abstract class Message {

    private String message;

    public Message(String message) {
        this.message = message;
    }

    public static NormalMessage createNormalMessage(String message) {
        if (message == null)
            return null;
        return new NormalMessage(message);
    }

    public static SpyMessage createSpyMessage(String message) {
        if (message == null)
            return null;
        return new SpyMessage(message);
    }

    public String getMessage() {
        return message;
    }

    public abstract boolean isSpywareMessage();
}
