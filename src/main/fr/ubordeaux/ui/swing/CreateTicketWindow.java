package main.fr.ubordeaux.ui.swing;

import main.fr.ubordeaux.application.Command;
import main.fr.ubordeaux.application.CreateTicket;
import main.fr.ubordeaux.domain.model.TicketInterface;
import main.fr.ubordeaux.domain.model.TicketRepository;
import main.fr.ubordeaux.domain.service.TicketBuilder;
import main.fr.ubordeaux.domain.type.EnseigneId;
import main.fr.ubordeaux.infrastructure.inmemory.TicketRepositoryInMemory;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Project :
 * Class : CreateTicketWindow
 * Created at 26/12/19 at 17:57
 * Author : rlasvenes
 */

public class CreateTicketWindow extends JFrame {

    private JPanel mainPanel;

    private JLabel labelNomTicket;
    private JTextField nomTicket;

    private JLabel labelEnseigneTicket;
    private JComboBox<EnseigneId> enseignes;

    private JLabel labelDateTicket;
    private JTextField dateTicket;

    private JButton addButton;

    private TicketRepository rep;

    public CreateTicketWindow(String title, TicketRepository rep) {
        this.setTitle(title);
        this.setSize(920, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.rep = rep;

        initGUI();
        initListeners();
    }

    private void initListeners() {
        this.addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nomTicket.getText().trim().length() == 0 || dateTicket.getText().trim().length() == 0 || !isValidDate(dateTicket.getText())) {
                    JOptionPane.showMessageDialog(mainPanel, "Données érronées ou manquantes !", "Erreur saisie ticket", JOptionPane.ERROR_MESSAGE);
                } else {
                    String infos = "Nom : " + nomTicket.getText() + "\n";
                    infos += "Date : " + dateTicket.getText() + "\n";
                    infos += "Enseigne : " + enseignes.getSelectedItem() + "\n";
                    int confirm = JOptionPane.showConfirmDialog(mainPanel, "Ces informations vous conviennent-elles ?\n" + infos, "Confirmer ?", JOptionPane.YES_NO_OPTION);

                    if (confirm == JOptionPane.YES_OPTION) {
                        Command create = new CreateTicket(rep, (EnseigneId)enseignes.getSelectedItem(), dateTicket.getText());
                    }
                }
            }
        });

        this.dateTicket.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                changedUpdate(e);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                changedUpdate(e);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                try {
                    String actualInput = e.getDocument().getText(0, e.getDocument().getLength());
                    if (isValidDate(actualInput)) {
                        System.out.println(actualInput + " is valid");
                        labelDateTicket.setIcon(new ImageIcon());
                    } else {

                    }
                } catch (BadLocationException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    private void initGUI() {
        this.labelEnseigneTicket = new JLabel("Quelle enseigne");
        this.labelNomTicket = new JLabel("Nom du ticket");
        this.labelDateTicket = new JLabel("Date du ticket");

        this.enseignes = new JComboBox<>();
        for (EnseigneId ens : EnseigneId.values()) {
            enseignes.addItem(ens);
        }

        this.nomTicket = new JTextField();
        this.dateTicket = new JTextField();
        this.addButton = new JButton("Créer le ticket");

        mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        Insets insetsLabel = new Insets(2, 25, 5, 25);
        Insets insetsText = new Insets(2, 25, 25, 25);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.ipady = 15;
        c.ipadx = 15;

        Component[] components = {labelNomTicket, nomTicket,
                                  labelDateTicket, dateTicket,
                                  labelEnseigneTicket, enseignes,
                                  addButton};

        for (int i = 0; i < components.length; i++) {
            if (i == components.length - 1) {
                c.ipadx = 5;
                c.ipady = 5;
                c.weightx = 0.5;
                c.fill = GridBagConstraints.CENTER;
            }

            c.gridy = i;
            c.insets = (i % 2 == 0) ? insetsLabel : insetsText;
            mainPanel.add(components[i], c);
        }

        add(mainPanel);
    }

    private boolean isValidDate(String input) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        format.setLenient(false);
        try {
            format.parse(input);
            return true;
        } catch (ParseException e) {
            // do nothing
        }

        return false;
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            TicketRepository rep = new TicketRepositoryInMemory();
            CreateTicketWindow window = new CreateTicketWindow("Test", rep);
            window.setVisible(true);
        });
    }
}
