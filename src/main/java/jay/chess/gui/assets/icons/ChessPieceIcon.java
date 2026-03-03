package jay.chess.gui.assets.icons;

import javax.swing.*;
import java.net.URL;

public enum ChessPieceIcon {

    PAWN,
    ROOK,
    KNIGHT,
    BISHOP,
    QUEEN,
    KING
    ;

    public String path = ChessPieceIcons.BASE_PATH;
    public ImageIcon blackIcon, whiteIcon;

    private ChessPieceIcon() {
        path = path + "/" + name().toLowerCase();
        URL blackURL = ChessPieceIcon.class.getResource(path + "/black.png");
        blackIcon = new ImageIcon(blackURL);
        URL whiteURL = ChessPieceIcon.class.getResource(path + "/white.png");
        whiteIcon = new ImageIcon(whiteURL);
    }


}
