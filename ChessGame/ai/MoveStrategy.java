package ChessGame.ai;

import ChessGame.board.Board;
import ChessGame.board.Move;

public interface MoveStrategy {

    Move execute(Board board);

}
