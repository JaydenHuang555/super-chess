package jay.chess.engine.event;

public abstract class ChessEvent {

    protected final ChessEventInvoker m_invoker;

    public ChessEvent(ChessEventInvoker invoker) {
        m_invoker = invoker;
    }

    public ChessEventInvoker getInvoker() {
        return m_invoker;
    }

}
