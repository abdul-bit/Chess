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

public class Queen extends Piece {
    private final static int[] POSSIBLE_MOVE_COORDINATES = { -9, -8, -7, -1, 1, 7, 8, 9 };

    Queen(final int piecePosition, final Color pieceColor) {
        super(piecePosition, pieceColor);
    }

    @Override
    public Collection<Move> CalculateLegalMoves(final Board board) {

        final List<Move> legalMoves = new ArrayList<>();
        for (final int currentCoordinate : POSSIBLE_MOVE_COORDINATES) {
            int destinationCoordinate = this.piecePosition;
            while (BoardUtils.isValidSquareCoordinate((destinationCoordinate))) {
                if (isFirstColumn(currentCoordinate, destinationCoordinate) ||
                        isEighthColumn(currentCoordinate, destinationCoordinate)) {
                    break;
                }
                destinationCoordinate += currentCoordinate;
                if (BoardUtils.isValidSquareCoordinate((destinationCoordinate))) {

                    final Square destinationCoordinateSquare = board.getSquare(destinationCoordinate);
                    if (!destinationCoordinateSquare.isSquareOccupied()) {
                        legalMoves.add(new MajorMove(board, this, destinationCoordinate));
                    } else {
                        final Piece pieceAtposition = destinationCoordinateSquare.getPiece();
                        final Color pieceColor = pieceAtposition.getColor();
                        if (this.pieceColor != pieceColor) {
                            legalMoves.add(new AttackMove(board, this, destinationCoordinate, pieceAtposition));
                        }
                        break;
                    }

                }

            }

        }
        return Collections.unmodifiableList(legalMoves);
    }

    private static boolean isFirstColumn(final int currentCandidate,
            final int candidateDestinationCoordinate) {
        return (BoardUtils.FIRST_COLUMN[candidateDestinationCoordinate] &&
                ((currentCandidate == -1) || (currentCandidate == -9) || (currentCandidate == 7)));
    }

    private static boolean isEighthColumn(final int currentCandidate,
            final int candidateDestinationCoordinate) {
        return BoardUtils.EIGHTH_COLUMN[candidateDestinationCoordinate] &&
                ((currentCandidate == 1) || (currentCandidate == -7) || (currentCandidate == 9));
    }
}
