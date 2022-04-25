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

// the most integral piece in chess

public class King extends Piece {
    private final static int[] POSSIBLE_MOVE_COORDINATES = { -9, -8, -7, -1, 1, 7, 8, 9 };

    public King(final Color pieceColor, final int piecePosition) {
        super(PieceType.KING, piecePosition, pieceColor);
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

    @Override
    public String toString() {
        return PieceType.KING.toString();
    }

    @Override
    public King movePiece(final Move move) {
        return new King(move.getMovedPiece().getColor(), move.getDestinationCoordinate());
    }
}
