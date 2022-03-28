package pieces;

import java.util.List;

import board.Board;
import board.Move;
import board.Square;

public class Knight extends Piece {
    private final static int[] POSSIBLE_MOVE_COORDINATES = { -17, -15, -10, -6, 6, 10, 15, 17 };

    Knight(final int piecePosition, final Color pieceColor) {
        super(piecePosition, pieceColor);
    }

    @Override
    public List<Move> legalMoves(Board board) {
        for (final int currentCoordinate : POSSIBLE_MOVE_COORDINATES) {
            int destinationCoordinate = this.piecePosition + currentCoordinate;
            if (true/* is valid */) {
                final Square destinationCoordinateSquare = board.getSquare(destinationCoordinate);
                if (!destinationCoordinateSquare.isSquareOccupied()) {
                    legalMoves.add(new Move());
                }
            }
        }

        return null;
    }

}
