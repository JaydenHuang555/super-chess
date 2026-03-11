package jay.chess.engine.player.bot;

import jay.chess.engine.ChessAlliance;
import jay.chess.engine.ChessMovementInfo;
import jay.chess.engine.ChessTile;
import jay.chess.engine.piece.ChessPiece;
import jay.util.Board2d;
import jay.util.math.geom.Translation2d;

import java.util.Random;
import java.util.function.Function;

public class RandomClassicBotPlayer extends ClassicBotPlayer {

    public RandomClassicBotPlayer(String name) {
        super(name);
    }

    private Translation2d getLegalTranslation(Board2d<ChessTile> board, ChessAlliance legalAlliance, boolean acceptEmpty) {
        Translation2d next = Translation2d.ZERO;
        Random random = new Random();
        Function<Translation2d, Boolean> allower = translation -> {
            if(!board.containsTranslation(translation)) {
                return false;
            }

            ChessTile tile = board.get(translation);

            if(tile.piece.isEmpty()) {
                return acceptEmpty;
            }

            ChessPiece piece = tile.piece.get();
            return piece.getAlliance() == legalAlliance;
        };

        Translation2d minConstraints = Translation2d.ZERO;
        Translation2d maxConstraints = Translation2d.of(board.getWidth(), board.getHeight());

        while(!allower.apply(next)) {
            next = Translation2d.fromInt(random, minConstraints, maxConstraints);
        }

        return next;
    }

    @Override
    public ChessMovementInfo play(Board2d<ChessTile> board) {
        Random next = new Random(board.getArea());

        Translation2d baseTranslation = getLegalTranslation(board, getAlliance(), false);
        Translation2d attackTranslation = getLegalTranslation(board, getAlliance().getOpposingAlliance(), true);

        return new ChessMovementInfo(baseTranslation, attackTranslation);

    }

}
