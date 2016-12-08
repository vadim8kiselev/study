package staff.outlawstaff;

import interfaces.Office;
import interfaces.Receivable;
import interfaces.Staff;
import message.Message;
import systems.Grid;
import systems.SpyCenter;

import java.util.List;

/**
 * Created by mark on 18.11.15.
 */
public class Spy implements Receivable, Staff {

    private int jobId;

    private Messenger messenger;

    public Spy(int jobId) {
        if (jobId < 0 || jobId > Grid.getInstance().getCountOfOffices() - 1)
            return;
        this.jobId = jobId;
    }

    public Spy(int jobId, int messengerId) {
        SpyCenter spyCenter = SpyCenter.getInstance();
        if (jobId < 0 || jobId > Grid.getInstance().getCountOfOffices() - 1 ||
                messengerId < 0 || messengerId > spyCenter.getCountOfMessengers() - 1)
            return;
        this.jobId = jobId;
        this.messenger = spyCenter.getMessenger(messengerId);
    }

    public void setMessenger(int messengerId) {
        SpyCenter spyCenter = SpyCenter.getInstance();
        if (messengerId < 0 || messengerId > spyCenter.getCountOfMessengers() - 1)
            return;
        this.messenger = spyCenter.getMessenger(messengerId);
    }

    @Override
    public void receiveMessage(Message message, List<Office> targets) {
        if (message == null)
            return;

        for (Office office : targets) {
            office.receiveMessage(message);
        }
        if (message.isSpywareMessage() && messenger != null)
            messenger.receiveMessage(message);
    }
}
