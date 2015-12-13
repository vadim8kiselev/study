package client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;

public class Client {

    static final int port = 8888;
    static final String address = "127.0.0.1";

    public static void main(String[] args) {
        try {
            InetAddress ipAddress = InetAddress.getByName(address);
            Socket socket = new Socket(ipAddress, port);

            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

            String play;
            System.out.println(in.readUTF());
            String choise = input.readLine();
            out.writeUTF(choise);
            out.flush();

            if (choise.charAt(0) == 'y') {

                while (true) {

                    play = in.readUTF();
                    if (play.equals("Game over"))
                        break;

                    while (true) {
                        System.out.println(in.readUTF());
                        out.writeUTF(input.readLine());
                        out.flush();

                        String correct = in.readUTF();
                        if (correct.charAt(0) == 'C') {
                            break;
                        } else {
                            System.out.println(correct);
                        }
                    }

                    play = in.readUTF();
                    if (play.equals("Game over"))
                        break;
                }

            } else if (choise.charAt(0) == 'n') {

                while (true) {
                    System.out.println(in.readUTF());
                    out.writeUTF(input.readLine());
                    out.flush();

                    String correct = in.readUTF();

                    if (correct.charAt(0) == 'W') {
                        System.out.println(correct);
                        continue;
                    }

                    play = in.readUTF();
                    if (play.equals("Game over"))
                        break;

                    play = in.readUTF();
                    if (play.equals("Game over"))
                        break;
                }

            } else {
                return;
            }
            System.out.println(in.readUTF());
            System.out.println(in.readUTF());

            in.close();
            out.close();

        } catch (Exception x) {
            System.err.println("Server was closed");
        }

    }
}