package jay.chess.gui.board;

import jay.chess.engine.ChessAlliance;
import jay.chess.engine.ChessTile;
import jay.util.Board2d;
import jay.util.math.geom.Translation2d;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeListener;
import java.util.Optional;

public class ChessBoardJPanel extends JPanel  {

    private final GridLayout layout;
    private final ChessTileActionListener tileListener;

    private Color getButtonColor(int x, int y) {
        if(y % 2 == 0) {
            if(x % 2 == 0) {
                return new Color(0, 100, 0);
            }
        }
        else if(x % 2 != 0) {
            return new Color(0, 100, 0);
        }
        return Color.WHITE;
    }

    private void initBoard(Board2d<ChessTileJButton> board, int width, int height) {
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                Translation2d translation = new Translation2d(x, height - y - 1);
                ChessTileJButton next = new ChessTileJButton(translation);
                next.addActionListener(tileListener);
                Color colorToSet = getButtonColor(x, y);
                next.setBackground(colorToSet);
                add(next);
                next.setOpaque(true);
                board.set(translation, next);
            }
        }
    }

    public ChessBoardJPanel(PropertyChangeListener propertyChangeListener, int width, int height) {
        layout = new GridLayout(width, height);
        setLayout(layout);
        Board2d<ChessTileJButton> board = new Board2d<>(width, height);
        tileListener = new ChessTileActionListener(propertyChangeListener, board);
        initBoard(board, width, height);
    }

    public void syncBoard(Board2d<ChessTile> board) {
        tileListener.syncBoard(board);
    }

    public void syncAlliance(ChessAlliance alliance) {
        tileListener.syncAlliance(alliance);
    }

}
