package main.fr.ubordeaux.ui.swing;

import javafx.scene.layout.HBox;
import main.fr.ubordeaux.domain.model.TicketRepository;
import main.fr.ubordeaux.infrastructure.inmemory.TicketRepositoryInMemory;

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
                TicketRepository rep = new TicketRepositoryInMemory();
                CreateTicketWindow w = new CreateTicketWindow("Création de ticket", rep);
                w.setVisible(true);
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
