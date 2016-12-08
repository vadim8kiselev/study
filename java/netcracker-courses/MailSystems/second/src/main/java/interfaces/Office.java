package interfaces;

import message.Message;

import java.util.List;

/**
 * Created by mark on 17.11.15.
 */
public interface Office {

    void setConnection(int id);

    List<Office> getConnections();

    void addEmployee(Staff employee);

    List<Staff> getStaff();

    void addPostman(Receivable postman);

    List<Receivable> getPostmans();

    void receiveMessage(Message message);
}
