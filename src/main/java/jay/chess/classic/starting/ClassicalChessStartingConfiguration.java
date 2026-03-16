package jay.chess.classic.starting;

import jay.chess.engine.ChessAlliance;
import jay.chess.engine.ChessTile;
import jay.chess.engine.piece.*;
import jay.chess.engine.starting.ChessStartingConfiguration;
import jay.util.Board2d;
import jay.util.Pair;
import jay.util.math.geom.GeomUtil;
import jay.util.math.geom.Translation2d;

import java.util.function.Function;

public class ClassicalChessStartingConfiguration extends ChessStartingConfiguration {

    /**
     * @apinote white based translations
     * */

    public static final Translation2d KING = new Translation2d(4, 0);

    public static final Translation2d KNIGHT_LEFT = new Translation2d(1.0, 0.0);
    public static final Translation2d BISHOP_LEFT = new Translation2d(2.0, 0.0);
    public static final Translation2d ROOK_LEFT = new Translation2d(0.0, 0.0);
    public static final Translation2d QUEEN = new Translation2d(3.0, 0.0);

    public Pair<Translation2d, Function<ChessAlliance, ChessPiece>>[] applyPawn(Board2d<ChessTile> board, Pair<Translation2d, Function<ChessAlliance, ChessPiece>>[] buffered) {
        Pair<Translation2d, Function<ChessAlliance, ChessPiece>>[] replace = new Pair[buffered.length + board.getWidth()];
        for(int i = 0; i < board.getWidth() + buffered.length; i++) {
            if(i < board.getWidth()) {
                replace[i] = Pair.of(new Translation2d(i, 1.0), PawnChessPiece::new);
            }
            else {
                int bufferedIndex = i - board.getWidth();
                replace[i] = buffered[bufferedIndex];
            }
        }
        return replace;
    }

    @Override
    public Pair<Translation2d, Function<ChessAlliance, ChessPiece>>[] getWhite(Board2d<ChessTile> board) {
        return applyPawn(board, new Pair[]{
                Pair.of(KING, (Function<ChessAlliance, ChessPiece>) KingChessPiece::new),
                Pair.of(KNIGHT_LEFT, (Function<ChessAlliance, ChessPiece>) KnightChessPiece::new),
                Pair.of(GeomUtil.flipAcrossY(board.getWidth() - 1, KNIGHT_LEFT), (Function<ChessAlliance, ChessPiece>) KnightChessPiece::new),
                Pair.of(BISHOP_LEFT, (Function<ChessAlliance, ChessPiece>) BishopChessPiece::new),
                Pair.of(GeomUtil.flipAcrossY(board.getWidth() - 1, BISHOP_LEFT), (Function<ChessAlliance, ChessPiece>) BishopChessPiece::new),
                Pair.of(ROOK_LEFT, (Function<ChessAlliance, ChessPiece>)RookChessPiece::new),
                Pair.of(GeomUtil.flipAcrossY(board.getWidth() - 1, ROOK_LEFT), (Function<ChessAlliance, ChessPiece>)RookChessPiece::new),
                Pair.of(QUEEN, (Function<ChessAlliance, ChessPiece>)QueenChessPiece::new)
        });
    }
}
