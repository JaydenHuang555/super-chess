package jay.chess.engine;


import jay.chess.engine.piece.ChessPiece;
import jay.chess.engine.piece.KingChessPiece;
import jay.chess.engine.player.Player;
import jay.chess.engine.starting.ChessStartingConfiguration;
import jay.chess.engine.starting.ClassicalChessStartingConfiguration;
import jay.util.Board2d;
import jay.util.CyclingPair;
import jay.util.Pair;
import jay.util.math.geom.Translation2d;

import java.util.Optional;

public class ChessEngine {

    private final Board2d<ChessTile> board;
    private ChessStartingConfiguration startingConfiguration = new ClassicalChessStartingConfiguration();
    private ChessAlliance currentPlayingAlliance = ChessAlliance.WHITE;
    private CyclingPair<Player> players;
    private final KingPositionTracker kingPositionTracker = new KingPositionTracker();
    private Optional<ChessAlliance> wimningAlliance = Optional.empty();

    public ChessEngine() {
        board = new Board2d<ChessTile>(8, 8);
        for(int i = 0; i < board.getArea(); i++) {
            board.set(i, new ChessTile());
        }
    }

    public void setPlayer(Player p1, Player p2) {
        p1.setAlliance(ChessAlliance.WHITE);
        p2.setAlliance(ChessAlliance.BLACK);
        players = new CyclingPair<>(p1, p2);
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
        players.getFirst().reset();
        players.getSecond().reset();
        kingPositionTracker.reset();
        wimningAlliance = Optional.empty();
    }

    public void play() {
        Player playing = players.getFirst();
        System.out.println(playing.getName() + " playing");
        while(true) {
            ChessMovementInfo info = playing.play(board);
            if(move(playing.getAlliance(), info.attacker, info.defender)) {
                System.out.println("moved");
                break;
            }
        }
        System.out.println("Finished Play");
    }

    private boolean move(ChessAlliance playingAlliance, Translation2d pieceTranslation, Translation2d targetTranslation) {
        ChessTile movingTile = board.get(pieceTranslation);
        ChessTile defendingTile = board.get(targetTranslation);
        if(movingTile.piece.isPresent()) {
            ChessPiece movingPiece = movingTile.piece.get();
            if(movingPiece.canMove(board, pieceTranslation, targetTranslation, defendingTile.piece)) {
                if(defendingTile.piece.isPresent()) {
                    ChessPiece tilePiece = defendingTile.piece.get();
                    if(tilePiece instanceof KingChessPiece) {
                        if(tilePiece.getAlliance() == playingAlliance) {
                            return false;
                        }
                        else {

                        }
                    }
                }
                board.set(pieceTranslation, new ChessTile());
                board.set(targetTranslation, movingTile);
                return true;
            }
        }
        return false;
    }

    public ChessAlliance getCurrentPlayingAlliance() {
        return currentPlayingAlliance;
    }

    public Board2d<ChessTile> getBoard() {
        return board;
    }

    private class KingPositionTracker {


        public Translation2d blackTranslation = Translation2d.ZERO, whiteTranslation = Translation2d.ZERO;

        public void reset() {
            blackTranslation = Translation2d.ZERO;
            whiteTranslation = Translation2d.ZERO;
        }

    }

}
