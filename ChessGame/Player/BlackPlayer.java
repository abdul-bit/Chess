package ChessGame.Player;

import java.util.ArrayList;
import java.util.Collection;

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
    public BlackPlayer(final Board board,
            final Collection<Move> whiteStandardLegalMoves,
            final Collection<Move> blackStandardLegalMoves) {
        super(board, blackStandardLegalMoves, whiteStandardLegalMoves);
    }

    @Override
    public Collection<Piece> getActivePieces() {
        return this.board.getBlackPieces();
    }

    @Override
    public Color getColor() {
        return Color.BLACK;
    }

    @Override
    public Player getOpponent() {
        return this.board.whitePlayer();
    }

    @Override
    protected Collection<Move> calculateKingCastles(final Collection<Move> playerLegals,
            final Collection<Move> opponentLegals) {

        final List<Move> kingCastles = new ArrayList<>();

        if (this.playerKing.isFirstMove() && !this.isInCheck()) {
            if (!this.board.getSquare(5).isSquareOccupied() && !this.board.getSquare(6).isSquareOccupied()) {
                final Square rookTile = this.board.getSquare(7);

                if (rookTile.isSquareOccupied() && rookTile.getPiece().isFirstMove()) {
                    if (Player.calculateAttacksOnSquare(5, opponentLegals).isEmpty() &&
                            Player.calculateAttacksOnSquare(6, opponentLegals).isEmpty() &&
                            rookTile.getPiece().getPieceType().isRook()) {
                        kingCastles.add(new KingSideCastleMove(this.board,
                                this.playerKing,
                                6,
                                (Rook) rookTile.getPiece(),
                                rookTile.getSquareCoordinate(),
                                5));
                    }
                }
            }
            if (!this.board.getSquare(1).isSquareOccupied() &&
                    !this.board.getSquare(2).isSquareOccupied() &&
                    !this.board.getSquare(3).isSquareOccupied()) {
                final Square rookTile = this.board.getSquare(0);
                if (rookTile.isSquareOccupied() && rookTile.getPiece().isFirstMove() &&
                        Player.calculateAttacksOnSquare(2, opponentLegals).isEmpty() &&
                        Player.calculateAttacksOnSquare(3, opponentLegals).isEmpty() &&
                        rookTile.getPiece().getPieceType().isRook()) {
                    kingCastles.add(new QueenSideCastleMove(this.board,
                            this.playerKing,
                            2,
                            (Rook) rookTile.getPiece(),
                            rookTile.getSquareCoordinate(),
                            3));
                }
            }
        }
        return ImmutableList.copyOf(kingCastles);
    }
}