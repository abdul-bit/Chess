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

public class Rook extends Piece {

    private final static int[] CANDIDATE_MOVE_VECTOR_COORDINATES = { -8, -1, 1, 8 };

    public Rook(final Color pieceColor, final int piecePosition) {
        super(PieceType.ROOK, piecePosition, pieceColor, true);
    }

    public Rook(final Color pieceColor, final int piecePosition, final boolean isFirstMove) {
        super(PieceType.ROOK, piecePosition, pieceColor, isFirstMove);
    }

    @Override
    public Collection<Move> calculateLegalMoves(final Board board) {

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
                        legalMoves.add(new MajorMove(board, this, candidateDestinationCoordinate));
                    } else {
                        final Piece pieceAtDestination = candidateDestinationSquare.getPiece();
                        final Color pieceColor = pieceAtDestination.getPieceColor();
                        if (this.pieceColor != pieceColor) {
                            legalMoves.add(
                                    new AttackMove(board, this, candidateDestinationCoordinate, pieceAtDestination));
                        }
                        break;
                    }
                }
            }
        }

        return ImmutableList.copyOf(legalMoves);
    }

    @Override
    public Rook movePiece(final Move move) {
        return new Rook(move.getMovedPiece().getPieceColor(), move.getDestinationCoordinate());
    }

    @Override
    public String toString() {
        return PieceType.ROOK.toString();
    }

    private static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffset) {
        return BoardUtils.FIRST_COLUMN[currentPosition] && (candidateOffset == -1);
    }

    private static boolean isEighthColumnExclusion(final int currentPosition, final int candidateOffset) {
        return BoardUtils.EIGHTH_COLUMN[currentPosition] && (candidateOffset == 1);
    }
}
