package main;

import interfaces.Office;
import interfaces.Receivable;
import interfaces.Staff;
import message.Message;
import staff.Postman;
import systems.Grid;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mark on 17.11.15.
 */
public class PostOffice implements Office {

    private List<Office> connections = new ArrayList<>();

    private List<Staff> staff = new ArrayList<>();

    private List<Receivable> postmans = new ArrayList<>();

    public static Receivable registerPostman(int id) throws ArrayIndexOutOfBoundsException {
        if (id < 0 || id > Grid.getInstance().getCountOfOffices() - 1)
            throw new ArrayIndexOutOfBoundsException();
        return new Postman(id);
    }

    @Override
    public void setConnection(int id) throws ArrayIndexOutOfBoundsException {
        Grid grid = Grid.getInstance();
        if (id < 0 || id > grid.getCountOfOffices() - 1)
            throw new ArrayIndexOutOfBoundsException();
        connections.add(grid.getOffice(id));
    }

    @Override
    public List<Office> getConnections() {
        return connections;
    }

    @Deprecated
    public void addEmployee(Staff employee) throws NullPointerException {
        if (employee == null)
            throw new NullPointerException();
        staff.add(employee);
    }

    @Deprecated
    public List<Staff> getStaff() {
        return staff;
    }

    @Override
    public void addPostman(Receivable postman) throws NullPointerException {
        if (postman == null)
            throw new NullPointerException();
        postmans.add(postman);
    }

    @Override
    public List<Receivable> getPostmans() {
        return postmans;
    }

    @Override
    public void receiveMessage(Message message) throws NullPointerException {
        if (message == null)
            throw new NullPointerException();

        for (Receivable postman : postmans)
            postman.receiveMessage(message, connections);
    }
}
