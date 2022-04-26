package ChessGame.Pieces;

import java.util.Collection;

import java.util.List;

import com.google.common.collect.ImmutableList;

import ChessGame.board.Board;
import ChessGame.board.BoardUtils;
import ChessGame.board.Move;
import ChessGame.board.Square;

import java.util.ArrayList;

public class Queen extends Piece {

    private final static int[] CANDIDATE_MOVE_VECTOR_COORDINATES = { -9, -8, -7, -1, 1, 7, 8, 9 };

    public Queen(final Color pieceColor, final int piecePosition) {
        super(PieceType.QUEEN, piecePosition, pieceColor, true);
    }

    public Queen(final Color pieceColor, final int piecePosition, final boolean isFirstMove) {
        super(PieceType.QUEEN, piecePosition, pieceColor, isFirstMove);
    }

    @Override
    public Collection<Move> calculateLegalMoves(Board board) {

        final List<Move> legalMoves = new ArrayList<>();

        for (final int candidateCoordinateOffset : CANDIDATE_MOVE_VECTOR_COORDINATES) {
            int candidateDestinationCoordinate = this.piecePosition;
            while (BoardUtils.isValidSquareCoordinate(candidateDestinationCoordinate)) {
                if (isFirstColumnExclusion(candidateDestinationCoordinate, candidateCoordinateOffset) ||
                        isEighthColumnExclusion(candidateDestinationCoordinate, candidateCoordinateOffset)) {
                    break;
                }
                candidateDestinationCoordinate += candidateCoordinateOffset;
                if (BoardUtils.isValidSquareCoordinate(candidateDestinationCoordinate)) {
                    final Square candidateDestinationSquare = board.getSquare(candidateDestinationCoordinate);
                    if (!candidateDestinationSquare.isSquareOccupied()) {
                        legalMoves.add(new Move.MajorMove(board, this, candidateDestinationCoordinate));
                    } else {
                        final Piece pieceAtDestination = candidateDestinationSquare.getPiece();
                        final Color pieceColor = pieceAtDestination.getPieceColor();
                        if (this.pieceColor != pieceColor) {
                            legalMoves.add(new Move.AttackMove(board, this, candidateDestinationCoordinate,
                                    pieceAtDestination));
                        }
                        break;
                    }
                }
            }
        }

        return ImmutableList.copyOf(legalMoves);
    }

    @Override
    public Queen movePiece(final Move move) {
        return new Queen(move.getMovedPiece().getPieceColor(), move.getDestinationCoordinate());
    }

    @Override
    public String toString() {
        return PieceType.QUEEN.toString();
    }

    private static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffset) {
        return BoardUtils.FIRST_COLUMN[currentPosition]
                && (candidateOffset == -9 || candidateOffset == -1 || candidateOffset == 7);
    }

    private static boolean isEighthColumnExclusion(final int currentPosition, final int candidateOffset) {
        return BoardUtils.EIGHTH_COLUMN[currentPosition]
                && (candidateOffset == -7 || candidateOffset == 1 || candidateOffset == 9);
    }
}
