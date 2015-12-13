package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PlayerThread implements Runnable {

    private Socket clientSocket = null;

    public PlayerThread(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {

        try {
            DataInputStream in = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());


            Desk desk = null;
            out.writeUTF("I will go first? (yes/no)");
            String choise = in.readUTF();
            int winner = 0;

            if (choise.charAt(0) == 'y') {

                desk = new Desk(1);

                while (desk.hasFreePlaces()) {

                    desk.setRandomPosition();

                    if ((winner = desk.checkWinner()) == -1 || !desk.hasFreePlaces()) {
                        out.writeUTF("Game over");
                        out.flush();
                        break;
                    } else {
                        out.writeUTF("Continue");
                        out.flush();
                    }

                    while (true) {
                        out.writeUTF(desk.getState() + "Write two coordinates as \"x y\": ");
                        out.flush();
                        String request = in.readUTF();
                        String[] points = request.split(" ");
                        if (!desk.setPosition(Integer.parseInt(points[0]) - 1,
                                Integer.parseInt(points[1]) - 1)) {
                            out.writeUTF("Wrong position! Choose again");
                            out.flush();
                            continue;
                        } else {
                            out.writeUTF("Correct coordinates");
                            break;
                        }
                    }

                    if ((winner = desk.checkWinner()) == 1 || !desk.hasFreePlaces()) {
                        out.writeUTF("Game over");
                        out.flush();
                        break;
                    } else {
                        out.writeUTF("Continue");
                        out.flush();
                    }
                }
            } else if (choise.charAt(0) == 'n') {

                desk = new Desk(0);

                while (desk.hasFreePlaces()) {

                    out.writeUTF(desk.getState() + "Write two coordinates as \"x y\": ");
                    out.flush();
                    String request = in.readUTF();
                    String[] points = request.split(" ");
                    if (!desk.setPosition(Integer.parseInt(points[0]) - 1,
                            Integer.parseInt(points[1]) - 1)) {
                        out.writeUTF("Wrong position! Choose again");
                        out.flush();
                        continue;
                    } else {
                        out.writeUTF("Correct coordinates");
                        out.flush();
                    }

                    if ((winner = desk.checkWinner()) == 1 || !desk.hasFreePlaces()) {
                        out.writeUTF("Game over");
                        out.flush();
                        break;
                    } else {
                        out.writeUTF("Continue");
                        out.flush();
                    }

                    desk.setRandomPosition();

                    if ((winner = desk.checkWinner()) == -1 || !desk.hasFreePlaces()) {
                        out.writeUTF("Game over");
                        out.flush();
                        break;
                    } else {
                        out.writeUTF("Continue");
                        out.flush();
                    }
                }
            } else {
                return;
            }

            out.writeUTF(desk.getState());
            out.flush();

            if (winner == -1) {
                out.writeUTF("I win!");
                out.flush();
            } else if (winner == 1) {
                out.writeUTF("You win!");
                out.flush();
            } else {
                out.writeUTF("It's standoff!");
                out.flush();
            }

            in.close();
            out.close();

        } catch (Exception error) {
            System.err.println("tic_tac_toe.com - - [" +
                    new SimpleDateFormat("dd/MMM/Y:HH:mm:ss Z").format(Calendar.getInstance().getTime())
                    + "] player has disconnect from the server");
        }
    }
}
