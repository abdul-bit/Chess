package ChessGame.Pieces;

import java.util.Collection;

import java.util.List;

import com.google.common.collect.ImmutableList;

import ChessGame.board.Board;
import ChessGame.board.BoardUtils;
import ChessGame.board.Move;

import java.util.ArrayList;

public class Pawn extends Piece {

    private final static int[] CANDIDATE_MOVE_COORDINATE = { 8, 16, 7, 9 };

    public Pawn(final Color pieceColor, final int piecePosition) {
        super(PieceType.PAWN, pieceColor, piecePosition, true);

    }

    public Pawn(final Color pieceColor, final int piecePosition, final boolean isFirstMove) {
        super(PieceType.PAWN, pieceColor, piecePosition, isFirstMove);
    }

    @Override
    public Collection<Move> calculateLegalMoves(final Board board) {

        final List<Move> legalMoves = new ArrayList<>();

        for (final int currentCandidateOffset : CANDIDATE_MOVE_COORDINATE) {

            final int candidateDestinationCoordinate = this.piecePosition
                    + (this.pieceColor.getDirection() * currentCandidateOffset);

            if (!BoardUtils.isValidSquareCoordinate(candidateDestinationCoordinate)) {
                continue;
            }
            if (currentCandidateOffset == 8 && !board.getSquare(candidateDestinationCoordinate).isSquareOccupied()) {
                if (this.pieceColor.isPawnPromotionSquare(candidateDestinationCoordinate)) {
                    legalMoves.add(
                            new Move.PawnPromotion(new Move.PawnMove(board, this, candidateDestinationCoordinate)));
                } else {
                    legalMoves.add(new Move.PawnMove(board, this, candidateDestinationCoordinate));
                }
            } else if (currentCandidateOffset == 16 && this.isFirstMove() &&
                    ((BoardUtils.SEVENTH_RANK[this.piecePosition] && this.getPieceColor().isBlack()) ||
                            (BoardUtils.SECOND_RANK[this.piecePosition] && this.getPieceColor().isWhite()))) {
                final int behindCandidateDestinationCoordinate = this.piecePosition
                        + (this.pieceColor.getDirection() * 8);
                if (!board.getSquare(behindCandidateDestinationCoordinate).isSquareOccupied() &&
                        !board.getSquare(candidateDestinationCoordinate).isSquareOccupied()) {
                    legalMoves.add(new Move.PawnJump(board, this, candidateDestinationCoordinate));

                }
            } else if (currentCandidateOffset == 7
                    && !((BoardUtils.EIGHTH_COLUMN[this.piecePosition] && this.pieceColor.isWhite())
                            || (BoardUtils.FIRST_COLUMN[this.piecePosition]
                                    && this.pieceColor.isBlack()))) {
                if (board.getSquare(candidateDestinationCoordinate).isSquareOccupied()) {
                    final Piece pieceOnCandidate = board.getSquare(candidateDestinationCoordinate).getPiece();
                    if (this.pieceColor != pieceOnCandidate.getPieceColor()) {
                        if (this.pieceColor.isPawnPromotionSquare(candidateDestinationCoordinate)) {
                            legalMoves.add(new Move.PawnPromotion(new Move.PawnAttackMove(board, this,
                                    candidateDestinationCoordinate, pieceOnCandidate)));
                        } else {
                            legalMoves.add(new Move.PawnAttackMove(board, this,
                                    candidateDestinationCoordinate, pieceOnCandidate));
                        }
                    }
                } else if (board.getEnPassantPawn() != null
                        && board.getEnPassantPawn().getPiecePosition() == (this.piecePosition
                                + (this.pieceColor.getOppositeDirection()))) {
                    final Piece pieceOnCandidate = board.getEnPassantPawn();
                    if (this.pieceColor != pieceOnCandidate.getPieceColor()) {
                        legalMoves.add(new Move.PawnEnPassantAttackMove(board, this, candidateDestinationCoordinate,
                                pieceOnCandidate));
                    }
                }

            } else if (currentCandidateOffset == 9 &&
                    !((BoardUtils.FIRST_COLUMN[this.piecePosition] && this.pieceColor.isWhite()) ||
                            (BoardUtils.EIGHTH_COLUMN[this.piecePosition]
                                    && this.pieceColor.isBlack()))) {
                if (board.getSquare(candidateDestinationCoordinate).isSquareOccupied()) {
                    final Piece pieceOnCandidate = board.getSquare(candidateDestinationCoordinate).getPiece();
                    if (this.pieceColor != pieceOnCandidate.getPieceColor()) {
                        if (this.pieceColor.isPawnPromotionSquare(candidateDestinationCoordinate)) {
                            legalMoves.add(new Move.PawnPromotion(
                                    new Move.PawnAttackMove(board, this, candidateDestinationCoordinate,
                                            pieceOnCandidate)));
                        } else {
                            legalMoves.add(new Move.PawnAttackMove(board, this, candidateDestinationCoordinate,
                                    pieceOnCandidate));
                        }
                    }
                } else if (board.getEnPassantPawn() != null && board.getEnPassantPawn()
                        .getPiecePosition() == (this.piecePosition - (this.pieceColor.getOppositeDirection()))) {
                    final Piece pieceOnCandidate = board.getEnPassantPawn();
                    if (this.pieceColor != pieceOnCandidate.getPieceColor()) {
                        legalMoves.add(new Move.PawnEnPassantAttackMove(board, this, candidateDestinationCoordinate,
                                pieceOnCandidate));
                    }
                }

            }
        }
        return ImmutableList.copyOf(legalMoves);
    }

    @Override
    public Pawn movePiece(final Move move) {
        // TODO Auto-generated method stub
        return new Pawn(move.getMovedPiece().getPieceColor(), move.getDestinationCoordinate());
    }

    @Override
    public String toString() {
        return Piece.PieceType.PAWN.toString();
    }

    public Piece getPromotedPiece() {
        return new Queen(this.pieceColor, this.piecePosition, false);
    }
}