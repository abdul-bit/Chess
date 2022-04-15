package pieces;

import java.util.Collection;
import java.util.Collections;
import board.Board;
import board.Move;
import board.Square;
import java.util.ArrayList;
import board.BoardUtils;
import board.Move.*;
import java.util.List;

public class Knight extends Piece {
    private final static int[] POSSIBLE_MOVE_COORDINATES = { -17, -15, -10, -6, 6, 10, 15, 17 };

    Knight(final int piecePosition, final Color pieceColor) {
        super(piecePosition, pieceColor);
    }

    @Override
    public Collection<Move> CalculateLegalMoves(final Board board) {

        final List<Move> legalMoves = new ArrayList<>();
        for (final int currentCoordinate : POSSIBLE_MOVE_COORDINATES) {
            final int destinationCoordinate = this.piecePosition + currentCoordinate;

            if (BoardUtils.isValidSquareCoordinate(destinationCoordinate)) {
                if (isFirstcolumn(this.piecePosition, currentCoordinate) ||
                        isSecondcolumn(this.piecePosition, currentCoordinate) ||
                        isSeventhcolumn(this.piecePosition, currentCoordinate) ||
                        isEighthcolumn(this.piecePosition, currentCoordinate)) {
                    continue;
                }
                final Square destinationCoordinateSquare = board.getSquare(destinationCoordinate);
                if (!destinationCoordinateSquare.isSquareOccupied()) {
                    legalMoves.add(new MajorMove(board, this, destinationCoordinate));
                } else {
                    final Piece pieceAtposition = destinationCoordinateSquare.getPiece();
                    final Color pieceColor = pieceAtposition.getColor();
                    if (this.pieceColor != pieceColor) {
                        legalMoves.add(new AttackMove(board, this, destinationCoordinate, pieceAtposition));
                    }
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
