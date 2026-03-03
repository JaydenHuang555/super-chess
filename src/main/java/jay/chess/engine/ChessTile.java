package jay.chess.engine;

import jay.chess.engine.piece.ChessPiece;

import java.util.Optional;

public class ChessTile {

    public Optional<ChessPiece> piece = Optional.empty();

    public void reset() {
        piece = Optional.empty();
    }

}
