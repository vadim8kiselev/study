package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PlayerThread implements Runnable {

    DataInputStream in;
    DataOutputStream out;
    Desk desk;
    String name;
    private Socket clientSocket;

    public PlayerThread(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {

        try {

            in = new DataInputStream(clientSocket.getInputStream());
            out = new DataOutputStream(clientSocket.getOutputStream());

            out.writeUTF("What is your name?");
            out.flush();
            this.name = in.readUTF();

            while (true) {
                out.writeUTF("I will go first? (yes/no)");
                out.flush();
                String choise = in.readUTF();
                int winner = 0;

                boolean serverFirst = choise.charAt(0) == 'y';

                desk = new Desk((serverFirst) ? 1 : 0);

                while (desk.hasFreePlaces()) {

                    if (serverFirst) {
                        serverStep();
                    } else {
                        clientStep();
                    }

                    if ((winner = desk.checkWinner()) == ((serverFirst) ? -1 : 1)
                            || !desk.hasFreePlaces()) {
                        break;
                    }

                    if (serverFirst) {
                        clientStep();
                    } else {
                        serverStep();
                    }

                    if ((winner = desk.checkWinner()) == ((serverFirst) ? 1 : -1)) {
                        break;
                    }

                }

                out.writeUTF(desk.getState());
                out.flush();

                String win;
                if (winner == -1) {
                    win = "I win!";
                } else if (winner == 1) {
                    win = "You win!";
                } else {
                    win = "It's standoff!";
                }
                out.writeUTF(win);
                out.flush();

                out.writeUTF("Do you want to play again? (yes/no)");
                out.flush();
                if (in.readUTF().charAt(0) != 'y')
                    break;
            }

        } catch (Exception error) {
            error.printStackTrace();

        } finally {
            clientSocket.close();
            
            try {
                if (in != null)
                    in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            try {
                if (out != null)
                    out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            System.out.println("tic_tac_toe.com - - [" +
                    new SimpleDateFormat("dd/MMM/Y:HH:mm:ss Z").format(Calendar.getInstance().getTime())
                    + "] " + name + "(" +
                    clientSocket.getInetAddress().toString().substring(1) +
                    ") has disconnect from the server");
        }
    }

    private void clientStep() throws IOException {
        out.writeUTF("Write two coordinates as \"x y\": ");
        out.flush();
        desk.setState(in.readUTF());
    }

    private void serverStep() throws IOException {
        desk.setRandomPosition();
        out.writeUTF(desk.getState());
        out.flush();
    }
}
