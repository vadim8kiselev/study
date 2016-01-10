package systems;

import entities.message.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mark on 10.11.15.
 */
public class SpywareData {

    private static List<Message> spywareData = new ArrayList<>();

    public static void addMessage(Message message) {
        spywareData.add(message);
    }

    public static List<Message> getSpywareData() {
        return spywareData;
    }


}
