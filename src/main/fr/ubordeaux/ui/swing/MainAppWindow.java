package main.fr.ubordeaux.ui.swing;

import javafx.scene.layout.HBox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Project :
 * Class : MainAppWindow
 * Created at 26/12/19 at 16:18
 * Author : rlasvenes
 */

public class MainAppWindow extends JFrame {
    public MainAppWindow() {
        this.setTitle("Main Window");
        this.setSize(720, 500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        BoxLayout box = new BoxLayout(panel, BoxLayout.X_AXIS);
        panel.setLayout(box);

        JButton create = new JButton("Créer un ticket");

        create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showInputDialog(null, "Quelle nom pour le ticket ?", JOptionPane.PLAIN_MESSAGE);
            }
        });

        panel.add(create);

        this.getContentPane().add(panel);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            MainAppWindow window = new MainAppWindow();
            window.setVisible(true);
        });
    }
}
