package jay.chess.gui.board;

import jay.chess.engine.piece.ChessPiece;
import jay.chess.gui.assets.icons.ChessPieceIcons;
import jay.util.math.geom.Translation2d;

import javax.swing.*;
import java.awt.*;
import java.net.URI;
import java.net.URL;
import java.util.Optional;

public class ChessTileJButton extends JButton {

    public static final String ICONS_PATH = "/assets/icons";

    private final Translation2d translation;
    private Optional<ChessPiece> presentPieceOptional = Optional.empty();

    public ChessTileJButton(Translation2d translation) {
        this.translation = translation;
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(80, 80));
    }

    public void setPresentPiece(Optional<ChessPiece> presentPieceOptional) {
        this.presentPieceOptional = presentPieceOptional;
       setIcon(ChessPieceIcons.getIcon(presentPieceOptional));
    }

    public Optional<ChessPiece> getPresentPieceNameOptional() {
        return presentPieceOptional;
    }

    public Translation2d getTranslation() {
        return translation;
    }

}
