package jay.chess.engine.event;

import jay.chess.engine.piece.ChessPiece;
import jay.util.math.geom.Translation2d;

import java.util.Optional;

public class PieceMoveChessEvent extends ChessEvent {

    private final ChessPiece m_piece;
    private final Translation2d m_originalTranslation;
    private final Translation2d m_newTranslation;

    public PieceMoveChessEvent(ChessEventInvoker invoker, ChessPiece piece, Translation2d originalTranslation, Translation2d newTranslation) {
        super(invoker);
        m_piece = piece;
        m_originalTranslation = originalTranslation;
        m_newTranslation = newTranslation;
    }

    public ChessPiece getPiece() {
        return m_piece;
    }

    public Translation2d getOriginalTranslation() {
        return m_originalTranslation;
    }

    public Translation2d getNewTranslation() {
        return m_newTranslation;
    }

}
