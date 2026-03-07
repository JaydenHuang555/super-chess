package jay.chess.engine.player;

import jay.chess.engine.ChessAlliance;
import jay.chess.engine.ChessMovementInfo;
import jay.chess.engine.ChessTile;
import jay.util.Board2d;
import jay.util.math.geom.Translation2d;

public abstract class Player {

    private ChessAlliance m_alliance;
    private String m_name;

    public Player(String name) {
        m_name = name;
    }

    public void reset() {

    }

    public void end() {

    }

    public abstract ChessMovementInfo play(Board2d<ChessTile> board);

    public String getName() {
        return m_name;
    }

    public void setAlliance(ChessAlliance alliance) {
        m_alliance = alliance;
    }

    public ChessAlliance getAlliance() {
        return m_alliance;
    }
}
