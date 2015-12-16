package entities;

import static entities.Desk.board;

public enum Unit {

    PAWN {
        public void setPath(int row, int column) {
            if (row != 0)
                board[row - 1][column] = '*';

            board[row][column] = 'P';
        }
    },
    ELEPHANT {
        public void setPath(int row, int column) {
            for (int index = 0; index < 8; index++)
                for (int jndex = 0; jndex < 8; jndex++)
                    if ((row + column == index + jndex) || (row - column == index - jndex))
                        board[index][jndex] = '*';

            board[row][column] = 'E';
        }
    },
    CASTLE {
        public void setPath(int row, int column) {
            for (int index = 0; index < 8; index++)
                for (int jndex = 0; jndex < 8; jndex++)
                    if (row == index || column == jndex)
                        board[index][jndex] = '*';
            board[row][column] = 'C';
        }
    },
    HORSE {
        public void setPath(int row, int column) {
            for (int index = 0; index < 8; index++)
                for (int jndex = 0; jndex < 8; jndex++)
                    if ((Math.abs(row - index) == 1 && Math.abs(jndex - column) == 2)
                            || Math.abs(column - jndex) == 1 && Math.abs(index - row) == 2) {
                        board[index][jndex] = '*';
                    }
            board[row][column] = 'H';
        }
    },
    QUEEN {
        public void setPath(int row, int column) {
            for (int index = 0; index < 8; index++)
                for (int jndex = 0; jndex < 8; jndex++)
                    if ((row == index || column == jndex)
                            || (row + column == index + jndex)
                            || (row - column == index - jndex))
                        board[index][jndex] = '*';

            board[row][column] = 'Q';
        }
    },
    KING {
        public void setPath(int row, int column) {
            for (int index = Math.max(row - 1, 0); index < Math.min(row + 2, 8); index++)
                for (int jndex = Math.max(column - 1, 0); jndex < Math.min(column + 2, 8); jndex++)
                    board[index][jndex] = '*';
            board[row][column] = 'K';
        }
    };

    abstract public void setPath(int row, int column);
}