package jay.chess.gui.board;

import jay.chess.engine.ChessAlliance;
import jay.chess.engine.ChessMovementInfo;
import jay.chess.engine.ChessTile;
import jay.util.Board2d;
import jay.util.math.geom.Translation2d;
import jay.util.swing.EmptyComponent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.util.Optional;

public class ChessTileActionListener implements ActionListener  {

    private final Board2d<ChessTileJButton> m_buttonBoard;
    private ChessAlliance m_currentPlayingAlliance = ChessAlliance.WHITE;
    private Optional<ChessTileJButton> m_lastSelectedPiece = Optional.empty();
    private final EmptyComponent m_propertyChangingComponent = new EmptyComponent();
    private final static String PROPERTY_WANT_MOVE = "Move";

    public ChessTileActionListener(PropertyChangeListener listener, Board2d<ChessTileJButton> buttonBoard) {
        m_buttonBoard = buttonBoard;
        m_propertyChangingComponent.addPropertyChangeListener(PROPERTY_WANT_MOVE, listener);
    }

    public void syncAlliance(ChessAlliance alliance) {
        m_currentPlayingAlliance = alliance;
    }

    public void syncBoard(Board2d<ChessTile> board) {
        for(int i = 0; i < board.getArea(); i++) {
            ChessTile incoming = board.get(i);
            if(incoming.piece.isEmpty()) {
                m_buttonBoard.get(i).setPresentPiece(Optional.empty());
            }
            else {
                m_buttonBoard.get(i).setPresentPiece(Optional.of(incoming.piece.get()));
            }
        }
    }

    public boolean hasSelectedPiece() {
        return m_lastSelectedPiece.isPresent();
    }

    public boolean inputIsOnCurrentAlliance(ChessTileJButton input) {
        if(input.getPresentPieceNameOptional().isEmpty()) {
            return false;
        }
        return input.getPresentPieceNameOptional().get().getAlliance() == m_currentPlayingAlliance;
    }

    public void move(Translation2d inputTranslation) {
        move(new ChessMovementInfo(m_lastSelectedPiece.get().getM_translation(), inputTranslation));
    }

    public void handlePieceSelection(ChessTileJButton input) {
        if(!hasSelectedPiece()) {
            if(input.getPresentPieceNameOptional().isPresent() && inputIsOnCurrentAlliance(input)) {
                m_lastSelectedPiece = Optional.of(input);
            }
            else {
                m_lastSelectedPiece = Optional.empty();
            }
        }

        else {
            if(input.getPresentPieceNameOptional().isPresent()) {
                if(inputIsOnCurrentAlliance(input)) {
                    m_lastSelectedPiece = Optional.of(input);
                }
                else {
                    move(input.getM_translation());
                }
            }
            else {
                move(input.getM_translation());
            }
        }
    }

    public void move(ChessMovementInfo info) {
        m_propertyChangingComponent.fireObjectPropertyChange(PROPERTY_WANT_MOVE, null, info);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() instanceof ChessTileJButton button) {
            Translation2d translation = button.getM_translation();
            handlePieceSelection(button);
        }
    }


}
