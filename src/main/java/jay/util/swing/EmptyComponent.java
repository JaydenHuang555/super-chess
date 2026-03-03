package jay.util.swing;

import java.awt.*;

public class EmptyComponent extends Component {

    public void fireObjectPropertyChange(String propertyName, Object old, Object next) {
        super.firePropertyChange(propertyName, old, next);
    }

}
