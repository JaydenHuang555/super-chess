package jay.chess.engine.event;

import jay.chess.engine.listener.ChessEventListener;

public interface ChessEventInvoker {

    void addEventListener(ChessEventListener listener);

    void invokeEvent(ChessEvent event);

}
