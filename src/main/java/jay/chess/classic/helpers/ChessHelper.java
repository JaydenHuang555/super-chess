package jay.chess.classic.helpers;

import jay.chess.engine.ChessMovementInfo;
import jay.chess.engine.ChessTile;
import jay.chess.engine.PositionChessTile;
import jay.util.Board2d;

public interface ChessHelper {

    boolean applyIfPossible(Board2d<ChessTile> board, PositionChessTile defender, PositionChessTile attacker);


    default boolean applyIfPossible(Board2d<ChessTile> board, ChessMovementInfo info) {
        PositionChessTile defending = new PositionChessTile(board.get(info.m_defender), info.m_defender);
        PositionChessTile attacking = new PositionChessTile(board.get(info.m_attacker), info.m_attacker);
        return applyIfPossible(board, defending, attacking);
    }

}
