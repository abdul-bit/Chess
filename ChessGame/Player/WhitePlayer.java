package ChessGame.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import ChessGame.Board.Board;
import ChessGame.Board.Move;
import ChessGame.Board.Square;
import ChessGame.Board.Move.KingSideCastleMove;
import ChessGame.Pieces.Color;
import ChessGame.Pieces.Piece;
import ChessGame.Pieces.Rook;
import ChessGame.Board.Move.QueenSideCastleMove;

public class WhitePlayer extends Player {

    public WhitePlayer(final Board board,
            final Collection<Move> whiteStandardLegaMoves,
            final Collection<Move> blackStandardLegaMoves) {
        super(board, blackStandardLegaMoves, whiteStandardLegaMoves);
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
        final List<Move> KingCastles = new ArrayList<>();
        if (this.playerKing.isFirstMove() && !this.isInCheck()) {
            // whites king side castle
            if (!this.board.getSquare(61).isSquareOccupied() &&
                    !this.board.getSquare(62).isSquareOccupied()) {
                final Square rookSquare = this.board.getSquare(63);
                if (rookSquare.isSquareOccupied() && rookSquare.getPiece().isFirstMove()) {
                    if (Player.calculateAttacksOnSquare(61, opponentLegals).isEmpty() &&
                            Player.calculateAttacksOnSquare(62, opponentLegals).isEmpty() &&
                            rookSquare.getPiece().getPieceType().isRook()) {

                        KingCastles.add(new KingSideCastleMove(this.board, this.playerKing, 62,
                                (Rook) rookSquare.getPiece(), rookSquare.getSquareCoordinate(), 61));

                    }

                }
            }
            if (!this.board.getSquare(59).isSquareOccupied()
                    && !this.board.getSquare(58).isSquareOccupied()
                    && !this.board.getSquare(57).isSquareOccupied()) {
                final Square rookSquare = this.board.getSquare(56);
                if (rookSquare.isSquareOccupied() && rookSquare.getPiece().isFirstMove()) {

                    KingCastles.add(new QueenSideCastleMove(this.board, this.playerKing, 58,
                            (Rook) rookSquare.getPiece(), rookSquare.getSquareCoordinate(), 59));

                }
            }

        }

        return Collections.unmodifiableCollection(KingCastles);
    }

}
