package jay.chess.engine.starting;

import jay.chess.engine.ChessAlliance;
import jay.chess.engine.ChessTile;
import jay.chess.engine.piece.ChessPiece;
import jay.util.Board2d;
import jay.util.Pair;
import jay.util.math.geom.GeomUtil;
import jay.util.math.geom.Translation2d;

import java.util.Optional;
import java.util.function.Function;

public abstract class ChessStartingConfiguration {


    public abstract Pair<Translation2d, Function<ChessAlliance, ChessPiece>>[] getWhite(Board2d<ChessTile> board);

    public void setup(Board2d<ChessTile> board) {
        for(Pair<Translation2d, Function<ChessAlliance, ChessPiece>> whitePieces : getWhite(board)) {
            Translation2d whiteTranslation = whitePieces.getFirst();
            Function<ChessAlliance, ChessPiece> pieceGetter = whitePieces.getSecond();
            board.get(whiteTranslation).piece = Optional.of(pieceGetter.apply(ChessAlliance.WHITE));
            Translation2d blackTranslation = GeomUtil.rotateAround(board.getCenterRelativeToIndices(), whiteTranslation);
            board.get(blackTranslation).piece = Optional.of(pieceGetter.apply(ChessAlliance.BLACK));
        }
    }

}
