package main.fr.ubordeaux.ui.swing.signal;

import java.util.Observable;

/**
 * Project :
 * Class : ChangeViewComponentSignal
 * Created at 05/03/20 at 02:21
 * Author : rlasvenes
 */

public class ChangeViewComponentSignal extends Observable {
    public ChangeViewComponentSignal() {
        super();
    }

    public void changeData(Object data) {
        setChanged();
        notifyObservers(data);
    }
}