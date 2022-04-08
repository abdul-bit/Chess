package board;

import pieces.Piece;

public abstract class Move {
    final Board board;
    final Piece movedPiece;
    final int destinationCoordinate;

    public Move(final Board board, final Piece movedPiece, final int destinationCoordinate) {
        this.board = board;
        this.movedPiece = movedPiece;
        this.destinationCoordinate = destinationCoordinate;
    }

    public static final class MajorMove extends Move {
        public MajorMove(final Board board,
                final Piece movedPiece,
                final int destinationCoordinate) {
            super(board, movedPiece, destinationCoordinate);
        }
    }

    public static final class AttackMove extends Move {
        final Piece pieceBeingAttacked;

        public AttackMove(final Board board,
                final Piece movedPiece,
                final int destinationCoordinate, final Piece pieceBeingAttacked) {
            super(board, movedPiece, destinationCoordinate);
            this.pieceBeingAttacked = pieceBeingAttacked;
        }
    }

}