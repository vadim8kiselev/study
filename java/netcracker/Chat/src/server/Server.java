package server;

import server.manager.CommandManager;
import server.manager.MessageManager;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

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

                String message = new String(request.getData()).trim();

                StringBuilder spaces = new StringBuilder("");
                for (int index = 0;
                     index < 20 - message.length();
                     index++)
                    spaces.append(" ");

                String log = "Received: " + message + spaces
                        + " From: "
                        + request.getAddress().toString().substring(1)
                        + ":" + request.getPort();
                if (isCommand(message)) {
                    System.err.println(log);
                }else{
                    System.out.println(log);
                }

                if (isCommand(message)) {
                    commandManager.execute(request);
                } else {
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
}