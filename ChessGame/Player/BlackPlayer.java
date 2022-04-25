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

public class BlackPlayer extends Player {

    public BlackPlayer(final Board board,
            final Collection<Move> whiteStandardLegaMoves,
            final Collection<Move> blackStandardLegaMoves) {
        super(board, blackStandardLegaMoves, whiteStandardLegaMoves);
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
        final List<Move> KingCastles = new ArrayList<>();
        if (this.playerKing.isFirstMove() && !this.isInCheck()) {
            // Blacks king side castle
            if (!this.board.getSquare(5).isSquareOccupied() &&
                    !this.board.getSquare(6).isSquareOccupied()) {
                final Square rookSquare = this.board.getSquare(7);
                if (rookSquare.isSquareOccupied() && rookSquare.getPiece().isFirstMove()) {
                    if (Player.calculateAttacksOnSquare(5, opponentLegals).isEmpty() &&
                            Player.calculateAttacksOnSquare(6, opponentLegals).isEmpty() &&
                            rookSquare.getPiece().getPieceType().isRook()) {

                        KingCastles.add(new KingSideCastleMove(this.board, this.playerKing, 6,
                                (Rook) rookSquare.getPiece(), rookSquare.getSquareCoordinate(), 5));

                    }

                }
            }
            if (!this.board.getSquare(1).isSquareOccupied()
                    && !this.board.getSquare(2).isSquareOccupied()
                    && !this.board.getSquare(3).isSquareOccupied()) {
                final Square rookSquare = this.board.getSquare(0);
                if (rookSquare.isSquareOccupied() && rookSquare.getPiece().isFirstMove()) {

                    KingCastles.add(new QueenSideCastleMove(this.board, this.playerKing, 2,
                            (Rook) rookSquare.getPiece(), rookSquare.getSquareCoordinate(), 3));

                }
            }

        }

        return Collections.unmodifiableCollection(KingCastles);
    }

}
