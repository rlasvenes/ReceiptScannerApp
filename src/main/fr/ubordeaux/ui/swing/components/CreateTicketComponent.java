package main.fr.ubordeaux.ui.swing.components;

import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.components.DateTimePicker;
import com.github.lgooddatepicker.components.TimePickerSettings;
import main.fr.ubordeaux.application.Command;
import main.fr.ubordeaux.application.CreateTicket;
import main.fr.ubordeaux.domain.model.TicketRepository;
import main.fr.ubordeaux.domain.type.EnseigneId;
import main.fr.ubordeaux.infrastructure.inmemory.TicketRepositoryInMemory;
import main.fr.ubordeaux.ui.swing.MainAppWindow;
import main.fr.ubordeaux.ui.swing.elements.Showable;
import main.fr.ubordeaux.ui.swing.listeners.KeySwitchViewListener;
import main.fr.ubordeaux.ui.swing.signal.ChangeViewComponentSignal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Project :
 * Class : CreateTicketComponent
 * Created at 26/12/19 at 17:57
 * Author : rlasvenes
 */

public class CreateTicketComponent extends JPanel implements Showable {

    // TODO Faire du "tri" également dans cette classe

    /**
     * The label displaying the name of the ticket
     */
    private JLabel labelNomTicket;

    /**
     * The textfield where the user will type the name of the ticket
     */
    private JTextField nomTicket;

    /**
     * The label displaying the supermarket sign
     */
    private JLabel labelEnseigneTicket;

    /**
     * The container with all the supermarket signs
     */
    private JComboBox<EnseigneId> enseignes;

    /**
     * The label displaying the date for the ticket
     */
    private JLabel labelDateTicket;

    /**
     * The component that will display the date and time for the ticket
     */
    private DateTimePicker dateTimePicker;

    /**
     * The settings for the date part
     */
    private DatePickerSettings datePickerSettings;

    /**
     * The settings for the time part
     */
    private TimePickerSettings timePickerSettings;

    /**
     * The button that will trigger the create action for the ticket with all the entered parameters
     */
    private JButton addButton;

    /**
     * The repository for the create/update/delete actions
     */
    private TicketRepository rep;

    /**
     *  The index corresponding to this JPanel in particular
     */
    private String index;

    /**
     * The parent (JFrame) of this component
     */
    private JFrame parent;

    /**
     * The main layout in which all the components are added
     */
    private CardLayout layout;

    public CreateTicketComponent(JFrame parent, TicketRepository rep, String index, CardLayout layout) {
        super(new GridBagLayout());
        this.setSize(920, 600);

        setParent(parent);
        setRep(rep);
        setIndex(index);
        setLayout(layout);

        initComponents();
        initListeners();
    }

    private void setRep(TicketRepository rep) {
        this.rep = rep;
    }

    private void setIndex(String index) {
        this.index = index;
    }

    private void setParent(JFrame parent) {
        this.parent = parent;
    }

    private void setLayout(CardLayout layout) {
        this.layout = layout;
    }

    private void initComponents() {
        this.setFocusable(false);
        this.labelEnseigneTicket = new JLabel("Quelle enseigne");
        this.labelNomTicket = new JLabel("Nom du ticket");
        this.labelDateTicket = new JLabel("Date du ticket");

        this.enseignes = new JComboBox<>();
        for (EnseigneId ens : EnseigneId.values()) {
            enseignes.addItem(ens);
        }

        this.nomTicket = new JTextField();
        this.datePickerSettings = new DatePickerSettings();
        this.datePickerSettings.setFormatForDatesCommonEra("dd/MM/yyyy");
        this.datePickerSettings.setFormatForDatesBeforeCommonEra("dd/MM/uuuu");
        this.dateTimePicker = new DateTimePicker(datePickerSettings, timePickerSettings);
        this.addButton = new JButton("Créer le ticket");

        GridBagConstraints c = new GridBagConstraints();

        Insets insetsLabel = new Insets(2, 25, 5, 25);
        Insets insetsText = new Insets(2, 25, 25, 25);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.ipady = 15;
        c.ipadx = 15;

        Component[] components = {labelNomTicket, nomTicket,
                                  labelDateTicket, dateTimePicker,
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
            this.add(components[i], c);
        }
    }

    private void initListeners() {
        ChangeViewComponentSignal observable = new ChangeViewComponentSignal();
        observable.addObserver(((MainAppWindow)parent).getListPanel());

        this.addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isNameEmpty() || isDateEmpty() || isTimeEmpty()) {
                    JOptionPane.showMessageDialog(CreateTicketComponent.this, "Données érronées ou manquantes !", "Erreur saisie ticket", JOptionPane.ERROR_MESSAGE);
                } else {
                    String infos = getInfos();
                    int confirm = JOptionPane.showConfirmDialog(CreateTicketComponent.this,
                            "Ces informations vous conviennent-elles ?\n" + infos,
                            "Confirmer ?",
                            JOptionPane.YES_NO_OPTION);

                    if (confirm == JOptionPane.YES_OPTION) {
                        Command create = new CreateTicket(rep, (EnseigneId)enseignes.getSelectedItem(), getDate());
                        create.execute();
                        observable.changeData(42);
                    }
                }
            }
        });

        listAllComponentsIn(this);
        addKeyListener(new KeySwitchViewListener(parent.getLayout(), parent.getContentPane()));
    }

    public void listAllComponentsIn(Container parent)
    {
        for (Component c : parent.getComponents())
        {
            System.out.println(c.toString());
            c.addKeyListener(new KeySwitchViewListener(layout, this.parent.getContentPane()));
            System.out.printf("Added \"%s\" layout of \"%s\" on %s", this.parent.getLayout(), this.parent.getContentPane(), c);

            if (c instanceof Container)
                listAllComponentsIn((Container)c);
        }
    }

    private boolean isNameEmpty() {
        return nomTicket.getText().trim().length() == 0;
    }

    public boolean isDateEmpty() {
        return dateTimePicker.getDatePicker().getText().trim().length() == 0;
    }

    public boolean isTimeEmpty() {
        return dateTimePicker.getTimePicker().getText().trim().length() == 0;
    }

    public String getInfos() {
        StringBuilder out = new StringBuilder();
        out.append("Nom ticket : ");
        out.append(nomTicket.getText());
        out.append("\n");

        out.append("Date : ");
        out.append(dateTimePicker.getDatePicker().getText());
        out.append("\n");

        out.append("Enseigne : ");
        out.append(enseignes.getSelectedItem());
        out.append("\n");

        return out.toString();
    }

    public String getDate() {
        return dateTimePicker.getDatePicker().getText() + " " + dateTimePicker.getTimePicker().getText();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            TicketRepository rep = new TicketRepositoryInMemory();
            CreateTicketComponent window = new CreateTicketComponent(null, rep, "1", null);
            window.setVisible(true);
        });
    }

    @Override
    public Object getIndex() {
        return index;
    }
}
