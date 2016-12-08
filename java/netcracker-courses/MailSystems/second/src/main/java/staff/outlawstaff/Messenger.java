package staff.outlawstaff;

import interfaces.Staff;
import message.Message;
import systems.Grid;
import systems.SpyCenter;

/**
 * Created by mark on 18.11.15.
 */
public class Messenger implements Staff {

    private int jobId;

    public Messenger(int jobId) {
        if (jobId < 0 || jobId > Grid.getInstance().getCountOfOffices() - 1)
            return;
        this.jobId = jobId;
    }

    public void receiveMessage(Message message) {
        if (message == null)
            return;
        SpyCenter.getInstance().addMessage(message);
    }
}
