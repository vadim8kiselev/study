package interfaces;

import message.Message;

import java.util.List;

/**
 * Created by mark on 18.11.15.
 */
public interface Receivable {

    void receiveMessage(Message message, List<Office> targets);
}
