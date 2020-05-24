package main.fr.ubordeaux.ui.swing.components;

import main.fr.ubordeaux.ui.swing.MainAppWindow;
import main.fr.ubordeaux.ui.swing.elements.Showable;
import main.fr.ubordeaux.ui.swing.signal.ChangeViewComponentSignal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import static main.fr.ubordeaux.ui.swing.Layouts.Layouts.*;

/**
 * Project :
 * Class : MainMenuComponent
 * Created at 04/03/20 at 22:04
 * Author : rlasvenes
 */

public class MainMenuComponent extends JPanel implements Showable {
    // TODO Faire un peu de "tri" dans cette classe afin de rendre les appels plus propre
    private JLabel mainMenuLabel;
    private JPanel mainPanel;

    private JButton createButton;
    private JButton listButton;
    private JButton addFoodButton;

    private final JFrame parent;
    private String index;

    private final static int PADDING = 10;
    private final static String TITLE_MENU = "Application de ticket";
    private final static String VERSION = "v1.0";

    public MainMenuComponent(JFrame parent, String index) {
        super(new GridBagLayout());
        this.parent = parent;
        this.index = index;
        initComponents();
        initListeners();
    }

    private void initComponents() {
        this.createButton = new JButton("Créer un ticket");
        this.listButton = new JButton("Liste des tickets");
        this.addFoodButton = new JButton("Modifier un ticket");

        this.mainMenuLabel = new JLabel(TITLE_MENU + " " + VERSION);
        this.mainMenuLabel.setFont(new Font("Sego UI", Font.PLAIN, 76));

        this.mainPanel = new JPanel(new GridBagLayout());
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(PADDING, PADDING, PADDING, PADDING);
        add(mainMenuLabel, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridy++;
        mainPanel.add(createButton, gbc);
        gbc.gridy++;
        mainPanel.add(listButton, gbc);
        gbc.gridy++;
        mainPanel.add(addFoodButton, gbc);

        add(mainPanel, gbc);
    }

    private void initListeners() {
        final ChangeViewComponentSignal observable = new ChangeViewComponentSignal();
        observable.addObserver((MainAppWindow)parent);

        this.createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                observable.changeData(CREATE_TICKET.id());
            }
        });

        this.addFoodButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                observable.changeData(MODIFY_TICKET.id());
            }
        });

        this.listButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                observable.changeData(LIST_TICKET.id());
            }
        });
    }

    @Override
    public Object getIndex() {
        return index;
    }
}
