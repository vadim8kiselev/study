package server.manager;

import server.data.Data;
import server.entity.User;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * Created by mark on 19.12.15.
 */
public class MessageManager {

    private DatagramSocket socket;

    public MessageManager(DatagramSocket socket) {
        this.socket = socket;
    }

    public User checkUser(DatagramPacket request) {
        return Data.getInstance().addUser(request);
    }

    public void sendPacket(DatagramPacket response, User user) throws IOException {
        String message = new String(response.getData());

        if (!message.trim().equals("")) {
            String sendData = user.getNickname() + ": " + message;

            for (User man : Data.getInstance().getRecievers(user)) {
                socket.send(new DatagramPacket(sendData.getBytes(), sendData.length(), man.getIp(), man.getPort()));
            }
        }
    }
}
