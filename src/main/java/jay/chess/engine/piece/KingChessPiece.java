package jay.chess.engine.piece;

import jay.chess.engine.ChessAlliance;
import jay.chess.engine.ChessTile;
import jay.util.Board2d;
import jay.util.CardinalDirection;
import jay.util.math.geom.Translation2d;

import java.util.List;
import java.util.Optional;

public class KingChessPiece extends ChessPiece {

    public KingChessPiece(ChessAlliance alliance) {
        super("King", alliance);
    }

    public KingChessPiece() {
        super("King");
    }

    @Override
    public boolean canMove(Board2d<ChessTile> board, Translation2d currentTranslation, Translation2d desiredTranslation, Optional<ChessPiece> defendingPiece) {
        Translation2d delta = desiredTranslation.minus(currentTranslation);
        if(Math.abs(delta.getX()) <= 1 && Math.abs(delta.getY()) <= 1) {
            Optional<List<CardinalDirection>> blockedDirectionsOptional = board.blockedByBoard(currentTranslation);
            if(blockedDirectionsOptional.isPresent()) {
                List<CardinalDirection> blockedDirections = blockedDirectionsOptional.get();
                if(blockedDirections.contains(CardinalDirection.NORTH) && delta.getY() == 1) return false;
                if(blockedDirections.contains(CardinalDirection.SOUTH) && delta.getY() == -1) return false;
                if(blockedDirections.contains(CardinalDirection.WEST) && delta.getX() == -1) return false;
                if(blockedDirections.contains(CardinalDirection.EAST) && delta.getX() == 1) return false;
            }
            return true;
        }
        return false;
    }

    public long serialID() {
        return 1l;
    }

}
