package jay.chess.gui;

import jay.chess.engine.ChessAlliance;
import jay.chess.engine.ChessEngine;
import jay.chess.engine.player.bot.RandomClassicBotPlayer;
import jay.chess.gui.board.ChessBoardJPanel;
import jay.chess.gui.player.ChessSwingClassicPlayer;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ChessJFrame extends JFrame   {

    private final ChessBoardJPanel m_boardPanel;
    private final ChessEngine m_engine;
    private final Timer m_displayingThread;
    private final Thread m_playingThread;
    private final ChessSwingClassicPlayer m_player = new ChessSwingClassicPlayer("test");

    public ChessJFrame() {
        m_engine = new ChessEngine();
        m_boardPanel = new ChessBoardJPanel(m_player,8, 8);
        add(m_boardPanel);
        resetEngine();
        m_playingThread = new Thread(this::periodic);
        m_displayingThread = new Timer(20,this::displayPeriodic);
        m_displayingThread.start();
        m_playingThread.start();
    }

    public void displayPeriodic(ActionEvent event) {
        m_boardPanel.syncBoard(m_engine.getBoard());
    }

    public void resetEngine() {
        m_engine.setPlayer(m_player, new RandomClassicBotPlayer("bot"));
        m_engine.reset();
        m_boardPanel.syncAlliance(m_player.getAlliance());
        m_boardPanel.syncBoard(m_engine.getBoard());
    }

    private void periodic() {
        while(true) {
            m_engine.play();
        }
    }
}
