package pieces;

import java.util.Collection;

import board.Board;
import board.Move;

public abstract class Piece {
    protected final int piecePosition;
    protected final Color pieceColor;
    protected final boolean isFirstMove;

    Piece(final int piecePosition, final Color pieceColor) {
        this.pieceColor = pieceColor;
        this.piecePosition = piecePosition;

        // todo
        this.isFirstMove = false;
    }

    public abstract Collection<Move> CalculateLegalMoves(final Board board);

    public Color getColor() {
        return this.pieceColor;
    }

    public boolean isFirstMove() {
        return this.isFirstMove;
    }
}
