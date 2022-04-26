package ChessGame;

import ChessGame.Gui.Table;
import ChessGame.board.Board;

public class Chess {

    public static void main(String[] args) {

        Board board = Board.createStandardBoard();

        System.out.println(board);

        Table.get().show();

    }

}