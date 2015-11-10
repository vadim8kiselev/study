/**
 * Created by mark on 09.11.15.
 */
public class NormalPostOffice extends PostOffice {

    public void receiveMessage(Message message) throws NullPointerException {

        if (message == null) {
            throw new NullPointerException();
        }

        if (!message.isSpywareMessage()) {
            listOfMessages.add(message);
        }

        for (PostOffice office : childrens) {
            office.receiveMessage(message);
        }
    }

}
