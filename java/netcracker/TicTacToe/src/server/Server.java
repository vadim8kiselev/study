package server;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    static final int port = 8888;
    static ExecutorService threadPool = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {

        try {
            ServerSocket serverSocket = new ServerSocket(port);

            while (true) {
                Socket socket = serverSocket.accept();

                System.err.println("tic_tac_toe.com - - [" +
                        new SimpleDateFormat("dd/MMM/Y:HH:mm:ss Z").format(Calendar.getInstance().getTime())
                        + "] new player has connection to the server");

                threadPool.execute(new PlayerThread(socket));
            }
        } catch (Exception x) {
            x.printStackTrace();
        }
    }
}