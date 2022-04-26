package ChessGame.Pieces;

import ChessGame.Player.BlackPlayer;
import ChessGame.Player.Player;
import ChessGame.Player.WhitePlayer;
import ChessGame.board.BoardUtils;

public enum Color {
    WHITE {

        @Override
        public int getDirection() {
            return -1;
        }

        @Override
        public boolean isWhite() {
            return true;
        }

        @Override
        public boolean isBlack() {
            return false;
        }

        @Override
        public Player choosePlayer(final WhitePlayer whitePlayer, final BlackPlayer blackPlayer) {
            // TODO Auto-generated method stub
            return whitePlayer;
        }

        @Override
        public int getOppositeDirection() {
            // TODO Auto-generated method stub
            return 1;
        }

        @Override
        public boolean isPawnPromotionSquare(int position) {
            // TODO Auto-generated method stub
            return BoardUtils.EIGHTH_RANK[position];
        }

    },
    BLACK

    {
        @Override
        public int getDirection() {
            return 1;
        }

        @Override
        public boolean isWhite() {
            return false;
        }

        @Override
        public boolean isBlack() {
            return true;
        }

        @Override
        public Player choosePlayer(final WhitePlayer whitePlayer, final BlackPlayer blackPlayer) {
            // TODO Auto-generated method stub
            return blackPlayer;
        }

        @Override
        public int getOppositeDirection() {
            // TODO Auto-generated method stub
            return -1;
        }

        @Override
        public boolean isPawnPromotionSquare(int position) {
            // TODO Auto-generated method stub
            return BoardUtils.FIRST_RANK[position];
        }
    };

    public abstract int getDirection();

    public abstract int getOppositeDirection();

    public abstract boolean isWhite();

    public abstract boolean isBlack();

    public abstract boolean isPawnPromotionSquare(int position);

    public abstract Player choosePlayer(WhitePlayer whitePlayer, BlackPlayer blackPlayer);
}
