package jay.chess.gui.player;

import jay.chess.engine.ChessMovementInfo;
import jay.chess.engine.ChessTile;
import jay.chess.engine.player.Player;
import jay.util.Board2d;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

public class ChessSwingPlayer extends Player implements PropertyChangeListener {

    public static final String PROPERTY_DESIRE_MOVE = "Move";

    private AtomicBoolean m_readyToPlay = new AtomicBoolean(false);
    private Optional<ChessMovementInfo> m_infoOptional = Optional.empty();

    public ChessSwingPlayer(String name) {
        super(name);
    }

    @Override
    public void reset() {
        m_readyToPlay.set(false);
        m_infoOptional = Optional.empty();
    }

    @Override
    public ChessMovementInfo play(Board2d<ChessTile> board) {
        while(!m_readyToPlay.get());
        if(m_infoOptional.isPresent()) {
            ChessMovementInfo infoToReturn = m_infoOptional.get();
            reset();
            System.out.println("returned value");
            return infoToReturn;
        }
        else {
            throw new RuntimeException("Error: Info is empty but found ready to play");
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("PROPERTY CHANGED " + evt.getPropertyName());
        if(evt.getPropertyName().compareToIgnoreCase(PROPERTY_DESIRE_MOVE) == 0) {
            if(evt.getNewValue() instanceof ChessMovementInfo info) {
                m_infoOptional = Optional.of(info);
                m_readyToPlay.set(true);
            }
        }
    }
}
