package main.fr.ubordeaux.ui.swing;

import main.fr.ubordeaux.domain.type.EnseigneId;

import javax.swing.*;
import java.awt.*;

/**
 * Project :
 * Class : CreateTicketWindow
 * Created at 26/12/19 at 17:57
 * Author : rlasvenes
 */

public class CreateTicketWindow extends JFrame {

    private JPanel mainPanel;

    private JLabel labelEnseigne;
    private JLabel labelDateTicket;

    private JTextField dateTicket;

    private JComboBox<EnseigneId> enseignes;


    public CreateTicketWindow(String title) {
        this.setTitle(title);
        this.setSize(920, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            CreateTicketWindow window = new CreateTicketWindow("Test");
            window.setVisible(true);
        });
    }
}
