package main;

import message.Message;
import systems.Grid;
import systems.SpyCenter;

/**
 * Created by mark on 17.11.15.
 */
public class Main {
    static Grid grid = Grid.getInstance();

    public static void main(String[] args) {

        addOffices(2);

        boolean connection = true;
        if (connection) {
            binTreeConnection();
        } else {
            handleConnection();
        }

        setStaff();

        try {
            grid.getOffice(0).receiveMessage(Message.createNormalMessage("Hello"));
            grid.getOffice(0).receiveMessage(Message.createSpyMessage("Spy:Hello"));
        } catch (Exception error) {
            System.out.println(error);
        }

        for (Message item : SpyCenter.getInstance().getSpyData())
            System.out.println(item.getMessage());
    }

    static void addOffices(int count) {
        for (int i = 0; i < count; i++)
            grid.addOffice();
    }

    static void binTreeConnection() {
        for (int i = 0; i <= grid.getCountOfOffices() / 2; i++) {
            try {
                grid.connectOffices(i, i * 2 + 1);
                grid.connectOffices(i, i * 2 + 2);
            } catch (Exception error) {
                continue;
            }
        }
    }

    static void handleConnection() {
        try {
            grid.connectOffices(0, 1);
            grid.connectOffices(1, 2);
            grid.connectOffices(2, 3);
            grid.connectOffices(3, 4);
            grid.connectOffices(4, 5);
        } catch (Exception error) {
            System.err.println(error);
        }
    }

    static void setStaff() {
        SpyCenter spyCenter = SpyCenter.getInstance();
        try {
            for (int index = 0; index < grid.getCountOfOffices(); index++) {
                grid.getOffice(index).addPostman(PostOffice.registerPostman(index));
                grid.getOffice(index).addPostman(PostOffice.registerPostman(index));
                grid.getOffice(index).addPostman(PostOffice.registerPostman(index));
                spyCenter.registerMessenger(index);
                grid.getOffice(index).addEmployee(spyCenter.getMessenger(index));
                spyCenter.registerSpy(index, index);
                grid.getOffice(index).addPostman(spyCenter.getSpy(index));
                spyCenter.registerSpy(index, index);
                grid.getOffice(index).addPostman(spyCenter.getSpy(index));
            }
        } catch (Exception error) {
            System.err.println(error);
        }
    }
}
