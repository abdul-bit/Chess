package ChessGame;

import ChessGame.Gui.Table;
import ChessGame.board.Board;

public class Chess {
    public static void main(String[] args) {
        Board board = Board.createStandBoard();
        System.out.println(board);
        Table table = new Table();
    }

}
