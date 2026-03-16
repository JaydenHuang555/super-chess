package jay.chess.engine;

import jay.chess.engine.event.ChessEvent;
import jay.chess.engine.event.ChessEventInvoker;
import jay.chess.engine.event.PieceKilledChessEvent;
import jay.chess.engine.event.PieceMoveChessEvent;
import jay.chess.engine.listener.ChessEventListener;
import jay.chess.engine.piece.ChessPiece;
import jay.util.Board2d;
import jay.util.math.geom.Translation2d;

import java.awt.image.renderable.ContextualRenderedImageFactory;
import java.util.ArrayList;

public class PiecePositionTracker<Piece extends ChessPiece> implements ChessEventInvoker, ChessEventListener  {

    private final Piece m_piece;
    private Translation2d m_currentTranslation;
    private boolean m_isAlive = false;

    private final ArrayList<ChessEventListener> m_listeners = new ArrayList<>();

    public PiecePositionTracker(Piece piece) {
        m_piece = piece;
        m_currentTranslation = Translation2d.ZERO;
    }

    @Override
    public void addEventListener(ChessEventListener listener) {
        m_listeners.add(listener);
    }

    @Override
    public void invokeEvent(ChessEvent event) {
        for(ChessEventListener listener : m_listeners) {
            listener.onEventFired(event);
        }
    }

    public void reset(Translation2d translation) {
        m_currentTranslation = translation;
        m_isAlive = true;
    }

    public void firePositionChangedEvent(Translation2d originalTranslation, Translation2d newTranslation) {
        PieceMoveChessEvent event = new PieceMoveChessEvent(
                this,
                m_piece,
                originalTranslation,
                newTranslation
        );
        invokeEvent(event);
    }

    public void update(Board2d<ChessTile> board) {
        if(m_isAlive) {
            for (int i = 0; i < board.getHeight(); i++) {
                for (int j = 0; j < board.getWidth(); j++) {
                    if (board.get(j, i).m_piece.isPresent() && board.get(j, i).m_piece.get() == m_piece) {
                        Translation2d foundTranslation = new Translation2d(j, i);
                        if (!foundTranslation.equals(m_currentTranslation)) {
                            firePositionChangedEvent(m_currentTranslation, foundTranslation);
                            m_currentTranslation = foundTranslation;
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onEventFired(ChessEvent event) {
        if(event instanceof PieceKilledChessEvent killedEvent && m_isAlive) {
            if(killedEvent.getKilled().m_piece.isPresent() && killedEvent.getKilled().m_piece.get() == m_piece) {
                invokeEvent(event);
                m_isAlive = false;
            }
        }
    }
}
