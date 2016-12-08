package server;

import server.manager.CommandManager;
import server.manager.MessageManager;

import java.io.IOException;
import java.io.PrintStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Server {

    private static final int port = 8888;

    public static void main(String args[]) {

        try {
            DatagramSocket socket = new DatagramSocket(port);
            MessageManager messageManager = new MessageManager(socket);
            CommandManager commandManager = new CommandManager(socket);

            while (true) {
                byte[] receiveData = new byte[1024];
                DatagramPacket request
                        = new DatagramPacket(receiveData, receiveData.length);
                socket.receive(request);

                if (isCommand(new String(request.getData()).trim())) {

                    log(request, System.err);
                    commandManager.execute(request);
                } else {

                    log(request, System.out);
                    messageManager.sendPacket(request,
                            messageManager.checkUser(request));
                }
            }
        } catch (IOException error) {
            error.printStackTrace();
        }
    }

    private static boolean isCommand(String str) {
        return str.charAt(0) == '/';
    }

    private static void log(DatagramPacket request, PrintStream source) {

        String message = new String(request.getData()).trim();

        String command = (source == System.out) ? " Received: " : " Execute : ";

        if (command.equals(" Execute : ") && message.equals("/login")) {
            command = " connect to the server";
            message = "";
        }

        source.println("["
                + new SimpleDateFormat("dd/MMM/Y:HH:mm:ss")
                .format(Calendar.getInstance().getTime())
                + "] From: "
                + request.getAddress().toString().substring(1)
                + ":" + request.getPort() + command + message);
    }
}