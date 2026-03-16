package jay.chess.engine.piece;

import jay.chess.engine.ChessAlliance;
import jay.chess.engine.ChessTile;
import jay.util.Board2d;
import jay.util.math.MathUtil;
import jay.util.math.geom.GeomUtil;
import jay.util.math.geom.Line2d;
import jay.util.math.geom.Translation2d;

import java.awt.geom.Line2D;
import java.util.Optional;

public class BishopChessPiece extends ChessPiece {
    public BishopChessPiece(ChessAlliance alliance) {
        super("Bishop", alliance);
    }

    @Override
    public long serialID() {
        return 23l;
    }

    @Override
    public boolean canMove(Board2d<ChessTile> board, Translation2d currentPoint, Translation2d desiredPoint, Optional<ChessPiece> defendingPiece) {
        Translation2d currentPointWhite = whiteOriginBased(board, currentPoint);
        Translation2d desiredPointWhite = whiteOriginBased(board, desiredPoint);
        Line2d whiteLine = new Line2d(currentPointWhite, desiredPointWhite);
        if(MathUtil.epsilonEquals(Math.abs(whiteLine.getSlope()), 1.0)) {
            currentPoint = currentPoint.round();
            desiredPoint = desiredPoint.round();
            Translation2d delta = desiredPoint.minus(currentPoint).round();
            int x = 0, y = 0;
            while(true) {
                x+= delta.getX() < 0 ? -1 : 1;
                y+= delta.getY() < 0 ? -1 : 1;
                Translation2d output = currentPoint.plus(x, y);
                if(output.equals(currentPoint)) {
                    break;
                }
                if(board.get(output).piece.isPresent()) {
                    ChessPiece piece = board.get(output).piece.get();
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
