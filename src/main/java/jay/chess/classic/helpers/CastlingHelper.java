package jay.chess.classic.helpers;

import jay.chess.engine.ChessMovementInfo;
import jay.chess.engine.ChessTile;
import jay.chess.engine.PositionChessTile;
import jay.chess.engine.piece.ChessPiece;
import jay.util.Board2d;

public class CastlingHelper implements ChessHelper{

    @Override
    public boolean applyIfPossible(Board2d<ChessTile> board, PositionChessTile defending, PositionChessTile attacking) {

        return false;
    }
}
