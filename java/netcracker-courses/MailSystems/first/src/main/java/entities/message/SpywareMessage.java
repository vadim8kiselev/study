package entities.message;

/**
 * Created by mark on 09.11.15.
 */
public class SpywareMessage extends Message {

    public SpywareMessage(String message) {
        super(message);
    }

    public boolean isSpywareMessage() {
        return true;
    }

}
