package jay.chess.engine.player.bot;

import jay.chess.engine.ChessAlliance;
import jay.chess.engine.ChessMovementInfo;
import jay.chess.engine.ChessTile;
import jay.chess.engine.piece.ChessPiece;
import jay.util.Board2d;
import jay.util.Pair;
import jay.util.math.geom.Translation2d;

import java.util.ArrayList;
import java.util.Random;

public class RandomBotPlayer extends BotPlayer {

    private ArrayList<Pair<Translation2d, ChessPiece>> m_alliancePieces = null;

    public RandomBotPlayer(String name) {
        super(name);
    }

    @Override
    public void reset() {
        m_alliancePieces = new ArrayList<>();
    }

    @Override
    public void end() {
        m_alliancePieces = null;
    }

    @Override
    public ChessMovementInfo play(Board2d<ChessTile> board) {
        for(int i = 0; i < board.getHeight(); i++) {
            for(int j = 0; j < board.getWidth(); j++) {
                if(board.get(j, i).piece.isPresent() && board.get(j, i).piece.get().getAlliance() == getAlliance()) {
                    m_alliancePieces.add(Pair.of(new Translation2d(j, i), board.get(j, i).piece.get()));
                }
            }
        }
        int maxIndex = m_alliancePieces.size() - 1;
        Random random = new Random();
        int index = random.nextInt(0, maxIndex);
        Pair<Translation2d, ChessPiece> chosen = m_alliancePieces.get(index);
        for(;;) {
            int x = random.nextInt(0, board.getWidth() - 1);
            int y = random.nextInt(0, board.getWidth() - 1);
            Translation2d next = new Translation2d(x, y);
            if(chosen.getSecond().canMove(board, chosen.getFirst(), next, board.get(next).piece)) {
                ChessMovementInfo info = new ChessMovementInfo();
                info.attacker = chosen.getFirst();
                info.defender = next;
                return info;
            }
        }
    }

}
