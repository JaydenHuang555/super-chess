package jay.chess.engine.player;

import jay.chess.engine.ChessAlliance;
import jay.chess.engine.ChessMovementInfo;
import jay.chess.engine.ChessTile;
import jay.util.Board2d;

public abstract class ClassicPlayer implements Player{

    private String m_name;
    private ChessAlliance m_alliance;

    public ClassicPlayer(String name) {
        m_name = name;
    }

    @Override
    public void reset() {

    }

    @Override
    public void end() {
    }

    @Override
    public String getName() {
        return m_name;
    }

    @Override
    public void setAlliance(ChessAlliance alliance) {
        m_alliance = alliance;
    }

    @Override
    public ChessAlliance getAlliance() {
        return m_alliance;
    }
}
