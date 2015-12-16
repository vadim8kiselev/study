package server;

public class Desk {

    private char[][] desk;
    {
        desk = new char[3][3];
        for (int index = 0; index < 3; index++) {
            for (int jndex = 0; jndex < 3; jndex++) {
                desk[index][jndex] = ' ';
            }
        }
    }

    private char clientMark;
    private char serverMark;

    public Desk(int id) {
        if (id == 0) {
            this.clientMark = 'X';
            this.serverMark = 'O';
        } else {
            this.clientMark = 'O';
            this.serverMark = 'X';
        }
    }

    public synchronized String getState() {
        return String.format("%c%c%c%c%c%c%c%c%c",
                desk[0][0], desk[0][1], desk[0][2],
                desk[1][0], desk[1][1], desk[1][2],
                desk[2][0], desk[2][1], desk[2][2]);
    }

    public synchronized void setState(String state){
        for (int index = 0; index < state.length(); index++)
            desk[index / 3][index % 3] = state.charAt(index);
    }

    public synchronized boolean hasFreePlaces() {
        for (int index = 0; index < 3; index++) {
            for (int jndex = 0; jndex < 3; jndex++) {
                if (desk[index][jndex] == ' ')
                    return true;
            }
        }
        return false;
    }

    public synchronized boolean setPosition(int rows, int columns) {
        if (desk[rows][columns] == ' ') {
            desk[rows][columns] = clientMark;
            return true;
        }
        return false;
    }

    public synchronized void setRandomPosition() {
        while (true) {
            int place = (int) (Math.random() * 9);
            if (desk[place / 3][place % 3] == ' ') {
                desk[place / 3][place % 3] = serverMark;
                break;
            }
        }
    }



    private synchronized boolean checkLine(char a, char b, char c) {
        return (a == b) && (b == c);
    }

    public synchronized int checkWinner() {
        for (int index = 0; index < 3; index++) {
            if (checkLine(desk[index][0], desk[index][1], desk[index][2]) && desk[index][0] == clientMark)
                return 1;
            if (checkLine(desk[0][index], desk[1][index], desk[2][index]) && desk[0][index] == clientMark)
                return 1;

            if (checkLine(desk[index][0], desk[index][1], desk[index][2]) && desk[index][0] == serverMark)
                return -1;
            if (checkLine(desk[0][index], desk[1][index], desk[2][index]) && desk[0][index] == serverMark)
                return -1;
        }
        if (checkLine(desk[0][0], desk[1][1], desk[2][2]) && desk[0][0] == clientMark)
            return 1;
        if (checkLine(desk[0][2], desk[1][1], desk[2][0]) && desk[0][2] == clientMark)
            return 1;

        if (checkLine(desk[0][0], desk[1][1], desk[2][2]) && desk[0][0] == serverMark)
            return -1;
        if (checkLine(desk[0][2], desk[1][1], desk[2][0]) && desk[0][2] == serverMark)
            return -1;

        return 0;
    }
}
