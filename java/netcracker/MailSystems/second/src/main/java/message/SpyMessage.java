package message;

/**
 * Created by mark on 18.11.15.
 */
public class SpyMessage extends Message {

    public SpyMessage(String message) {
        super(message);
    }

    public boolean isSpywareMessage() {
        return true;
    }
}
