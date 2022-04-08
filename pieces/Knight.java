package pieces;

import java.util.Collection;
import java.util.Collections;

import board.Board;
import board.Move;
import board.Square;
import java.util.ArrayList;
import board.BoardUtils;

public class Knight extends Piece {
    private final static int[] POSSIBLE_MOVE_COORDINATES = { -17, -15, -10, -6, 6, 10, 15, 17 };

    Knight(final int piecePosition, final Color pieceColor) {
        super(piecePosition, pieceColor);
    }

    @Override
    public Collection<Move> CalculateLegalMoves(Board board) {

        final Collection<Move> legalMoves = new ArrayList<>();
        for (final int currentCoordinate : POSSIBLE_MOVE_COORDINATES) {
            final int destinationCoordinate = this.piecePosition + currentCoordinate;

            if (BoardUtils.isValidSquareCoordinate(destinationCoordinate)) {
                final Square destinationCoordinateSquare = board.getSquare(destinationCoordinate);
                if (isFirstcolumn(this.piecePosition, destinationCoordinate) ||
                        isSecondcolumn(this.piecePosition, destinationCoordinate) ||
                        isSeventhcolumn(this.piecePosition, destinationCoordinate) ||
                        isEighthcolumn(this.piecePosition, destinationCoordinate)) {
                    continue;
                }

                if (!destinationCoordinateSquare.isSquareOccupied()) {
                    legalMoves.add(new Move());
                } else {
                    final Piece pieceAtposition = destinationCoordinateSquare.getPiece();
                    final Color pieceColor = pieceAtposition.getColor();
                }
            }
        }
        return Collections.unmodifiableCollection(legalMoves);

    }

    private static boolean isFirstcolumn(final int currentPosition, final int offset) {
        return BoardUtils.FIRST_COLUMN[currentPosition]
                && ((offset == -17 || offset == -10 || offset == 6 || offset == 15));
    }

    private static boolean isSecondcolumn(final int currentPosition, final int offset) {
        return BoardUtils.SECOND_COLUMN[currentPosition]
                && (offset == -10 || offset == 6);
    }

    private static boolean isSeventhcolumn(final int currentPosition, final int offset) {
        return BoardUtils.SEVENTH_COLUMN[currentPosition]
                && (offset == 6 || offset == 10);
    }

    private static boolean isEighthcolumn(final int currentPosition, final int offset) {
        return BoardUtils.EIGHTH_COLUMN[currentPosition]
                && ((offset == 17 || offset == 10 || offset == -6 || offset == -15));
    }

}
