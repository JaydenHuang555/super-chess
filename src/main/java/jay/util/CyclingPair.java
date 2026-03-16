package jay.util;

import java.util.function.BooleanSupplier;
import java.util.function.Function;

public class CyclingPair<A> extends Pair<A, A> {

    private A m_value;

    public CyclingPair(A first, A second) {
        super(first, second);
        seed();
    }

    public void seed() {
        m_value = m_first;
    }

    public void seed(Function<A, Boolean> seedGetter) {
        if(seedGetter.apply(m_first)) {
            m_value = m_first;
        }
        else {
            m_value = m_second;
        }
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
