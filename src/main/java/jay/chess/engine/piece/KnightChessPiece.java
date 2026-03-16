package jay.chess.engine.piece;

import jay.chess.engine.ChessAlliance;
import jay.chess.engine.ChessTile;
import jay.util.Board2d;
import jay.util.Pair;
import jay.util.math.geom.Translation2d;

import java.util.Optional;

public class KnightChessPiece extends ChessPiece{
    public KnightChessPiece(ChessAlliance alliance) {
        super("Knight", alliance);
    }

    @Override
    public long serialID() {
        return 4l;
    }

    @Override
    public boolean canMove(Board2d<ChessTile> board, Translation2d currentPoint, Translation2d desiredPoint, Optional<ChessPiece> defendingPiece) {
        currentPoint = whiteOriginBased(board, currentPoint);
        desiredPoint = whiteOriginBased(board, desiredPoint);
        Translation2d delta = desiredPoint.minus(currentPoint).round();

        if(Math.abs(delta.getX()) == 1 && Math.abs(delta.getY()) == 2) {
            return true;
        }
        if(Math.abs(delta.getX()) == 2 && Math.abs(delta.getY()) == 1) {
            return true;
        }

        return false;
    }
}
