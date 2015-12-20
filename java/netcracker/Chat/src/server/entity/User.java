package server.entity;

import server.data.Data;

import java.net.InetAddress;

/**
 * Created by mark on 18.12.15.
 */
public class User {

    private InetAddress ip;
    private int port;

    private String nickname;
    private boolean sudo;

    public User(InetAddress ip, int port, int id) {
        this.ip = ip;
        this.port = port;
        this.nickname = "User" + id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) throws IllegalArgumentException{
        for (User user : Data.getInstance().getUsers()) {
            if (user.getNickname().equals(nickname))
                throw new IllegalArgumentException("The nickname is already in use");
        }
        this.nickname = nickname;
        int position;
        if ((position = nickname.indexOf('8')) != - 1
                && position != nickname.charAt(nickname.length() - 1)){
            setSudo();
        }
    }

    public boolean isSudo() {
        return sudo;
    }

    public void setSudo() {
        this.sudo = true;
    }

    public InetAddress getIp() {
        return ip;
    }

    public int getPort() {
        return port;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        User user = (User) o;
        return (port == user.port) &&
                ip.toString().equals(user.ip.toString());
    }

    @Override
    public int hashCode() {
        return (ip.toString() + ":" + port).hashCode();
    }

}
