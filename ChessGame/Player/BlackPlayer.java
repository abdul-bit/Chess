package ChessGame.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.google.common.collect.ImmutableList;

import ChessGame.Pieces.Color;
import ChessGame.Pieces.Piece;
import ChessGame.Pieces.Rook;
import ChessGame.board.Board;
import ChessGame.board.Move;
import ChessGame.board.Square;
import ChessGame.board.Move.KingSideCastleMove;
import ChessGame.board.Move.QueenSideCastleMove;

public class BlackPlayer extends Player {

    public BlackPlayer(final Board board, final Collection<Move> whiteStandardLegalMoves,
            final Collection<Move> blackStandardLegalMoves) {
        super(board, blackStandardLegalMoves, whiteStandardLegalMoves);

    }

    @Override
    public Collection<Piece> getActivePieces() {
        return this.board.getBlackPieces();
    }

    @Override
    public Color getColor() {
        // TODO Auto-generated method stub
        return Color.BLACK;
    }

    @Override
    public Player getOpponent() {
        // TODO Auto-generated method stub
        return this.board.whitePlayer();
    }

    @Override
    public String toString() {
        return Color.BLACK.toString();
    }

    @Override
    protected Collection<Move> calculateKingCastles(final Collection<Move> playerLegals,
            final Collection<Move> opponentsLegals) {
        final List<Move> kingCastles = new ArrayList<>();

        if (this.playerKing.isFirstMove() && !this.isInCheck()) {
            // Black King Side Castle
            if (!this.board.getSquare(5).isSquareOccupied() && !this.board.getSquare(6).isSquareOccupied())
                ;
            final Square kingRookTile = this.board.getSquare(7);
            if (kingRookTile.isSquareOccupied() && kingRookTile.getPiece().isFirstMove()) {
                if (Player.calculateAttacksOnSquare(5, opponentsLegals).isEmpty()
                        && Player.calculateAttacksOnSquare(6, opponentsLegals).isEmpty()
                        && kingRookTile.getPiece().getPieceType().isRook()) {
                    kingCastles.add(new Move.KingSideCastleMove(this.board, this.playerKing, 6,
                            (Rook) kingRookTile.getPiece(), kingRookTile.getSquareCoordinate(), 5));
                }

            }
            if (!this.board.getSquare(1).isSquareOccupied() && !this.board.getSquare(2).isSquareOccupied()
                    && !this.board.getSquare(3).isSquareOccupied()) {
                final Square queenRookTile = this.board.getSquare(0);
                if (queenRookTile.isSquareOccupied() && queenRookTile.getPiece().isFirstMove() &&
                        Player.calculateAttacksOnSquare(2, opponentsLegals).isEmpty()
                        && Player.calculateAttacksOnSquare(3, opponentsLegals).isEmpty() &&
                        queenRookTile.getPiece().getPieceType().isRook()) {
                    kingCastles.add(new Move.QueenSideCastleMove(this.board, this.playerKing, 2,
                            (Rook) queenRookTile.getPiece(), queenRookTile.getSquareCoordinate(), 3));
                }

            }

        }

        return Collections.unmodifiableList(kingCastles);
    }

}
