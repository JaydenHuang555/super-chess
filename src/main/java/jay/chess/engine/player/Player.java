package jay.chess.engine.player;

import jay.chess.engine.ChessAlliance;
import jay.chess.engine.ChessMovementInfo;
import jay.chess.engine.ChessTile;
import jay.util.Board2d;
import jay.util.math.geom.Translation2d;

public interface Player {

    public void reset();

    public void end();

    public abstract ChessMovementInfo play(Board2d<ChessTile> board);

    public String getName();

    public void setAlliance(ChessAlliance alliance);

    public ChessAlliance getAlliance();
}
