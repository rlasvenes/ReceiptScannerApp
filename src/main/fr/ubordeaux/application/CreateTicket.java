package main.fr.ubordeaux.application;

import main.fr.ubordeaux.domain.model.Aliment;
import main.fr.ubordeaux.domain.model.Ticket;
import main.fr.ubordeaux.domain.model.TicketInterface;
import main.fr.ubordeaux.domain.model.TicketRepository;
import main.fr.ubordeaux.domain.service.TicketBuilder;
import main.fr.ubordeaux.domain.type.EnseigneId;
import main.fr.ubordeaux.domain.type.Prix;
import main.fr.ubordeaux.domain.type.Quantite;

/**
 * Project :
 * Class : CreateTicket
 * Created at 26/12/19 at 14:28
 * Author : rlasvenes
 */

public class CreateTicket extends Command {

    private final EnseigneId enseigne;
    private final String date;

    /**
     *
     * @param rep
     * @param enseigne
     * @param date La date doit être au format \"dd/MM/yyyy HH:mm\"
     */
    public CreateTicket(TicketRepository rep, EnseigneId enseigne, String date) {
        super(rep);
        this.enseigne = enseigne;
        this.date = date;
    }

    @Override
    public void execute() {
        TicketInterface ticket = new TicketBuilder()
                            .enseigne(enseigne)
                            .date(date)
                            .build();
        getRep().persist(ticket);
        System.out.println("Ticket " + ticket + " [" + ticket.id() + "] created successfully !");
        ticket.ajouterAliment(new Aliment("Tomate", new Prix(2.52)),  new Quantite(2));
        System.out.println("Ticket " + ticket + " [" + ticket.id() + "] updated successfully !");
    }
}
