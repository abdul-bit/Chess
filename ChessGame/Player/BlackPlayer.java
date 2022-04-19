package ChessGame.Player;

import java.util.Collection;

import ChessGame.Board.Board;
import ChessGame.Board.Move;
import ChessGame.Pieces.Color;
import ChessGame.Pieces.Piece;

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
}
