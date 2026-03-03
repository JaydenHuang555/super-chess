package jay.chess.gui.assets.icons;

import jay.chess.engine.ChessTile;
import jay.chess.engine.piece.*;

import javax.swing.*;
import java.net.URL;
import java.util.Optional;

public class ChessPieceIcons {

    public static final String BASE_PATH = "/assets/icons";

    public static ImageIcon getIcon(ChessPiece piece) {
        if(piece instanceof PawnChessPiece) {
            return switch(piece.getAlliance()) {
                case BLACK -> ChessPieceIcon.PAWN.blackIcon;
                case WHITE -> ChessPieceIcon.PAWN.whiteIcon;
            };
        }
        else if(piece instanceof RookChessPiece) {
            return switch(piece.getAlliance()) {
                case BLACK -> ChessPieceIcon.ROOK.blackIcon;
                case WHITE -> ChessPieceIcon.ROOK.whiteIcon;
            };
        }
        else if(piece instanceof KnightChessPiece) {
            return switch(piece.getAlliance()) {
                case BLACK -> ChessPieceIcon.KNIGHT.blackIcon;
                case WHITE -> ChessPieceIcon.KNIGHT.whiteIcon;
            };
        }
        else if(piece instanceof BishopChessPiece) {
            return switch(piece.getAlliance()) {
                case BLACK -> ChessPieceIcon.BISHOP.blackIcon;
                case WHITE -> ChessPieceIcon.BISHOP.whiteIcon;
            };
        }
        else if(piece instanceof QueenChessPiece) {
            return switch(piece.getAlliance()) {
                case BLACK -> ChessPieceIcon.QUEEN.blackIcon;
                case WHITE -> ChessPieceIcon.QUEEN.whiteIcon;
            };
        }
        else if(piece instanceof KingChessPiece) {
            return switch(piece.getAlliance()) {
                case BLACK -> ChessPieceIcon.KING.blackIcon;
                case WHITE -> ChessPieceIcon.KING.whiteIcon;
            };
        }
        else return null;
    }

    public static ImageIcon getIcon(Optional<ChessPiece> pieceOptional) {
        if(pieceOptional.isPresent()) {
            ChessPiece piece = pieceOptional.get();
            return getIcon(piece);
        }
        return null;
    }


}
