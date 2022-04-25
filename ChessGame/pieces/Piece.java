package ChessGame.Pieces;

import java.util.Collection;

import ChessGame.Board.Board;
import ChessGame.Board.Move;

public abstract class Piece {
    protected final PieceType pieceType;
    protected final int piecePosition;
    protected final Color pieceColor;
    protected final boolean isFirstMove;
    private final int cachedHashcode;

    Piece(final PieceType pieceType, final int piecePosition, final Color pieceColor) {
        this.pieceType = pieceType;
        this.pieceColor = pieceColor;
        this.piecePosition = piecePosition;
        this.cachedHashcode = computeHashcode();

        // todo
        this.isFirstMove = false;
    }

    private int computeHashcode() {
        int result = pieceType.hashCode();
        result = 31 * result + pieceColor.hashCode();
        result = 31 * result + piecePosition;
        result = 31 * result + (isFirstMove ? 1 : 0);
        return result;
    }

    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Piece)) {
            return false;
        }
        final Piece otherPiece = (Piece) other;
        return piecePosition == otherPiece.getPiecePosition() && pieceType == otherPiece.getPieceType()
                && pieceColor == otherPiece.getColor() && isFirstMove == otherPiece.isFirstMove;

    }

    @Override
    public int hashCode() {
        return this.cachedHashcode;

    }

    public int getPiecePosition() {
        return this.piecePosition;
    }

    public abstract Collection<Move> CalculateLegalMoves(final Board board);

    public abstract Piece movePiece(Move move);

    public Color getColor() {
        return this.pieceColor;
    }

    public boolean isFirstMove() {
        return this.isFirstMove;
    }

    public enum PieceType {
        PAWN("P") {
            @Override
            public boolean isKing() {
                return false;
            }

            @Override
            public boolean isRook() {
                return false;
            }
        },
        KNIGHT("N") {
            @Override
            public boolean isKing() {
                return false;
            }

            @Override
            public boolean isRook() {
                return false;
            }
        },
        BISHOP("B") {
            @Override
            public boolean isKing() {
                return false;
            }

            @Override
            public boolean isRook() {
                return false;
            }
        },
        ROOK("R") {
            @Override
            public boolean isKing() {
                return false;
            }

            @Override
            public boolean isRook() {
                return true;
            }
        },
        QUEEN("Q") {
            @Override
            public boolean isKing() {
                return false;
            }

            @Override
            public boolean isRook() {
                return false;
            }
        },
        KING("K") {
            @Override
            public boolean isKing() {
                return true;
            }

            @Override
            public boolean isRook() {
                return false;
            }
        };

        private final String pieceName;

        PieceType(final String pieceName) {

            this.pieceName = pieceName;
        }

        @Override
        public String toString() {
            return this.pieceName;
        }

        public abstract boolean isKing();

        public abstract boolean isRook();

    }

    public PieceType getPieceType() {
        return this.pieceType;
    }

}
