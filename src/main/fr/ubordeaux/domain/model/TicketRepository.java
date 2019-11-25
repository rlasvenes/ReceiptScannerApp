package main.fr.ubordeaux.domain.model;

/**
 * Project :
 * Class : TicketRepository
 * Created at 24/11/19 at 22:24
 * Author : rlasvenes
 */

public interface TicketRepository {
    public TicketInterface find(int id);
    public void persist(TicketInterface ticket);
}
