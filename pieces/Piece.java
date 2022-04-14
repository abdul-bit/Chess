package pieces;

import java.util.Collection;

import board.Board;
import board.Move;

public abstract class Piece {
    protected final int piecePosition;
    protected final Color pieceColor;

    Piece(final int piecePosition, final Color pieceColor) {
        this.pieceColor = pieceColor;
        this.piecePosition = piecePosition;
    }

    public abstract Collection<Move> CalculateLegalMoves(final Board board);

    public Color getColor() {
        return this.pieceColor;
    }

}
