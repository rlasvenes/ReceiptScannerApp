package main.fr.ubordeaux.ui.swing.components;

import com.github.lgooddatepicker.zinternaltools.WrapLayout;
import main.fr.ubordeaux.domain.model.Aliment;
import main.fr.ubordeaux.domain.model.TicketInterface;
import main.fr.ubordeaux.domain.model.TicketRepository;
import main.fr.ubordeaux.domain.service.TicketBuilder;
import main.fr.ubordeaux.domain.type.EnseigneId;
import main.fr.ubordeaux.domain.type.Prix;
import main.fr.ubordeaux.domain.type.Quantite;
import main.fr.ubordeaux.ui.swing.elements.Showable;
import main.fr.ubordeaux.ui.swing.elements.TicketPreviewPanel;

import javax.swing.*;
import java.util.Comparator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Project :
 * Class : ListTicketComponent
 * Created at 05/03/20 at 12:07
 * Author : rlasvenes
 */

public class ListTicketComponent extends JScrollPane implements Showable, Observer {

    private JPanel mainPanel;
    private String index;
    private JFrame parent;

    private JButton backButton;

    private List<TicketInterface> tickets;
    private TicketRepository repo;

    public ListTicketComponent(JFrame parent, TicketRepository repository, String index) {
        super();
        this.index = index;
        this.parent = parent;
        this.repo = repository;
        initComponents();
        initGUI();
    }

    private void initComponents() {
        WrapLayout wl = new WrapLayout(WrapLayout.CENTER, 25, 25);
        this.mainPanel = new JPanel(wl);

        TicketInterface ticket1 = new TicketBuilder().enseigne(EnseigneId.INTERMARCHE).date("06/03/2020 16:00").build();
        TicketInterface ticket2 = new TicketBuilder().enseigne(EnseigneId.LECLERC).date("25/11/2019 11:33").build();
        TicketInterface ticket3 = new TicketBuilder().enseigne(EnseigneId.AUCHAN).date("14/01/2020 12:35").build();
        TicketInterface ticket4 = new TicketBuilder().enseigne(EnseigneId.LIDLE).date("30/06/2018 17:20").build();

        ticket1.ajouterAliment(new Aliment("Tomate rouge", new Prix(2.54)), new Quantite(8));
        repo.persist(ticket1);
        repo.persist(ticket2);
        repo.persist(ticket3);
        repo.persist(ticket4);
    }

    private void initGUI() {
        List<TicketInterface> tickets = getSortedTickets();

        for (int i = 0; i < tickets.size(); i++) {
            mainPanel.add(new TicketPreviewPanel(tickets.get(i % tickets.size()), parent));
        }

        add(mainPanel);

        setViewportView(mainPanel);
        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        getVerticalScrollBar().setUnitIncrement(20);
        setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    }

    public void setRepository(TicketRepository repository) {
        this.repo = repository;
    }

    private List<TicketInterface> getSortedTickets() {
        List<TicketInterface> tickets = repo.findAll();
        tickets.sort(Comparator.comparing(TicketInterface::date));
        return tickets;
    }

    private void updateTickets() {
        mainPanel.removeAll(); // beurk !!
        List<TicketInterface> tickets = getSortedTickets();
        for (int i = 0; i < tickets.size(); i++) {
            mainPanel.add(new TicketPreviewPanel(tickets.get(i % tickets.size()), parent));
        }
    }

    @Override
    public Object getIndex() {
        return index;
    }

    @Override
    public void update(Observable o, Object arg) {
//        System.out.printf("We have %d tickets now \n", tickets.size());
        System.out.println("UPDATE WITH " + o + " " + arg);
        updateTickets();
    }
}
