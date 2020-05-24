package main.fr.ubordeaux.infrastructure.inmemory;

import main.fr.ubordeaux.domain.exception.GestionTicketException;
import main.fr.ubordeaux.domain.model.TicketInterface;
import main.fr.ubordeaux.domain.model.TicketRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Project :
 * Class : TicketRepositoryInMemory
 * Created at 24/11/19 at 22:31
 * Author : rlasvenes
 */

public class TicketRepositoryInMemory implements TicketRepository {

    private List<TicketInterface> tickets;

    public TicketRepositoryInMemory() {
        this.tickets = new ArrayList<>();
    }

    @Override
    public TicketInterface find(int id) {
        for (TicketInterface ticket : tickets) {
            if (ticket.id() == id) {
                return ticket;
            }
        }
        return null;
    }

    @Override
    public List<TicketInterface> findAll() {
        return new ArrayList<>(tickets);
    }

    @Override
    public void persist(TicketInterface ticket) {
        for (TicketInterface t : tickets) {
            if (t.id() == ticket.id()) {
                throw new GestionTicketException("Cannot persist ticket \"" + ticket.id() + "\", already in list !");
            }
        }
        tickets.add(ticket);
        System.out.println("Tickets size : " + tickets.size());
    }
}
