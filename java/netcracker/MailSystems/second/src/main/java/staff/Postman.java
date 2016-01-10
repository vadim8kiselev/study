package staff;

import interfaces.Office;
import interfaces.Receivable;
import interfaces.Staff;
import message.Message;
import systems.Grid;

import java.util.List;

/**
 * Created by mark on 18.11.15.
 */
public class Postman implements Receivable, Staff {

    private int jobId;

    public Postman(int jobId) {
        if (jobId < 0 || jobId > Grid.getInstance().getCountOfOffices() - 1)
            return;
        this.jobId = jobId;
    }

    @Override
    public void receiveMessage(Message message, List<Office> targets) {
        if (message == null)
            return;
        for (Office office : targets) {
            office.receiveMessage(message);
        }

    }
}
