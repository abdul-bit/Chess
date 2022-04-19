package ChessGame.Pieces;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import ChessGame.Board.Board;
import ChessGame.Board.BoardUtils;
import ChessGame.Board.Move;
import ChessGame.Board.Square;
import ChessGame.Board.Move.*;

import java.util.ArrayList;

public class Queen extends Piece {
    private final static int[] POSSIBLE_MOVE_COORDINATES = { -9, -8, -7, -1, 1, 7, 8, 9 };

    public Queen(final Color pieceColor, final int piecePosition) {
        super(PieceType.QUEEN, piecePosition, pieceColor);
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

    @Override
    public String toString() {
        return PieceType.QUEEN.toString();
    }
}
