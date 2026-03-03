package jay.chess.engine.piece;

import jay.chess.engine.ChessAlliance;
import jay.chess.engine.ChessTile;
import jay.util.Board2d;
import jay.util.math.geom.GeomUtil;
import jay.util.math.geom.Rotation2d;
import jay.util.math.geom.Translation2d;

import java.util.Optional;

public class PawnChessPiece extends ChessPiece{


    public PawnChessPiece(ChessAlliance alliance) {
        super("Pawn", alliance);
    }

    @Override
    public long serialID() {
        return 2;
    }

    @Override
    public boolean canMove(Board2d<ChessTile> board, Translation2d currentPoint, Translation2d desiredPoint, Optional<ChessPiece> defendingPiece) {
        if(getAlliance().isBlack()) {
            currentPoint = GeomUtil.rotateAround(board.getCenterRelativeToIndices(), currentPoint, new Rotation2d(Math.PI)).round();
            desiredPoint = GeomUtil.rotateAround(board.getCenterRelativeToIndices(), desiredPoint, new Rotation2d(Math.PI)).round();
        }
        Translation2d deltaTranslation = desiredPoint.minus(currentPoint).floor();
        boolean atStart = currentPoint.getY() == 1.0;
        if(defendingPiece.isPresent()) {
            ChessPiece defender = defendingPiece.get();
            if(defender.getAlliance() == getAlliance()) return false;
            if(Math.abs(deltaTranslation.getX()) == 1 && deltaTranslation.getY() == 1) {
                return true;
            }
        }
        else {
            if(deltaTranslation.getX() == 0.0) {
                if(atStart && deltaTranslation.getY() == 2) {
                    return true;
                }
                else if(deltaTranslation.getY() == 1) {
                    return true;
                }
            }
        }
        return false;
    }
}
