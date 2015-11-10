/**
 * Created by mark on 09.11.15.
 */
public abstract class Message {

    private String message;

    public Message(String message) {
        this.message = message;
    }

    public static NormalMessage createNormalMessage(String message) throws NullPointerException {

        if (message == null)
            throw new NullPointerException();
        return new NormalMessage(message);
    }

    public static SpywareMessage createSpywareMessage(String message) throws NullPointerException {
        if (message == null)
            throw new NullPointerException();
        return new SpywareMessage(message);
    }

    public String getMessage() {
        return message;
    }

    public abstract boolean isSpywareMessage();
}
