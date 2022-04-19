package ChessGame.Pieces;

import ChessGame.Player.BlackPlayer;
import ChessGame.Player.Player;
import ChessGame.Player.WhitePlayer;

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
        public Player choosePlayer(BlackPlayer blackPlayer, WhitePlayer whitePlayer) {
            return whitePlayer;
        }

    },
    BLACK {
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
        public Player choosePlayer(final BlackPlayer blackPlayer,
                final WhitePlayer whitePlayer) {
            return blackPlayer;// polymorphic trick
        }
    };

    public abstract int getDirection();

    public abstract boolean isWhite();

    public abstract boolean isBlack();

    public abstract Player choosePlayer(BlackPlayer blackPlayer, WhitePlayer whitePlayer);
}
