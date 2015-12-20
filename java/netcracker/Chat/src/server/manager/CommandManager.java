package server.manager;

import server.data.Data;
import server.entity.User;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.HashMap;

/**
 * Created by mark on 18.12.15.
 */
public class CommandManager {

    private static DatagramSocket socket;

    private HashMap<String, Executable> methods;

    {
        methods = new HashMap<>();

        methods.put("login", (DatagramPacket request) ->
                Data.getInstance().addUser(request));

        methods.put("name", (DatagramPacket request) -> {
            User user = Data.getInstance().addUser(request);
            String oldNickname = user.getNickname();

            try {
                user.setNickname(getFirstArguement(request));
            } catch (IllegalArgumentException error){
                String wrong = error.getMessage();
                socket.send(new DatagramPacket(wrong.getBytes(),
                        wrong.length(),
                        request.getAddress(),
                        request.getPort()));
                return;
            }
            String response = oldNickname
                    + " has changed nickname to "
                    + user.getNickname();

            for (User man : Data.getInstance().getUsers()) {
                socket.send(new DatagramPacket(response.getBytes(),
                        response.length(),
                        man.getIp(),
                        man.getPort()));
            }
        });

        methods.put("whoami", (DatagramPacket request) -> {
            String response = Data.getInstance().addUser(request).getNickname();
            socket.send(new DatagramPacket(response.getBytes(),
                    response.length(),
                    request.getAddress(),
                    request.getPort()));
        });

        methods.put("ls", (DatagramPacket request) -> {
            StringBuilder list = new StringBuilder("");

            for (User man : Data.getInstance().getUsers()) {

                StringBuilder spaces = new StringBuilder("");
                for (int index = 0;
                     index < 15 - man.getNickname().length();
                     index++)
                    spaces.append(" ");

                list.append(man.getNickname() + spaces
                        + "| "
                        + ((man.isSudo()) ? "Admin" : "User") + "\n");
            }
            socket.send(new DatagramPacket(list.toString().getBytes(),
                    list.toString().length(),
                    request.getAddress(),
                    request.getPort()));
        });
        methods.put("to", (DatagramPacket request) -> {
            User user = Data.getInstance()
                    .getUserByName(getFirstArguement(request));

            if (user != null) {
                String response = "> "
                        + Data.getInstance()
                        .addUser(request).getNickname()
                        + ": " + getSecondArguement(request);

                socket.send(new DatagramPacket(
                        response.getBytes(),
                        response.length(),
                        user.getIp(),
                        user.getPort()));
            }
        });
        methods.put("exit", (DatagramPacket request) -> {
            Data data = Data.getInstance();
            data.deleteUser(data.addUser(request));
        });
    }

    public CommandManager(DatagramSocket socket) {
        this.socket = socket;
    }

    private String getCommand(DatagramPacket request) {
        String command = new String(request.getData()).trim() + " ";
        return command.substring(1, command.indexOf(" "));
    }

    private String getFirstArguement(DatagramPacket request) {
        String arguement = new String(request.getData()).trim() + " ";
        int firstSpace = arguement.indexOf(" ");
        return arguement.substring(firstSpace + 1,
                arguement.indexOf(" ", firstSpace + 1));
    }

    private String getSecondArguement(DatagramPacket request) {
        String arguement = new String(request.getData()).trim();
        int firstSpace = arguement.indexOf(" ", arguement.indexOf(" ") + 1);
        return arguement.substring(firstSpace + 1);
    }

    public void execute(DatagramPacket request) {
        try {
            Executable method = methods.get(getCommand(request));
            if (method != null)
                method.execute(request);
            else {
                String list = "Wrong command\n"
                        + "Try to use any command of these: \n\n"
                        + "login                   - login to chat\n"
                        + "ls                      - show all users in chat\n"
                        + "whoami                  - show your nickname\n"
                        + "name <NICKNAME>         - change nickname\n"
                        + "to <USERNAME> <MESSAGE> - send private message\n"
                        + "exit                    - exit from chat";
                socket.send(new DatagramPacket(list.getBytes(),
                        list.length(),
                        request.getAddress(),
                        request.getPort()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
