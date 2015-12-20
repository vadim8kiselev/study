package client.threads;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

/**
 * Created by mark on 20.12.15.
 */
public class WritingThread implements Runnable {

    private final int port;
    private InetAddress ip;
    private byte[] sendData;
    private DatagramSocket socket;
    private Scanner reader;

    public WritingThread(DatagramSocket socket, InetAddress ip, int port) {
        this.socket = socket;
        this.ip = ip;
        this.port = port;
    }

    @Override
    public void run() {
        try {
            reader = new Scanner(System.in);

            while (true) {
                sendData = reader.nextLine().getBytes();
                socket.send(new DatagramPacket(sendData, sendData.length, ip, port));
            }
        } catch (IOException error) {
            error.printStackTrace();

        } finally {
            if (socket != null) {
                socket.close();
            }
            if (reader != null) {
                reader.close();
            }
        }
    }
}
