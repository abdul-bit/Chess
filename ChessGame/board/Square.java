package ChessGame.Board;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import ChessGame.Pieces.Piece;

public abstract class Square {
    protected final int squareCoordinate; // to prevent mutation from any of the players who are using the api

    private static final Map<Integer, EmptySquare> EMPTY_SQUARES = createSquares(); // creating all the empty squares

    private static Map<Integer, EmptySquare> createSquares() {
        final Map<Integer, EmptySquare> emptySquareMap = new HashMap<>();
        for (int i = 0; i < BoardUtils.NUM_SQUARES; i++) {
            emptySquareMap.put(i, new EmptySquare(i));
        }

        return Collections.unmodifiableMap(emptySquareMap);
    }

    public static Square createSquare(final int squareCoordinate, final Piece piece) {
        return piece != null ? new OccupiedSquare((squareCoordinate), piece) : EMPTY_SQUARES.get(squareCoordinate);

    }// since all constructors are privatised the only way to create a new square is
     // using this method

    private Square(int squareCoordinate) {
        this.squareCoordinate = squareCoordinate;
    }

    public abstract boolean isSquareOccupied();

    public abstract Piece getPiece();

    public static final class EmptySquare extends Square {
        private EmptySquare(final int coordinate) {
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
        private final Piece pieceOnSquare; // cant be referenced without calling get piece on it

        private OccupiedSquare(final int coordinate, Piece pieceOnSquare) {
            super(coordinate);
            this.pieceOnSquare = pieceOnSquare;
        }

        @Override
        public String toString() {
            return getPiece().getColor().isBlack() ? "B_" + getPiece().toString() : "W_" + getPiece().toString();
        }

        @Override
        public boolean isSquareOccupied() {
            return false;
        }

        @Override

        public Piece getPiece() {
            return pieceOnSquare;
        }
    }

    public int getSquareCoordinate() {
        return this.squareCoordinate;
    }
}
