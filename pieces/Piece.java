package pieces;

import board.Move;
import board.Board;
import board.Square;

import java.util.List;

public abstract class Piece {
    protected final int piecePosition;
    protected final Color pieceColor;

    Piece(final int piecePosition, final Color pieceColor) {
        this.pieceColor = pieceColor;
        this.piecePosition = piecePosition;
    }

    public abstract List<Move> legalMoves(final Board board);

}
