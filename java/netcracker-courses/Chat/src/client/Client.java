package client;

import client.threads.ReadingThread;
import client.threads.WritingThread;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Created by mark on 18.12.15.
 */
public class Client {

    private static final int receiveDataSize = 1024;
    private static final int port = 8888;
    private static InetAddress ip;

    public static void main(String[] args) {
        try {

            ip = InetAddress.getByName("localhost");
            DatagramSocket socket = new DatagramSocket();

            new Thread(new WritingThread(socket, ip, port)).start();
            new Thread(new ReadingThread(socket, receiveDataSize)).start();

        } catch (IOException error) {
            error.printStackTrace();
        }
    }
}
