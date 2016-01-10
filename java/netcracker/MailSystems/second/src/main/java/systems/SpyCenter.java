package systems;

import message.Message;
import staff.outlawstaff.Messenger;
import staff.outlawstaff.Spy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mark on 17.11.15.
 */
public class SpyCenter {

    private static final SpyCenter center = new SpyCenter();

    private List<Spy> spies = new ArrayList<>();

    private List<Messenger> messengers = new ArrayList<>();

    private List<Message> spyData = new ArrayList<>();

    private SpyCenter() {

    }

    public static SpyCenter getInstance() {
        return center;
    }

    public void registerSpy(int jobId, int messengerId) {
        spies.add(new Spy(jobId, messengerId));
    }

    public void registerSpy(int jobId) {
        spies.add(new Spy(jobId));
    }

    public void registerMessenger(int messengerId) {
        messengers.add(new Messenger(messengerId));
    }

    public List<Spy> getSpies() {
        return spies;
    }

    public List<Messenger> getMessengers() {
        return messengers;
    }

    public Spy getSpy(int id) {
        if (id < 0 || id > spies.size() - 1)
            return null;
        return spies.get(id);
    }

    public Messenger getMessenger(int id) {
        if (id < 0 || id > messengers.size() - 1)
            return null;
        return messengers.get(id);
    }

    public int getCountOfSpies() {
        return spies.size();
    }

    public int getCountOfMessengers() {
        return messengers.size();
    }

    public void setConnection(int spyId, int messengerId) {
        if (spyId < 0 || spyId > spies.size() - 1 ||
                messengerId < 0 || messengerId > messengers.size() - 1)
            return;
        getSpy(spyId).setMessenger(messengerId);
    }

    public void addMessage(Message message) {
        if (message == null)
            return;
        spyData.add(message);
    }

    public List<Message> getSpyData() {
        return spyData;
    }
}
