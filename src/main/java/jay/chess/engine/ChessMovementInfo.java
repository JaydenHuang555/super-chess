package jay.chess.engine;

import jay.util.math.geom.Translation2d;

public class ChessMovementInfo {

    public Translation2d m_attacker;
    public Translation2d m_defender;

    public ChessMovementInfo(Translation2d attacker, Translation2d defender) {
        m_attacker = attacker;
        m_defender = defender;
    }

    public ChessMovementInfo() {
        this(null, null);
    }

}
