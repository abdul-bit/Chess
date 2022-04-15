package pieces;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import board.Board;
import board.Move;
import java.util.ArrayList;
import board.BoardUtils;
import board.Move.*;

public class Pawn extends Piece {
    private final static int[] POSSIBLE_MOVE_COORDINATES = { 8, 16, 7, 9 };

    public Pawn(final Color pieceColor, final int piecePosition) {
        super(piecePosition, pieceColor);
    }

    @Override
    public Collection<Move> CalculateLegalMoves(final Board board) {
        final List<Move> legalMoves = new ArrayList<>();
        for (final int currentCoordinate : POSSIBLE_MOVE_COORDINATES) {
            int destinationCoordinate = this.piecePosition + (this.getColor().getDirection() * currentCoordinate);

            if (!BoardUtils.isValidSquareCoordinate((destinationCoordinate))) {

                continue;

            }
            if (currentCoordinate == 8 && !board.getSquare(destinationCoordinate).isSquareOccupied()) {
                // todo
                legalMoves.add(new MajorMove(board, this, destinationCoordinate));
            } else if (currentCoordinate == 16 && this.isFirstMove()
                    && (BoardUtils.SECOND_ROW[this.piecePosition] && this.getColor().isBlack())
                    || (BoardUtils.SEVENTH_ROW[this.piecePosition] && this.getColor().isWhite())) {
                final int behindDestinationCoordinate = this.piecePosition
                        + (this.getColor().getDirection() * 8);
                if (!board.getSquare(behindDestinationCoordinate).isSquareOccupied()
                        && !board.getSquare(destinationCoordinate).isSquareOccupied()) {
                    legalMoves.add(new MajorMove(board, this, destinationCoordinate));
                }

            } else if (currentCoordinate == 7 && !((BoardUtils.EIGHTH_COLUMN[this.piecePosition]
                    && this.getColor().isWhite())
                    || (BoardUtils.FIRST_COLUMN[this.piecePosition]
                            && this.getColor().isBlack()))) {
                if (board.getSquare(destinationCoordinate).isSquareOccupied()) {
                    final Piece pieceOnCandidate = board.getSquare(destinationCoordinate).getPiece();
                    if (this.pieceColor != pieceOnCandidate.getColor()) {
                        // todo move
                        legalMoves.add(new MajorMove(board, this, destinationCoordinate));
                    }
                }

            } else if (currentCoordinate == 9 && !((BoardUtils.FIRST_COLUMN[this.piecePosition]
                    && this.getColor().isWhite())
                    || (BoardUtils.EIGHTH_COLUMN[this.piecePosition]
                            && this.getColor().isBlack()))) {
                // todo move
                legalMoves.add(new MajorMove(board, this, destinationCoordinate));
            }

        }
        return Collections.unmodifiableList(legalMoves);
    }

}
