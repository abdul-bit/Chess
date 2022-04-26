package ChessGame.Pieces;

import java.util.Collection;

import java.util.List;

import com.google.common.collect.ImmutableList;

import ChessGame.board.Board;
import ChessGame.board.BoardUtils;
import ChessGame.board.Move;
import ChessGame.board.Square;
import ChessGame.board.Move.*;

import java.util.ArrayList;

// the most integral piece in chess

public class King extends Piece {

    private final static int[] CANDIDATE_MOVE_COORDINATE = { -9, -8, -7, -1, 1, 7, 8, 9 };

    public King(final Color pieceColor, final int piecePosition) {
        super(PieceType.KING, piecePosition, pieceColor, true);
    }

    public King(final Color pieceColor, final int piecePosition, final boolean isFirstMove) {
        super(PieceType.KING, piecePosition, pieceColor, isFirstMove);
    }

    @Override
    public Collection<Move> calculateLegalMoves(Board board) {

        final List<Move> legalMoves = new ArrayList<>();

        for (final int currentCandidateOffset : CANDIDATE_MOVE_COORDINATE) {
            final int candidateDestinationCoordinate = this.piecePosition + currentCandidateOffset;

            if (isFirstColumnExclusion(this.piecePosition, currentCandidateOffset) ||
                    isEighthColumnExclusion(this.piecePosition, currentCandidateOffset)) {
                continue;
            }
            if (BoardUtils.isValidSquareCoordinate(candidateDestinationCoordinate)) {
                final Square candidateDestinationSquare = board.getSquare(candidateDestinationCoordinate);
                if (!candidateDestinationSquare.isSquareOccupied()) {
                    legalMoves.add(new MajorMove(board, this, candidateDestinationCoordinate));
                } else {
                    final Piece pieceAtDestination = candidateDestinationSquare.getPiece();
                    final Color pieceColor = pieceAtDestination.getPieceColor();
                    if (this.pieceColor != pieceColor) {
                        legalMoves.add(new AttackMove(board, this, candidateDestinationCoordinate, pieceAtDestination));
                    }
                }
            }
        }
        return ImmutableList.copyOf(legalMoves);
    }

    @Override
    public King movePiece(final Move move) {
        return new King(move.getMovedPiece().getPieceColor(), move.getDestinationCoordinate());
    }

    @Override
    public String toString() {
        return PieceType.KING.toString();
    }

    private static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffset) {
        return BoardUtils.FIRST_COLUMN[currentPosition] && (candidateOffset == -9 || (candidateOffset == -1) ||
                candidateOffset == 7);
    }

    private static boolean isEighthColumnExclusion(final int currentPosition, final int candidateOffset) {
        return BoardUtils.EIGHTH_COLUMN[currentPosition] && (candidateOffset == -7 || candidateOffset == 1 ||
                candidateOffset == 9);
    }
}