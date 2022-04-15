package pieces;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import board.Board;
import board.Move;
import board.Square;

import java.util.ArrayList;
import board.BoardUtils;
import board.Move.*;

// the most integral piece in chess

public class King extends Piece {
    private final static int[] POSSIBLE_MOVE_COORDINATES = { -9, -8, -7, -1, 1, 7, 8, 9 };

    public King(final Color pieceColor, final int piecePosition) {
        super(piecePosition, pieceColor);
    }

    @Override
    public Collection<Move> CalculateLegalMoves(final Board board) {
        final List<Move> legalMoves = new ArrayList<>();
        for (final int currentCoordinate : POSSIBLE_MOVE_COORDINATES) {
            final int destinationCoordinate = this.piecePosition + currentCoordinate;

            if (isFirstcolumn(this.piecePosition, currentCoordinate)
                    || isEighthcolumn(this.piecePosition, currentCoordinate)) {
                continue;
            }

            if (BoardUtils.isValidSquareCoordinate(destinationCoordinate)) {
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

        return Collections.unmodifiableList(legalMoves);
    }

    private static boolean isFirstcolumn(final int currentPosition, final int offset) {
        return BoardUtils.FIRST_COLUMN[currentPosition]
                && ((offset == -9 || offset == -1 || offset == 7));
    }

    private static boolean isEighthcolumn(final int currentPosition, final int offset) {
        return BoardUtils.EIGHTH_COLUMN[currentPosition]
                && ((offset == -7 || offset == 1 || offset == 9));
    }

}
