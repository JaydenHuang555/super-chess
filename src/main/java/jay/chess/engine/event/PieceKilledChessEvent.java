package jay.chess.engine.event;

import jay.chess.engine.PositionChessTile;
import jay.chess.engine.piece.ChessPiece;

public class PieceKilledChessEvent extends ChessEvent  {

    private final PositionChessTile m_killed, m_killer;

    public PieceKilledChessEvent(ChessEventInvoker invoker, PositionChessTile killed, PositionChessTile killer) {
        super(invoker);
        m_killed = killed;
        m_killer = killer;
    }

    public PositionChessTile getKilled() {
        return m_killed;
    }

    public PositionChessTile getKiller() {
        return m_killer;
    }

}
