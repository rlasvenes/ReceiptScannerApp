package main.fr.ubordeaux.ui.swing.components;

import main.fr.ubordeaux.ui.swing.MainAppWindow;
import main.fr.ubordeaux.ui.swing.elements.Showable;
import main.fr.ubordeaux.ui.swing.signal.ChangeViewComponentSignal;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Project :
 * Class : ModifyTicketComponent
 * Created at 05/03/20 at 01:44
 * Author : rlasvenes
 */

public class ModifyTicketComponent extends JPanel implements Showable {
    // TODO Finir de faire l'interface pour cette classe ainsi que la liaison avec la couche application
    private JButton test1;
    private JButton test2;

    private JFrame parent;
    private String index;

    public ModifyTicketComponent(JFrame parent, String index) {
        super();
        this.parent = parent;
        this.index = index;
        initComponents();
    }

    private void initComponents() {
        this.test1 = new JButton("Test 1");
        this.test2 = new JButton("Test 2");
        final ChangeViewComponentSignal observable = new ChangeViewComponentSignal();
        observable.addObserver((MainAppWindow)parent);
        test1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                observable.changeData(0);
            }
        });
        add(test1);
        add(test2);
        this.setVisible(true);
    }

    @Override
    public Object getIndex() {
        return index;
    }
}
