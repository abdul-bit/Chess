package ChessGame.Player;

import java.util.Collection;

import ChessGame.Board.Board;
import ChessGame.Board.Move;
import ChessGame.Pieces.Color;
import ChessGame.Pieces.Piece;

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

}
