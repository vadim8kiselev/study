package entities.office;

import entities.message.Message;
import systems.SpywareData;

/**
 * Created by mark on 09.11.15.
 */
public class SpywarePostOffice extends PostOffice {

    public void receiveMessage(Message message) throws NullPointerException {

        if (message == null) {
            throw new NullPointerException();
        }

        if (message.isSpywareMessage()) {
            SpywareData.addMessage(message);
        } else {
            listOfMessages.add(message);

            for (PostOffice office : childrens) {
                office.receiveMessage(message);
            }
        }

    }
}
