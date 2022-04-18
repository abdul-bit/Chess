package ChessGame.pieces;

import java.util.Collection;

import ChessGame.board.Board;
import ChessGame.board.Move;

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

    public int getPiecePosition() {
        return this.piecePosition;
    }

    public abstract Collection<Move> CalculateLegalMoves(final Board board);

    public Color getColor() {
        return this.pieceColor;
    }

    public boolean isFirstMove() {
        return this.isFirstMove;
    }

    public enum PieceType {
        PAWN("P"),
        KNIGHT("N"),
        BISHOP("B"),
        ROOK("R"),
        QUEEN("Q"),
        KING("K");

        private final String pieceName;

        PieceType(final String pieceName) {

            this.pieceName = pieceName;
        }

        @Override
        public String toString() {
            return this.pieceName;
        }
    }
}
