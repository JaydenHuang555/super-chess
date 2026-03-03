package jay.chess.engine.piece;

import jay.chess.engine.ChessAlliance;
import jay.chess.engine.ChessTile;
import jay.util.Board2d;
import jay.util.Pair;
import jay.util.math.geom.GeomUtil;
import jay.util.math.geom.Translation2d;

import java.util.Optional;

public abstract class ChessPiece {

    private final String name;
    private ChessAlliance alliance;

    public ChessPiece(String name, ChessAlliance alliance) {
        this.name = name;
        setAlliance(alliance);
    }

    public ChessPiece(String name) {
        this.name = name;
    }

    public void setAlliance(ChessAlliance alliance) {
        this.alliance = alliance;
    }

    public ChessAlliance getAlliance() {
        return alliance;
    }

    public abstract long serialID();

    public Translation2d whiteOriginBased(Board2d<ChessTile> board, Translation2d translation) {
        if(getAlliance().isBlack()) {
            return GeomUtil.rotateAround(board.getCenterRelativeToIndices(), translation, GeomUtil.ROTATION2D_PI).round();
        }
        return translation;
    }

    public Pair<Translation2d, Translation2d> whiteOriginBased(Board2d<ChessTile> board, Translation2d currentTranslation, Translation2d desiredTranslation) {
        return Pair.of(
                whiteOriginBased(board, currentTranslation),
                whiteOriginBased(board, desiredTranslation)
        );
    }



    public abstract boolean canMove(Board2d<ChessTile> board, Translation2d currentPoint, Translation2d desiredPoint, Optional<ChessPiece> defendingPiece);

    public String name() {
        return name;
    }

    @Override
    public String toString() {
        return alliance.name() + " " + name;
    }

}
