package jay.chess.gui;

import jay.chess.engine.ChessAlliance;
import jay.chess.engine.ChessEngine;
import jay.chess.engine.ChessMovementInfo;
import jay.chess.engine.player.bot.RandomBotPlayer;
import jay.chess.gui.board.ChessBoardJPanel;
import jay.chess.gui.player.ChessSwingPlayer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ChessJFrame extends JFrame   {

    private final ChessBoardJPanel boardPanel;
    private final ChessEngine engine;
    private final Timer displayingThread;
    private final Thread playingThread;
    private final ChessSwingPlayer player = new ChessSwingPlayer("test");

    public ChessJFrame() {
        engine = new ChessEngine();
        resetEngine();
        boardPanel = new ChessBoardJPanel(player,8, 8);
        add(boardPanel);
        boardPanel.syncBoard(engine.getBoard());
        boardPanel.syncAlliance(ChessAlliance.WHITE);
        playingThread = new Thread(this::periodic);
        displayingThread = new Timer(20,this::displayPeriodic);
        displayingThread.start();
        playingThread.start();
    }

    public void displayPeriodic(ActionEvent event) {
        boardPanel.syncBoard(engine.getBoard());
    }

    public void resetEngine() {
        engine.setPlayer(player, new RandomBotPlayer("bot"));
        engine.reset();
    }

    private void periodic() {
        while(true) {
            engine.play();
        }
    }
}
