package ChessGame;

import ChessGame.board.Board;

public class Chess {
    public static void main(String[] args) {
        Board board = Board.createStandBoard();
        System.out.println(board);
    }

}
