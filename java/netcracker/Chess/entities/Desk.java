package entities.figures;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Created by mark on 16.12.15.
 */
public class Desk {

    static StringBuilder[] board = new StringBuilder[8];
    private int row;
    private int column;

    static{
        for (int index = 0; index < board.length; index++) {
            board[index] = new StringBuilder("00000000");
        }
    }

    public Desk(Scanner input){
        this.row = 0;
        this.column = 0;
        for (int index = 0; index < 8; index++) {
            String line = input.next().replaceAll(" ", "");
            for (int jndex = 0; jndex < line.length(); jndex++) {
                if (line.charAt(jndex) != '0' && line.charAt(jndex) != ' '){
                    row = index;
                    column = jndex;
                    break;
                }
            }
        }
    }

    public void showTable(Unit unit) {
        try (PrintWriter output = new PrintWriter("/tmp/chess_answer.txt")) {
            output.println(unit.name());
            unit.setPath(row, column);

            for (int index = 0; index < 8; index++) {
                for (int jndex = 0; jndex < 8; jndex++)
                    output.print(board[index].charAt(jndex) + " ");
                output.println();
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
