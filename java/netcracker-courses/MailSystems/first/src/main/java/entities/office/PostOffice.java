package entities.office;

import entities.message.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mark on 09.11.15.
 */
public abstract class PostOffice {

    abstract public void receiveMessage(Message message);

    protected List<Message> listOfMessages = new ArrayList<>();

    protected List<PostOffice> childrens = new ArrayList<>();

    public static PostOffice addNormalPostOffice() {
        return new NormalPostOffice();
    }

    public static PostOffice addSpywarePostOffice() {
        return new SpywarePostOffice();
    }

    public void addChild(PostOffice office) throws NullPointerException {

        if (office == null) {
            throw new NullPointerException();
        }
        childrens.add(office);
    }

    public List<Message> getListOfMessages() {
        return listOfMessages;
    }
}
