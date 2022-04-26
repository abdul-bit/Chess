package ChessGame.Gui;

import javax.imageio.ImageIO;
import javax.swing.*;

import ChessGame.board.Board;
import ChessGame.board.BoardUtils;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Table {

    private final JFrame gameFrame;
    private final BoardPanel boardPanel;
    private final Board chessBoard;
    String defaultPieceImagesPath = "art/fancy";
    private final Color lightTileColor = Color.decode("#eeeed2");
    private final Color darkTileColor = Color.decode("#769655");
    private final static Dimension OUTER_FRAME_DIMENSION = new Dimension(600, 600);
    private static final Dimension BOARD_PANEL_DIMENSION = new Dimension(400, 350);
    private static final Dimension TILE_PANEL_DIMENSION = new Dimension(10, 10);;

    public Table() {
        this.gameFrame = new JFrame("Turbo Chess");
        this.gameFrame.setLayout(new BorderLayout());
        final JMenuBar tableMenuBar = createTableMenuBar();
        this.chessBoard = Board.createStandardBoard();// here is where the board is created
        this.gameFrame.setJMenuBar(tableMenuBar);
        this.gameFrame.setSize(OUTER_FRAME_DIMENSION);
        this.gameFrame.setVisible(true);
        this.boardPanel = new BoardPanel();
        this.gameFrame.add(this.boardPanel, BorderLayout.CENTER);
    }

    private JMenuBar createTableMenuBar() {
        final JMenuBar tableMenuBar = new JMenuBar();
        tableMenuBar.add(createFileMenu());
        return tableMenuBar;

    }

    private JMenu createFileMenu() {
        final JMenu fileMenu = new JMenu("File");
        final JMenuItem openPGN = new JMenuItem("Load PGN File");
        openPGN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Open up dat PGN File!!!");
                System.out.println("Open up dat PGN File!!!");

            }
        });
        fileMenu.add(openPGN);

        final JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        fileMenu.add(exitMenuItem);

        return fileMenu;
    }

    private class BoardPanel extends JPanel {

        final List<SquarePanel> boardSquares;

        BoardPanel() {
            super(new GridLayout(8, 8));
            this.boardSquares = new ArrayList<>();
            for (int i = 0; i < BoardUtils.NUM_SQUARES; i++) {
                final SquarePanel squarePanel = new SquarePanel(this, i);
                this.boardSquares.add(squarePanel);
                add(squarePanel);
            }
            setPreferredSize(BOARD_PANEL_DIMENSION);
            validate();
        }

        // public void drawBoard(final Board board) {
        // removeAll();
        // for (final TilePanel tilePanel : boardDirection.traverse(boardTiles)) {
        // tilePanel.drawTile(board);
        // add(tilePanel);
        // }
        // validate();
        // repaint();
        // }
    }

    private class SquarePanel extends JPanel {

        private final int squareID;

        SquarePanel(final BoardPanel boardPanel,
                final int squareID) {
            super(new GridBagLayout());
            this.squareID = squareID;
            setPreferredSize(TILE_PANEL_DIMENSION);
            assignSquareColor();
            assignSquarePieceIcon(chessBoard);

            // addMouseListener(new MouseListener() {
            // @Override
            // public void mouseClicked(final MouseEvent e) {

            // if(isRightMouseButton(e)) {

            // sourceTile = null;
            // destinationTile = null;
            // humanMovedPiece = null;

            // } else if (isLeftMouseButton(e)) {
            // if(sourceTile == null) {
            // sourceTile = chessBoard.getTile(tileID);
            // humanMovedPiece = sourceTile.getPiece();
            // if (humanMovedPiece == null) {
            // sourceTile = null;
            // }
            // } else {
            // destinationTile = chessBoard.getTile(tileID);
            // final Move move = Move.MoveFactory.createMove(chessBoard,
            // sourceTile.getTileCoordinate(), destinationTile.getTileCoordinate());
            // final MoveTransition transition = chessBoard.currentPlayer().makeMove(move);
            // if(transition.getMoveStatus().isDone()) {
            // chessBoard = transition.getTransitionBoard();
            // moveLog.addMove(move);
            // //TODO add the move that was made to the move log
            // }
            // sourceTile = null;
            // destinationTile = null;
            // humanMovedPiece = null;
            // }
            // SwingUtilities.invokeLater(new Runnable() {
            // @Override
            // public void run() {
            // gameHistoryPanel.redo(chessBoard, moveLog);
            // takenPiecesPanel.redo(moveLog);
            // boardPanel.drawBoard(chessBoard);
            // }
            // });
            // }
            // }

            // @Override
            // public void mousePressed(final MouseEvent e) {

            // }

            // @Override
            // public void mouseReleased(final MouseEvent e) {

            // }

            // @Override
            // public void mouseEntered(final MouseEvent e) {

            // }

            // @Override
            // public void mouseExited(final MouseEvent e) {

            // }
            // });

            validate();
        }

        private void assignSquarePieceIcon(final Board board) {
            this.removeAll();
            if (board.getSquare(this.squareID).isSquareOccupied()) {
                System.out.println(defaultPieceImagesPath
                        + board.getSquare(this.squareID).getPiece().getPieceColor().toString().substring(0, 1) +
                        board.getSquare(this.squareID).getPiece().toString() + ".jpg");
                try {

                    final BufferedImage image = ImageIO.read(new File(defaultPieceImagesPath
                            + board.getSquare(this.squareID).getPiece().getPieceColor().toString().substring(0, 1) +
                            board.getSquare(this.squareID).getPiece().toString() + ".jpg"));
                    add(new JLabel(new ImageIcon(image)));

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private void assignSquareColor() {
            if (BoardUtils.EIGHTH_RANK[this.squareID] ||
                    BoardUtils.SIXTH_RANK[this.squareID] ||
                    BoardUtils.FOURTH_RANK[this.squareID] ||
                    BoardUtils.SECOND_RANK[this.squareID]) {
                setBackground(this.squareID % 2 == 0 ? lightTileColor : darkTileColor);
            } else if (BoardUtils.SEVENTH_RANK[this.squareID] ||
                    BoardUtils.FIFTH_RANK[this.squareID] ||
                    BoardUtils.THIRD_RANK[this.squareID] ||
                    BoardUtils.FIRST_RANK[this.squareID]) {
                setBackground(this.squareID % 2 != 0 ? lightTileColor : darkTileColor);
            }
        }

    }
}