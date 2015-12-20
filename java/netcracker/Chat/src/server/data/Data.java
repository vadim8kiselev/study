package server.data;

import server.entity.User;

import java.net.DatagramPacket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mark on 19.12.15.
 */
public class Data {

    private static final Data instance = new Data();
    private List<User> users = new ArrayList<>();

    private Data() {
    }

    public static Data getInstance() {
        return instance;
    }

    public User addUser(DatagramPacket request) {
        User user = new User(request.getAddress(), request.getPort(), users.size());
        int position;
        if ((position = users.indexOf(user)) != -1) {
            return users.get(position);
        }
        users.add(user);
        return user;
    }

    public User getUserByName(String nickname) {
        for (User user : users) {
            if (user.getNickname().equals(nickname))
                return user;
        }
        return null;
    }

    public List<User> getUsers() {
        return users;
    }

    public List<User> getRecievers(User user) {
        if (!users.contains(user)) {
            users.add(user);
        }
        List<User> recievers = new ArrayList<>(users);
        recievers.remove(user);
        return recievers;
    }

    public void deleteUser(User user) {
        users.remove(user);
    }
}
