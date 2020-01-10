package main.fr.ubordeaux.application;

import main.fr.ubordeaux.domain.model.Ticket;
import main.fr.ubordeaux.domain.model.TicketRepository;

/**
 * Project :
 * Class : Command
 * Created at 23/12/19 at 19:02
 * Author : rlasvenes
 */

public abstract class Command {
    private TicketRepository rep;
    private Ticket ticket;

    Command(TicketRepository rep, Ticket ticket) {
        this(rep);
        this.ticket = ticket;
    }

    Command(TicketRepository rep) {
        this.rep = rep;
    }

    public abstract void execute();

    public TicketRepository getRep() {
        return rep;
    }

    public Ticket getTicket() {
        return ticket;
    }
}
