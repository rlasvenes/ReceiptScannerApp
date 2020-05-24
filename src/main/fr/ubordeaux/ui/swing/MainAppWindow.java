package main.fr.ubordeaux.ui.swing;

import main.fr.ubordeaux.domain.model.TicketRepository;
import main.fr.ubordeaux.infrastructure.inmemory.TicketRepositoryInMemory;
import main.fr.ubordeaux.ui.swing.components.CreateTicketComponent;
import main.fr.ubordeaux.ui.swing.components.ListTicketComponent;
import main.fr.ubordeaux.ui.swing.components.MainMenuComponent;
import main.fr.ubordeaux.ui.swing.components.ModifyTicketComponent;
import main.fr.ubordeaux.ui.swing.listeners.KeySwitchViewListener;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Project :
 * Class : MainAppWindow
 * Created at 26/12/19 at 16:18
 * Author : rlasvenes
 */

public class MainAppWindow extends JFrame implements Observer {
    private MainMenuComponent mainMenu;
    private CreateTicketComponent createPanel;
    private ListTicketComponent listPanel;
    private ModifyTicketComponent modifyPanel;

    private CardLayout cl;

    private TicketRepository repository;

    public MainAppWindow(String title, TicketRepository repository) {
        super(title);
        this.repository = repository;
        initialize();
        initComponents();
        initListeners();
    }

    private void initListeners() {
        System.out.println("Initializing listeners...");
        addKeyListener(new KeySwitchViewListener(cl, getContentPane()));
    }

    public void listAllComponentsIn(Container parent)
    {
        for (Component c : parent.getComponents())
        {
            System.out.println(c.toString());

            if (c instanceof Container)
                listAllComponentsIn((Container)c);
        }
    }

    private void initialize() {
        this.setFocusable(true);
        this.setSize(1220, 920);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    private void initComponents() {
        cl = new CardLayout();
        this.setLayout(cl);

        mainMenu    = new MainMenuComponent     (this,              "0");
        listPanel   = new ListTicketComponent   (this, repository,  "2");
        createPanel = new CreateTicketComponent (this, repository,  "1", cl);
        modifyPanel = new ModifyTicketComponent (this,              "3");

        listPanel.setRepository(repository);

        this.add(mainMenu, mainMenu.getIndex());
        this.add(createPanel, createPanel.getIndex());
        this.add(listPanel, listPanel.getIndex());
        this.add(modifyPanel, modifyPanel.getIndex());
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            MainAppWindow window = new MainAppWindow("MainAppWindow.java", new TicketRepositoryInMemory());
            window.setVisible(true);
        });
    }

    public MainMenuComponent getMainMenu() {
        return mainMenu;
    }

    public CreateTicketComponent getCreatePanel() {
        return createPanel;
    }

    public ListTicketComponent getListPanel() {
        return listPanel;
    }

    public ModifyTicketComponent getModifyPanel() {
        return modifyPanel;
    }

    @Override
    public void update(Observable o, Object arg) {
        // TODO Changer la vue (card layout) de manière propre et dynamique
        System.out.println("Update received from... " + o.toString() + " with data : " + arg);
        cl.show(this.getContentPane(), String.valueOf(arg));
    }
}
