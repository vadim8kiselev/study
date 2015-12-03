package entities.figures;

import entities.Figure;

public enum Unit implements Figure {

    PAWN {
        @Override
        public void printBoard(int row, int column) {
            if (row != 0)
                board[row - 1].setCharAt(column, '*');

            board[row].setCharAt(column, 'P');
            showTable(Unit.PAWN);
        }
    },
    ELEPHANT {
        @Override
        public void printBoard(int row, int column) {
            for (int index = 0; index < 8; index++)
                for (int jndex = 0; jndex < 8; jndex++)
                    if ((row + column == index + jndex) || (row - column == index - jndex))
                        board[index].setCharAt(jndex, '*');

            board[row].setCharAt(column, 'E');
            showTable(Unit.ELEPHANT);
        }
    },
    CASTLE {
        @Override
        public void printBoard(int row, int column) {
            for (int index = 0; index < 8; index++)
                for (int jndex = 0; jndex < 8; jndex++)
                    if (row == index || column == jndex)
                        board[index].setCharAt(jndex, '*');
            board[row].setCharAt(column, 'C');
            showTable(Unit.CASTLE);
        }
    },
    HORSE {
        @Override
        public void printBoard(int row, int column) {
            for (int index = 0; index < 8; index++)
                for (int jndex = 0; jndex < 8; jndex++)
                    if ((Math.abs(row - index) == 1 && Math.abs(jndex - column) == 2)
                            || Math.abs(column - jndex) == 1 && Math.abs(index - row) == 2) {
                        board[index].setCharAt(jndex, '*');
                    }
            board[row].setCharAt(column, 'H');
            showTable(Unit.HORSE);
        }
    },
    QUEEN {
        @Override
        public void printBoard(int row, int column) {
            for (int index = 0; index < 8; index++)
                for (int jndex = 0; jndex < 8; jndex++)
                    if ((row == index || column == jndex)
                            || (row + column == index + jndex)
                            || (row - column == index - jndex))
                        board[index].setCharAt(jndex, '*');

            board[row].setCharAt(column, 'Q');
            showTable(Unit.QUEEN);
        }
    },
    KING {
        @Override
        public void printBoard(int row, int column) {
            for (int index = Math.max(row - 1, 0); index < Math.min(row + 2, 8); index++)
                for (int jndex = Math.max(column - 1, 0); jndex < Math.min(column + 2, 8); jndex++)
                    board[index].setCharAt(jndex, '*');
            board[row].setCharAt(column, 'K');
            showTable(Unit.KING);
        }
    };

    static StringBuilder[] board = createBoard();

    private static StringBuilder[] createBoard() {
        StringBuilder[] board = new StringBuilder[8];
        for (int index = 0; index < board.length; index++) {
            board[index] = new StringBuilder("00000000");
        }
        return board;
    }

    private static void showTable(Unit unit) {
        System.out.println(unit.name());
        for (int index = 0; index < 8; index++) {
            for (int jndex = 0; jndex < 8; jndex++)
                System.out.print(board[index].charAt(jndex) + " ");
            System.out.println();
        }
    }
}
