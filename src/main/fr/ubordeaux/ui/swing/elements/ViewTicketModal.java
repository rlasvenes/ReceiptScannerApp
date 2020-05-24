package main.fr.ubordeaux.ui.swing.elements;

import main.fr.ubordeaux.domain.model.EntreeTicketDTO;
import main.fr.ubordeaux.domain.model.TicketInterface;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


/**
 * Project :
 * Class : ViewTicketModal
 * Created at 07/03/2020 at 12:26
 * Author : rlasvenes
 */

public class ViewTicketModal extends JDialog {

    private TicketInterface ticket;
    private JScrollPane scrollPane;
    private JPanel mainPanel;

    private JLabel dateLabel;
    private JLabel enseigneLabel;
    private JLabel prixTotalLabel;

    private final String DATE_PATTERN = "EE dd/MM/yyyy HH:mm";
    private SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);

    public ViewTicketModal(TicketInterface ticket) {
        super();
        this.ticket = ticket;
        initComponents();
    }

    private void initComponents() {
        this.scrollPane = new JScrollPane();
        this.mainPanel = new JPanel(new GridBagLayout());

        this.dateLabel = new JLabel(sdf.format(ticket.date()));
        this.enseigneLabel = new JLabel(ticket.enseigne().label());
        this.prixTotalLabel = new JLabel(getTotal());

        setTitle(getTicketTitle());
        setLayout(new BorderLayout());
        add(scrollPane);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        mainPanel.add(dateLabel, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridy++;
        mainPanel.add(enseigneLabel, gbc);

        for (EntreeTicketDTO entree : ticket.aliments()) {
            gbc.gridy++;
            mainPanel.add(new JLabel(getFullTicketLine(entree)), gbc);
        }
        gbc.gridy++;
        mainPanel.add(prixTotalLabel, gbc);

        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.getVerticalScrollBar().setUnitIncrement(20);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setViewportView(mainPanel);

        getContentPane().add(scrollPane, BorderLayout.CENTER);
    }

    private String getTicketTitle() {
        StringBuilder out = new StringBuilder();
        out.append("Ticket du : ");
        out.append(sdf.format(ticket.date()));
        return out.toString();
    }

    private String getTotal() {
        StringBuilder out = new StringBuilder();

        out.append("Total : ");
        out.append(ticket.total().valeur());
        out.append("€");
        return out.toString();
    }

    private String getFullTicketLine(EntreeTicketDTO entree) {
        StringBuilder out = new StringBuilder();
        out.append(entree.aliment().nom());
        out.append(" ");
        out.append(entree.aliment().prixUnitaire());
        out.append(" * ");
        out.append(entree.quantite());
        out.append(" : ");
        out.append(entree.prixTotal());
        out.append("€");

        return out.toString();
    }
}
