package main.fr.ubordeaux.ui.swing.listeners;

import main.fr.ubordeaux.ui.swing.Layouts.Layouts;

import javax.smartcardio.Card;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Project :
 * Class : KeySwitchViewListener
 * Created at 08/03/2020 at 21:41
 * Author : rlasvenes
 */

public class KeySwitchViewListener implements KeyListener {

    private LayoutManager layout;
    private Container container;

    public KeySwitchViewListener(LayoutManager layout, Container container) {
        this.layout = layout;
        this.container = container;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            System.out.println("ESC key pressed");
            ((CardLayout)layout).show(container, String.valueOf(Layouts.MAIN_MENU.id()));
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
