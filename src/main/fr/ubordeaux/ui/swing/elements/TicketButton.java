package main.fr.ubordeaux.ui.swing.elements;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Project :
 * Class : TicketButton
 * Created at 05/03/20 at 12:19
 * Author : rlasvenes
 */

public class TicketButton extends JButton {

    private final static int SIZE_BUTTON = 200;

    public TicketButton() {
        super();
    }

    public TicketButton(Icon icon) {
        super(icon);
    }

    public TicketButton(String text) {
        super(text);
        this.setForeground(Color.BLACK);
        this.setBackground(Color.WHITE);
        Border line = new LineBorder(Color.BLACK);
        Border margin = new EmptyBorder(5, 15, 5, 15);
        Border compound = new CompoundBorder(line, margin);
        this.setBorder(compound);
        this.setPreferredSize(new Dimension(SIZE_BUTTON, SIZE_BUTTON));
    }

    public TicketButton(Action a) {
        super(a);
    }

    public TicketButton(String text, Icon icon) {
        super(text, icon);
    }
}
