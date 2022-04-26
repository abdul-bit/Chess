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

public class WhitePlayer extends Player {

    public WhitePlayer(final Board board, final Collection<Move> whiteStandardLegalMoves,
            final Collection<Move> blackStandardLegalMoves) {
        super(board, whiteStandardLegalMoves, blackStandardLegalMoves);

    }

    @Override
    public Collection<Piece> getActivePieces() {
        return this.board.getWhitePieces();
    }

    @Override
    public Color getColor() {
        // TODO Auto-generated method stub
        return Color.WHITE;
    }

    @Override
    public Player getOpponent() {
        // TODO Auto-generated method stub
        return this.board.blackPlayer();
    }

    @Override
    public String toString() {
        return Color.WHITE.toString();
    }

    @Override
    protected Collection<Move> calculateKingCastles(final Collection<Move> playerLegals,
            final Collection<Move> opponentsLegals) {

        final List<Move> kingCastles = new ArrayList<>();

        if (this.playerKing.isFirstMove() && !this.isInCheck()) {
            // White King Side Castle
            if (!this.board.getSquare(61).isSquareOccupied() && !this.board.getSquare(62).isSquareOccupied())
                ;
            final Square kingRookTile = this.board.getSquare(63);
            if (kingRookTile.isSquareOccupied() && kingRookTile.getPiece().isFirstMove()) {
                if (Player.calculateAttacksOnSquare(61, opponentsLegals).isEmpty()
                        && Player.calculateAttacksOnSquare(62, opponentsLegals).isEmpty()
                        && kingRookTile.getPiece().getPieceType().isRook()) {
                    kingCastles.add(new Move.KingSideCastleMove(this.board,
                            this.playerKing,
                            62,
                            (Rook) kingRookTile.getPiece(),
                            kingRookTile.getSquareCoordinate(),
                            61));
                }

            }
            if (!this.board.getSquare(59).isSquareOccupied() && !this.board.getSquare(58).isSquareOccupied()
                    && !this.board.getSquare(57).isSquareOccupied()) {
                final Square queenRookTile = this.board.getSquare(56);
                if (queenRookTile.isSquareOccupied() && queenRookTile.getPiece().isFirstMove()
                        && Player.calculateAttacksOnSquare(58, opponentsLegals).isEmpty()
                        && Player.calculateAttacksOnSquare(59, opponentsLegals).isEmpty()
                        && queenRookTile.getPiece().getPieceType().isRook()) {
                    kingCastles.add(new Move.QueenSideCastleMove(this.board, this.playerKing, 58,
                            (Rook) queenRookTile.getPiece(),
                            queenRookTile.getSquareCoordinate(), 59));
                }

            }

        }

        return Collections.unmodifiableList(kingCastles);
    }

}