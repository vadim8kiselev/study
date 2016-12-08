package server.manager;

import java.io.IOException;
import java.net.DatagramPacket;

/**
 * Created by mark on 20.12.15.
 */
public interface Executable {

    void execute(DatagramPacket request) throws IOException;
}
