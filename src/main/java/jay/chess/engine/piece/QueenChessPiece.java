package jay.chess.engine.piece;

import jay.chess.engine.ChessAlliance;
import jay.chess.engine.ChessTile;
import jay.util.Board2d;
import jay.util.math.geom.Translation2d;

import java.util.Optional;

public class QueenChessPiece extends ChessPiece {
    public QueenChessPiece(ChessAlliance alliance) {
        super("Queen", alliance);
    }

    @Override
    public long serialID() {
        return 0;
    }

    @Override
    public boolean canMove(Board2d<ChessTile> board, Translation2d currentPoint, Translation2d desiredPoint, Optional<ChessPiece> defendingPiece) {
        Translation2d delta = desiredPoint.minus(currentPoint);
        int x = 0, y = 0;
        while(true) {
            if(delta.getY() != 0) {
                y += delta.getY() < 0 ? -1 : 1;
            }
            if(delta.getX() != 0) {
                x += delta.getX() < 0 ? -1 : 1;
            }
            Translation2d outreach = currentPoint.plus(x, y);
            if(outreach.equals(desiredPoint)) {
                break;
            }
            if(board.get(outreach).piece.isPresent()) {
                return false;
            }
        }
        return true;
    }
}
