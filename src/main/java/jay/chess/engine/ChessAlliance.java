package jay.chess.engine;

public enum ChessAlliance {
    BLACK(1),
    WHITE(0)
    ;
    public int id;
    private ChessAlliance(int id) {
        this.id = id;
    }

    public boolean isBlack() {
        return this == BLACK;
    }

    public boolean isWhite() {
        return this == WHITE;
    }

    public ChessAlliance getOpposingAlliance() {
        return switch(this) {
            case BLACK -> WHITE;
            case WHITE -> BLACK;
        };
    }

}
