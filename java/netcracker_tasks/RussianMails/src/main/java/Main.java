/**
 * Created by mark on 09.11.15.
 */
public class Main {

    public static void main(String[] args) {

        Grid grid = Grid.getInstance(); // Singleton controller

        /* initialization */
        grid.addPostOffice(PostOffice.addNormalPostOffice());
        grid.addPostOffice(PostOffice.addSpywarePostOffice());
        grid.addPostOffice(PostOffice.addSpywarePostOffice());
        grid.addPostOffice(PostOffice.addNormalPostOffice());
        grid.addPostOffice(PostOffice.addSpywarePostOffice());
        // ...
        grid.addPostOffice(PostOffice.addNormalPostOffice());
        grid.addPostOffice(PostOffice.addSpywarePostOffice());
        grid.addPostOffice(PostOffice.addNormalPostOffice());
        grid.addPostOffice(PostOffice.addSpywarePostOffice());



        /* building a binary tree */
        for (int index = 0; index <= grid.getGridSize() / 2; index++) {
            try {
                grid.getPostOffice(index).addChild(grid.getPostOffice(index * 2 + 1));
                grid.getPostOffice(index).addChild(grid.getPostOffice(index * 2 + 2));
            } catch (ArrayIndexOutOfBoundsException error) {
            }
        }


        /* sending messages */
        try {
            grid.getPostOffice(0).receiveMessage(Message.createNormalMessage("NORMAL"));
            grid.getPostOffice(1).receiveMessage(Message.createNormalMessage("MESSAGE"));
            // ...
            grid.getPostOffice(1).receiveMessage(Message.createSpywareMessage("SPY_MESSAGE"));
        } catch (ArrayIndexOutOfBoundsException error) {
        }

        /* print result */
        System.out.println("List of normal data:");
        for (int i = 0; i < grid.getGridSize(); i++) {
            System.out.print(i + 1 + ": ");
            for (Message message : grid.getPostOffice(i).getListOfMessages()) {
                System.out.print(message.getMessage() + " ");
            }
            System.out.println();
        }

        System.out.println();

        // print spyware data
        System.out.println("List of spyware data:");
        for (Message message : SpywareData.getSpywareData()) {
            System.out.println(message.getMessage());
        }
    }
}
