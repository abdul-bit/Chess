package ChessGame.board;

import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.ImmutableMap;

import ChessGame.Pieces.Piece;

//we have declared the square class as abstract to prevent its instatiation whereas we can instantiate concrete subclasses
public abstract class Square {

    protected final int squareCoordinate;

    private static final Map<Integer, EmptySquare> EMPTY_SQUARES_CACHE = createAllPossibleEmptySquares();

    private static Map<Integer, EmptySquare> createAllPossibleEmptySquares() {

        final Map<Integer, EmptySquare> emptySquareMap = new HashMap<>();

        for (int i = 0; i < BoardUtils.NUM_SQUARES; i++) {
            emptySquareMap.put(i, new EmptySquare(i));
        }

        return ImmutableMap.copyOf(emptySquareMap);
    }

    public static Square createSquare(final int SquareCoordinate, final Piece piece) {
        return piece != null ? new OccupiedSquare(SquareCoordinate, piece) : EMPTY_SQUARES_CACHE.get(SquareCoordinate);
    }

    private Square(final int squareCoordinate) {
        this.squareCoordinate = squareCoordinate;

    }

    public abstract boolean isSquareOccupied(); // Checks to see if theres a chess piece on the Square //

    public abstract Piece getPiece();

    public int getSquareCoordinate() {
        return this.squareCoordinate;
    }

    public static final class EmptySquare extends Square {
        EmptySquare(final int coordinate) {
            super(coordinate);
        }

        @Override
        public String toString() {
            return "-";
        }

        @Override
        public boolean isSquareOccupied() {
            return false;
        }

        @Override
        public Piece getPiece() {
            return null;
        }

    }

    public static final class OccupiedSquare extends Square {
        private final Piece pieceOnSquare;

        OccupiedSquare(int squareCoordinate, final Piece pieceonSquare) {
            super(squareCoordinate);
            this.pieceOnSquare = pieceonSquare;

        }

        @Override
        public String toString() {
            return getPiece().getPieceColor().isBlack() ? getPiece().toString().toLowerCase()
                    : getPiece().toString();
        }

        @Override
        public boolean isSquareOccupied() {
            return true;
        }

        @Override
        public Piece getPiece() {
            return this.pieceOnSquare;

        }
    }
}
