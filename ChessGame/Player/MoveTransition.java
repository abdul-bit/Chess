package ChessGame.Player;

import ChessGame.board.Board;
import ChessGame.board.Move;

//when you transition from one move to another and all the information you wanna carry in that.
public class MoveTransition {

    private final Board transitionBoard;
    private final Move move;
    private final MoveStatus moveStatus;// we can do the move or can we do the move will it result in check etc.

    public MoveTransition(final Board transitionBoard,
            final Move move,
            final MoveStatus moveStatus) {
        this.transitionBoard = transitionBoard;
        this.move = move;
        this.moveStatus = moveStatus;
    }

    public MoveStatus getMoveStatus() {
        return this.moveStatus;
    }

    public Board getTransitionBoard() {
        return this.transitionBoard;
    }
}
