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

public class WhitePlayer extends Player {
    public WhitePlayer(final Board board,
            final Collection<Move> whiteStandardLegalMoves,
            final Collection<Move> blackStandardLegalMoves) {
        super(board, whiteStandardLegalMoves, blackStandardLegalMoves);
    }

    @Override
    public Collection<Piece> getActivePieces() {
        return this.board.getWhitePieces();
    }

    @Override
    public Color getColor() {
        return Color.WHITE;
    }

    @Override
    public Player getOpponent() {
        return this.board.blackPlayer();
    }

    @Override
    protected Collection<Move> calculateKingCastles(final Collection<Move> playerLegals,
            final Collection<Move> opponentLegals) {

        final List<Move> kingCastles = new ArrayList<>();

        if (this.playerKing.isFirstMove() && !this.isInCheck()) {
            if (!this.board.getSquare(61).isSquareOccupied() && !this.board.getSquare(62).isSquareOccupied()) {
                final Square rookTile = this.board.getSquare(63);

                if (rookTile.isSquareOccupied() && rookTile.getPiece().isFirstMove()) {
                    if (Player.calculateAttacksOnSquare(61, opponentLegals).isEmpty() &&
                            Player.calculateAttacksOnSquare(62, opponentLegals).isEmpty() &&
                            rookTile.getPiece().getPieceType().isRook()) {
                        kingCastles.add(new KingSideCastleMove(this.board,
                                this.playerKing,
                                62,
                                (Rook) rookTile.getPiece(),
                                rookTile.getSquareCoordinate(),
                                61));
                    }
                }
            }
            if (!this.board.getSquare(59).isSquareOccupied() &&
                    !this.board.getSquare(58).isSquareOccupied() &&
                    !this.board.getSquare(57).isSquareOccupied()) {
                final Square rookTile = this.board.getSquare(56);
                if (rookTile.isSquareOccupied() && rookTile.getPiece().isFirstMove() &&
                        Player.calculateAttacksOnSquare(58, opponentLegals).isEmpty() &&
                        Player.calculateAttacksOnSquare(59, opponentLegals).isEmpty() &&
                        rookTile.getPiece().getPieceType().isRook()) {
                    kingCastles.add(new QueenSideCastleMove(this.board,
                            this.playerKing,
                            58,
                            (Rook) rookTile.getPiece(),
                            rookTile.getSquareCoordinate(),
                            59));
                }
            }
        }
        return ImmutableList.copyOf(kingCastles);
    }
}
