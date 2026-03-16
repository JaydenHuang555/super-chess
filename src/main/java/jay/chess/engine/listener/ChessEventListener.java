package jay.chess.engine.listener;

import jay.chess.engine.event.ChessEvent;

public interface ChessEventListener {

    void onEventFired(ChessEvent event);

}
