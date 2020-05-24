package main.fr.ubordeaux.ui.swing.elements;

import main.fr.ubordeaux.domain.model.TicketInterface;

import javax.swing.*;
import javax.swing.plaf.BorderUIResource;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;

/**
 * Project :
 * Class : TicketPreviewPanel
 * Created at 06/03/20 at 15:24
 * Author : rlasvenes
 */

public class TicketPreviewPanel extends JPanel {

    private final static int JPANEL_SIZE = 200;
    private final JFrame parent;
    private JLabel ticketLabel;
    private JLabel dateLabel;
    private JLabel totalTicketLabel;
    private TicketInterface ticket;

    private final String DATE_PATTERN = "dd/MM/yyyy";
    private SimpleDateFormat dateFormatter;

    private Color defaultColor;
    private Color hoverColor;

    public TicketPreviewPanel(LayoutManager layout, boolean isDoubleBuffered, TicketInterface ticket, JFrame parent) {
        super(layout, isDoubleBuffered);
        this.ticket = ticket;
        this.parent = parent;
        initComponents();
        initUI();
        initListeners();
    }

    public TicketPreviewPanel(LayoutManager layout) {
        this(layout, true, null, null);
    }

    public TicketPreviewPanel(boolean isDoubleBuffered) {
        this(null, isDoubleBuffered, null, null);
    }

    public TicketPreviewPanel(TicketInterface ticket, JFrame parent) {
        this(null, true, ticket, parent);
    }

    private void initComponents() {
        dateFormatter = new SimpleDateFormat(DATE_PATTERN);

        ticketLabel = new JLabel("Ticket du ");
        ticketLabel.setHorizontalAlignment(JLabel.CENTER);
        ticketLabel.setFont(new Font("Sego UI", Font.BOLD, 20));
        ticketLabel.setForeground(new Color(4, 79, 103));

        dateLabel = new JLabel(parseDate());
        dateLabel.setFont(new Font("Sego UI", Font.PLAIN, 32));

        totalTicketLabel = new JLabel(ticket.total().valeur() + "€");
        totalTicketLabel.setFont(new Font("Sego UI", Font.BOLD, 12));

        add(ticketLabel);
        add(dateLabel);
        add(totalTicketLabel);
    }

    private void initUI() {
        defaultColor = getBackground();
        hoverColor = Color.gray;

        setLayout(new GridBagLayout());
        setBorder(new BorderUIResource.LineBorderUIResource(Color.BLACK));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(ticketLabel, gbc);
        gbc.gridy++;
        add(dateLabel, gbc);
        gbc.gridy++;
        add(totalTicketLabel, gbc);
    }

    private String parseDate() {
        return dateFormatter.format(ticket.date());
    }

    public void setDate(TicketInterface ticket) {
        this.ticket = ticket;
    }

    private void initListeners() {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
//                super.mouseEntered(e);
                setCursor(new Cursor(Cursor.HAND_CURSOR));
                setBackground(hoverColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                setBackground(defaultColor);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println("Clicked on ticket : " + ticket);
                showTicketInformation(ticket);
            }
        });
    }

    private void showTicketInformation(TicketInterface ticket) {
        ViewTicketModal modal = new ViewTicketModal(ticket);
        modal.setModal(true);
        modal.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        modal.setSize(500, 500);
        modal.setLocationRelativeTo(null);
        modal.setVisible(true);
    }

    @Override
    public Dimension getMinimumSize() {
        return super.getPreferredSize();
    }

    @Override
    public Dimension getMaximumSize() {
        return super.getPreferredSize();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(JPANEL_SIZE, JPANEL_SIZE);
    }
}
