package jay.chess.engine;

import jay.util.math.geom.Translation2d;

public class PositionChessTile extends ChessTile {

    public Translation2d m_position = Translation2d.ZERO;

    public PositionChessTile() {

    }

    public PositionChessTile(ChessTile other) {
        m_piece = other.m_piece;
    }

    public PositionChessTile(ChessTile other, Translation2d position) {
        this(other);
        m_position = position;
    }

    @Override
    public void reset() {
        super.reset();
        m_position = Translation2d.ZERO;
    }

}
