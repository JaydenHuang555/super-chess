package jay.chess.gui.board;

import jay.chess.engine.piece.ChessPiece;
import jay.chess.gui.assets.icons.ChessPieceIcons;
import jay.util.math.geom.Translation2d;

import javax.swing.*;
import java.awt.*;
import java.util.Optional;

public class ChessTileJButton extends JButton {

    public static final String ICONS_PATH = "/assets/icons";

    private final Translation2d m_translation;
    private Optional<ChessPiece> m_presentPieceOptional = Optional.empty();

    public ChessTileJButton(Translation2d translation) {
        this.m_translation = translation;
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(80, 80));
    }

    public void setPresentPiece(Optional<ChessPiece> presentPieceOptional) {
        this.m_presentPieceOptional = presentPieceOptional;
       setIcon(ChessPieceIcons.getIcon(presentPieceOptional));
    }

    public Optional<ChessPiece> getPresentPieceNameOptional() {
        return m_presentPieceOptional;
    }

    public Translation2d getM_translation() {
        return m_translation;
    }

}
