package pieces;

import java.util.Collection;
import java.util.Collections;
import board.Board;
import board.Move;
import board.Square;
import java.util.ArrayList;
import board.BoardUtils;
import board.Move.*;

public class Bishop extends Piece {
    private final static int[] POSSIBLE_MOVE_COORDINATES = { -9, -7, 7, 9 };

    Bishop(final int piecePosition, final Color pieceColor) {
        super(piecePosition, pieceColor);
    }

    @Override
    public Collection<Move> CalculateLegalMoves(final Board board) {

        final List<Move> legalMoves = new ArrayList<>();
        for (final int currentCoordinate : POSSIBLE_MOVE_COORDINATES) {
            int destinationCoordinate = this.piecePosition;

        }

    }
}
