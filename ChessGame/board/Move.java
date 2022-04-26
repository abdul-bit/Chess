package ChessGame.board;

import ChessGame.Pieces.Pawn;
import ChessGame.Pieces.Piece;
import ChessGame.Pieces.Rook;
import ChessGame.board.Board.Builder;

public abstract class Move {
    final Board board;
    final Piece movedPiece;
    final int destinationCoordinate;
    public static final Move NULL_MOVE = new NullMove();

    public Move(
            final Board board,
            final Piece movedPiece,
            final int destinationCoordinate) {
        this.board = board;
        this.movedPiece = movedPiece;
        this.destinationCoordinate = destinationCoordinate;
    }

    public Board execute() {

        final Builder builder = new Builder();
        for (final Piece piece : this.board.currentPlayer().getActivePieces()) {

            if (!this.movedPiece.equals(piece)) {
                builder.setPiece(piece);
            }
        }
        for (final Piece piece : this.board.currentPlayer().getOpponent().getActivePieces()) {
            builder.setPiece(piece);
        }
        // move the moved piece
        builder.setPiece(this.movedPiece.movePiece(this));
        builder.setMoveMaker(this.board.currentPlayer().getOpponent().getColor());
        return builder.build();
    }

    public Piece getMovedPiece() {
        return this.movedPiece;
    }

    public static final class MajorMove extends Move {
        public MajorMove(final Board board,
                final Piece movedPiece,
                final int destinationCoordinate) {
            super(board, movedPiece, destinationCoordinate);
        }

    }

    public static class AttackMove extends Move {
        final Piece pieceBeingAttacked;

        public AttackMove(final Board board,
                final Piece movedPiece,
                final int destinationCoordinate, final Piece pieceBeingAttacked) {
            super(board, movedPiece, destinationCoordinate);
            this.pieceBeingAttacked = pieceBeingAttacked;
        }

        @Override
        public int hashCode() {
            return this.pieceBeingAttacked.hashCode() + super.hashCode();
        }

        @Override
        public boolean equals(final Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof AttackMove)) {

                return false;
            }
            final AttackMove otheraAttackMove = (AttackMove) other;
            return super.equals(otheraAttackMove) && getAttackedPiece().equals(otheraAttackMove.getAttackedPiece());
        }

        @Override
        public Board execute() {

            return null;
        }

        @Override
        public boolean isAttack() {
            return true;
        }

        @Override
        public Piece getAttackedPiece() {
            return this.pieceBeingAttacked;
        }
    }

    public static final class PawnMove extends Move {
        public PawnMove(final Board board,
                final Piece movedPiece,
                final int destinationCoordinate) {
            super(board, movedPiece, destinationCoordinate);
        }

    }

    public static class PawnAttackMove extends AttackMove {
        public PawnAttackMove(final Board board,
                final Piece movedPiece,
                final int destinationCoordinate,
                final Piece pieceBeingAttacked) {
            super(board, movedPiece, destinationCoordinate, pieceBeingAttacked);
        }

    }

    public static final class PawnEnPassantMove extends PawnAttackMove {
        public PawnEnPassantMove(final Board board,
                final Piece movedPiece,
                final int destinationCoordinate,
                final Piece pieceBeingAttacked) {
            super(board, movedPiece, destinationCoordinate, pieceBeingAttacked);
        }

    }

    public static final class PawnJump extends Move {
        public PawnJump(final Board board,
                final Piece movedPiece,
                final int destinationCoordinate) {
            super(board, movedPiece, destinationCoordinate);
        }

        @Override
        public Board execute() {
            final Builder builder = new Builder();
            for (final Piece piece : this.board.currentPlayer().getActivePieces()) {
                if (!this.movedPiece.equals(piece)) {
                    builder.setPiece(piece);
                }

            }
            for (final Piece piece : this.board.currentPlayer().getOpponent().getActivePieces()) {
                builder.setPiece(piece);

            }
            final Pawn movedPawn = (Pawn) this.movedPiece.movePiece(this);
            builder.setPiece(movedPawn);
            builder.setEnPassant(movedPawn);
            builder.setMoveMaker(this.board.currentPlayer().getOpponent().getColor());
            return builder.build();
        }
    }

    static abstract class CastleMove extends Move {
        protected final Rook castleRook;
        protected final int castleRookStart;
        protected final int castleRookDestination;

        public CastleMove(final Board board,
                final Piece movedPiece,
                final int destinationCoordinate,
                final Rook castleRook,
                final int castleRookStart,
                final int castleRookDestination) {
            super(board, movedPiece, destinationCoordinate);
            this.castleRook = castleRook;
            this.castleRookStart = castleRookStart;
            this.castleRookDestination = castleRookDestination;
        }

        public Rook getCastleRook() {
            return this.castleRook;
        }

        @Override
        public boolean isCastlingMove() {
            return true;
        }

        @Override
        public Board execute() {
            final Builder builder = new Builder();
            for (final Piece piece : this.board.currentPlayer().getActivePieces()) {
                if (!this.movedPiece.equals(piece) && !this.castleRook.equals(piece)) {
                    builder.setPiece(piece);
                }

            }
            for (final Piece piece : this.board.currentPlayer().getOpponent().getActivePieces()) {
                builder.setPiece(piece);
            }
            builder.setPiece(this.movedPiece.movePiece(this));
            builder.setPiece(new Rook(this.castleRook.getColor(), this.castleRookDestination));
            builder.setMoveMaker(this.board.currentPlayer().getOpponent().getColor());
            return builder.build();
        }

    }

    public static final class KingSideCastleMove extends CastleMove {
        public KingSideCastleMove(final Board board,
                final Piece movedPiece,
                final int destinationCoordinate,
                final Rook castleRook,
                final int castleRookStart,
                final int castleRookDestination) {
            super(board, movedPiece, destinationCoordinate, castleRook, castleRookStart, castleRookDestination);
        }

        @Override
        public String toString() {
            return "O-O";
        }
    }

    public static final class QueenSideCastleMove extends CastleMove {
        public QueenSideCastleMove(final Board board,
                final Piece movedPiece,
                final int destinationCoordinate,
                final Rook castleRook,
                final int castleRookStart,
                final int castleRookDestination) {
            super(board, movedPiece, destinationCoordinate, castleRook, castleRookStart, castleRookDestination);
        }

        @Override
        public String toString() {
            return "O-O-O";
        }

    }

    public static final class NullMove extends Move {
        public NullMove() {
            super(null, null, -1);
        }

        @Override
        public Board execute() {

            throw new RuntimeException("Cannot execute the null move");
        }
    }

    public static class MoveFactory {
        private MoveFactory() {
            throw new RuntimeException("not instantiable!!!");
        }

        public static Move createMove(final Board board,
                final int currentCoordinate,
                final int destinationCoordinate) {
            for (final Move move : board.getAllLegalMoves()) {
                if (move.getCurrentCoordinate() == currentCoordinate
                        && move.getDestinationCoordinate() == destinationCoordinate) {

                    return move;
                }

            }
            return NULL_MOVE;
        }
    }

    public int getDestinationCoordinate() {

        return this.destinationCoordinate;
    }

    public int getCurrentCoordinate() {
        return this.getMovedPiece().getPiecePosition();
    }

    public boolean isAttack() {
        return false;
    }

    public boolean isCastlingMove() {
        return false;
    }

    public Piece getAttackedPiece() {
        return null;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;

        result = prime * result + this.destinationCoordinate;
        result = prime * result + this.movedPiece.hashCode();
        return result;
    }

    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Move)) {
            return false;
        }
        final Move othermove = (Move) other;
        return this.getCurrentCoordinate() == othermove.getCurrentCoordinate()
                && this.getDestinationCoordinate() == othermove.getDestinationCoordinate() &&
                getMovedPiece().equals(othermove.getMovedPiece());
    }
}