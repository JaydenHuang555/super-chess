package jay.chess.gui;

import jay.chess.engine.ChessEngine;
import jay.chess.engine.ChessMovementInfo;
import jay.chess.gui.board.ChessBoardJPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ChessJFrame extends JFrame implements PropertyChangeListener  {

    private final ChessBoardJPanel boardPanel;
    private final ChessEngine engine;
    private final Timer playingThread;

    public ChessJFrame() {
        engine = new ChessEngine();
        engine.reset();
        boardPanel = new ChessBoardJPanel(this, 8, 8);
        add(boardPanel);
        boardPanel.syncBoard(engine.getBoard());
        playingThread = new Timer(20, this::periodic);
        playingThread.start();
    }

    private void periodic(ActionEvent actionEvent) {
        boardPanel.syncAlliance(engine.getCurrentPlayingAlliance());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(evt.getPropertyName().compareToIgnoreCase("Move") == 0) {
//            System.out.println("Property " + evt.getPropertyName() + " Changed");
            if(evt.getNewValue() instanceof ChessMovementInfo info) {
                System.out.println("Attacking");
                boolean ableToMove = engine.move(info.attacker, info.defender);
                System.out.println("Last able to move " + ableToMove);
                if(ableToMove) {
                    boardPanel.syncBoard(engine.getBoard());
                }
            }
        }
    }
}
