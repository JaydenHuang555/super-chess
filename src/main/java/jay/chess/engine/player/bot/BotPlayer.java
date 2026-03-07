package jay.chess.engine.player.bot;

import jay.chess.engine.ChessMovementInfo;
import jay.chess.engine.ChessTile;
import jay.chess.engine.player.Player;
import jay.util.Board2d;

public abstract class BotPlayer extends Player {
    public BotPlayer(String name) {
        super(name);
    }
}
