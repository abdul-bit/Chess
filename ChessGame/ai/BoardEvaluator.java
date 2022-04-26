package ChessGame.ai;

import ChessGame.board.Board;

public interface BoardEvaluator {

    int evaluate(Board board, int depth);

}
