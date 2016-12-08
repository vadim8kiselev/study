package client.threads;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * Created by mark on 20.12.15.
 */
public class ReadingThread implements Runnable {

    private DatagramSocket socket;
    private int receiveDataSize;

    public ReadingThread(DatagramSocket socket, int receiveDataSize){
        this.socket = socket;
        this.receiveDataSize = receiveDataSize;
    }

    @Override
    public void run(){
        try {
            while (true) {
                byte[] receiveData = new byte[receiveDataSize];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                socket.receive(receivePacket);
                System.out.println(new String(receivePacket.getData()));
            }
        } catch (IOException error) {
            error.printStackTrace();

        } finally {
            if (socket != null)
                socket.close();
        }
    }
}
