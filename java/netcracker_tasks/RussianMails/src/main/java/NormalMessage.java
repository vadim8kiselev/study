/**
 * Created by mark on 09.11.15.
 */
public class NormalMessage extends Message {

    public NormalMessage(String message) {
        super(message);
    }

    public boolean isSpywareMessage() {
        return false;
    }
}
