package jay.chess.engine;


import jay.chess.engine.piece.ChessPiece;
import jay.chess.engine.starting.ChessStartingConfiguration;
import jay.chess.engine.starting.ClassicalChessStartingConfiguration;
import jay.util.Board2d;
import jay.util.math.geom.Translation2d;

public class ChessEngine {

    private final Board2d<ChessTile> board;
    private ChessStartingConfiguration startingConfiguration = new ClassicalChessStartingConfiguration();
    private ChessAlliance currentPlayingAlliance = ChessAlliance.WHITE;

    public ChessEngine() {
        board = new Board2d<ChessTile>(8, 8);
        for(int i = 0; i < board.getArea(); i++) {
            board.set(i, new ChessTile());
        }
    }

    public void setStartingConfiguration(ChessStartingConfiguration configuration) {
        startingConfiguration = configuration;
    }

    public void reset() {
        for(int i = 0; i < board.getArea(); i++) {
            board.get(i).reset();
        }
        startingConfiguration.setup(board);
        currentPlayingAlliance = ChessAlliance.WHITE;
    }

    public boolean move(Translation2d pieceTranslation, Translation2d targetTranslation) {
        ChessTile movingTile = board.get(pieceTranslation);
        ChessTile defendingTile = board.get(targetTranslation);
        if(movingTile.piece.isPresent()) {
            ChessPiece movingPiece = movingTile.piece.get();
            if(movingPiece.canMove(board, pieceTranslation, targetTranslation, defendingTile.piece)) {
                board.set(pieceTranslation, new ChessTile());
                board.set(targetTranslation, movingTile);
                cycleAlliance();
                return true;
            }
        }
        return false;
    }

    public ChessAlliance getCurrentPlayingAlliance() {
        return currentPlayingAlliance;
    }

    public void cycleAlliance() {
         ChessAlliance cycle = switch(currentPlayingAlliance) {
             case WHITE -> ChessAlliance.BLACK;
             case BLACK -> ChessAlliance.WHITE;
         };
         currentPlayingAlliance = cycle;
    }

    public Board2d<ChessTile> getBoard() {
        return board;
    }

}
