package client;

import server.Desk;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.regex.Pattern;

public class Client {

    static final int port = 8888;
    static final String address = "127.0.0.1";

    static DataInputStream in;
    static DataOutputStream out;
    static BufferedReader input;
    static Desk desk = null;

    public static void main(String[] args) {
        try {
            InetAddress ipAddress = InetAddress.getByName(address);
            Socket socket = new Socket(ipAddress, port);

            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            input = new BufferedReader(new InputStreamReader(System.in));

            System.out.println(in.readUTF());
            out.writeUTF(input.readLine());
            out.flush();

            while (true) {
                System.out.println(in.readUTF());
                String choise = input.readLine();
                out.writeUTF(choise);
                out.flush();

                boolean serverFirst = choise.charAt(0) == 'y';

                desk = new Desk((serverFirst) ? 1 : 0);

                while (desk.hasFreePlaces()) {

                    if (serverFirst) {
                        serverStep();

                    } else {
                        System.out.println(showDesk(desk.getState()));
                        clientStep();
                    }

                    if (desk.checkWinner() == ((serverFirst) ? -1 : 1)
                            || !desk.hasFreePlaces()) {
                        break;
                    }

                    if (serverFirst) {
                        System.out.println(showDesk(desk.getState()));
                        clientStep();
                    } else {
                        serverStep();
                    }

                    if (desk.checkWinner() == ((serverFirst) ? 1 : -1)) {
                        break;
                    }
                }

                System.out.println(showDesk(in.readUTF()));
                System.out.println(in.readUTF());

                System.out.println(in.readUTF());
                String repeat = input.readLine();
                out.writeUTF(repeat);
                out.flush();

                if (repeat.charAt(0) != 'y')
                    break;
            }

        } catch (Exception x) {
            System.err.println("Server was closed");
            x.printStackTrace();

        } finally {
            try {
                in.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void clientStep() throws IOException{
        String request = in.readUTF();
        while (true) {
            System.out.println(request);
            String coordinates = input.readLine();
            String[] points = coordinates.split(" ");
            if (!desk.setPosition(Integer.parseInt(points[0]) - 1,
                    Integer.parseInt(points[1]) - 1)
                    || !Pattern.compile("^\\d+ \\d+$").matcher(coordinates).matches() ) {
                System.out.println("Wrong position! Choose again");
                continue;
            } else {
                break;
            }
        }
        out.writeUTF(desk.getState());
        out.flush();
    }

    private static void serverStep() throws IOException {
        desk.setState(in.readUTF());
    }

    private static String showDesk(String state){
        return String.format(
                        "+-----+-----+-----+\n" +
                        "|  %c  |  %c  |  %c  |\n" +
                        "+-----+-----+-----+\n" +
                        "|  %c  |  %c  |  %c  |\n" +
                        "+-----+-----+-----+\n" +
                        "|  %c  |  %c  |  %c  |\n" +
                        "+-----+-----+-----+\n",
                state.charAt(0), state.charAt(1), state.charAt(2),
                state.charAt(3), state.charAt(4), state.charAt(5),
                state.charAt(6), state.charAt(7), state.charAt(8));
    }

}
