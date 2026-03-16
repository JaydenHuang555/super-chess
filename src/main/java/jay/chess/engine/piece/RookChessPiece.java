package jay.chess.engine.piece;

import jay.chess.engine.ChessAlliance;
import jay.chess.engine.ChessTile;
import jay.util.Board2d;
import jay.util.math.geom.Translation2d;

import java.util.Optional;

public class RookChessPiece extends ChessPiece {
    public RookChessPiece(ChessAlliance alliance) {
        super("Rook", alliance);
    }

    @Override
    public long serialID() {
        return 9032l;
    }

    @Override
    public boolean canMove(Board2d<ChessTile> board, Translation2d currentPoint, Translation2d desiredPoint, Optional<ChessPiece> defendingPiece) {
        Translation2d delta = desiredPoint.minus(currentPoint).round();
        if((delta.getX() != 0 && delta.getY() == 0) || delta.getY() != 0 && delta.getX() == 0) {
            int distance = (int)Math.round(delta.norm());
            for(int i = 0; i < distance; i++) {
                int x = 0, y = 0;
                if(delta.getX() == 0) {
                    y+= delta.getY() < 0 ? -1 : 1;
                }
                else {
                    x+= delta.getX() < 0 ? -1 : 1;
                }
                if(board.get(currentPoint.plus(x, y)).m_piece.isPresent()) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
