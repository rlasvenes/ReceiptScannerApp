package main.fr.ubordeaux.domain.model;

import java.util.List;

/**
 * Project :
 * Class : TicketRepository
 * Created at 24/11/19 at 22:24
 * Author : rlasvenes
 */

public interface TicketRepository {
    public TicketInterface find(int id);
    public List<TicketInterface> findAll();
    public void persist(TicketInterface ticket);
}
