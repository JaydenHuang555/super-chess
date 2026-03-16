package jay.chess.engine;

import jay.chess.engine.piece.ChessPiece;

import java.util.Optional;

public class ChessTile {

    public Optional<ChessPiece> m_piece = Optional.empty();

    public ChessTile() {

    }

    public ChessTile(Optional<ChessPiece> piece) {
        m_piece = piece;
    }

    public void reset() {
        m_piece = Optional.empty();
    }

}
