package main;

import entities.message.Message;
import entities.office.PostOffice;
import systems.Grid;
import systems.SpywareData;

/**
 * Created by mark on 09.11.15.
 */
public class Main {

    static Grid grid = Grid.getInstance();

    public static void main(String[] args) {

        addOffices();

        boolean choiseOfLinking = true;
        if (choiseOfLinking) {

            buildBinaryTree();
        } else {

            buildTree();
        }

        sendMessages();


        System.out.println("List of normal data:");
        printOfficesListsOfMessages();


        System.out.println("List of spyware data:");
        printSpywareDataMessages();
    }


    public static void addOffices() {
        grid.addPostOffice(PostOffice.addNormalPostOffice());
        grid.addPostOffice(PostOffice.addSpywarePostOffice());
        grid.addPostOffice(PostOffice.addNormalPostOffice());
        grid.addPostOffice(PostOffice.addSpywarePostOffice());
        // ...
        grid.addPostOffice(PostOffice.addNormalPostOffice());
        grid.addPostOffice(PostOffice.addSpywarePostOffice());
        grid.addPostOffice(PostOffice.addNormalPostOffice());
        grid.addPostOffice(PostOffice.addSpywarePostOffice());
    }

    static void buildBinaryTree() {
        for (int index = 0; index <= grid.getGridSize() / 2; index++) {
            try {
                grid.getPostOffice(index).addChild(grid.getPostOffice(index * 2 + 1));
                grid.getPostOffice(index).addChild(grid.getPostOffice(index * 2 + 2));
            } catch (ArrayIndexOutOfBoundsException error) {
            }
        }
    }

    static void buildTree() {
        try {
            grid.getPostOffice(0).addChild(grid.getPostOffice(1));
            grid.getPostOffice(1).addChild(grid.getPostOffice(2));
            grid.getPostOffice(2).addChild(grid.getPostOffice(3));
            grid.getPostOffice(3).addChild(grid.getPostOffice(4));
            grid.getPostOffice(4).addChild(grid.getPostOffice(5));
            grid.getPostOffice(5).addChild(grid.getPostOffice(6));
            grid.getPostOffice(6).addChild(grid.getPostOffice(7));
        } catch (ArrayIndexOutOfBoundsException error) {
        }
    }

    static void sendMessages() {
        try {
            grid.getPostOffice(0).receiveMessage(Message.createNormalMessage("NORMAL"));
            grid.getPostOffice(1).receiveMessage(Message.createNormalMessage("MESSAGE"));
            // ...
            grid.getPostOffice(1).receiveMessage(Message.createSpywareMessage("SPY_MESSAGE"));
        } catch (ArrayIndexOutOfBoundsException error) {
        }
    }

    static void printOfficesListsOfMessages() {
        for (int index = 0; index < grid.getGridSize(); index++) {
            System.out.print(index + 1 + ": ");
            for (Message message : grid.getPostOffice(index).getListOfMessages()) {
                System.out.print(message.getMessage() + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static void printSpywareDataMessages() {
        for (Message message : SpywareData.getSpywareData()) {
            System.out.println(message.getMessage());
        }
        System.out.println();
    }
}
