package jay.util;

public class CyclingPair<A> extends Pair<A, A> {

    private A m_value;

    public CyclingPair(A first, A second) {
        super(first, second);
        seed();
    }

    public void seed() {
        m_value = m_first;
    }

    public A peek() {
        return m_value;
    }

    public A cycle() {
        A ret = m_value;
        if(m_value.equals(m_first)) {
            m_value = m_second;
        }
        else {
            m_value = m_first;
        }
        return ret;
    }

}
