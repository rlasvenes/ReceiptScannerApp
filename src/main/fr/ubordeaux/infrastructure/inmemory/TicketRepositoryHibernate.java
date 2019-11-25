package main.fr.ubordeaux.infrastructure.inmemory;

import main.fr.ubordeaux.domain.model.TicketInterface;
import main.fr.ubordeaux.domain.model.TicketRepository;

/**
 * Project :
 * Class : TicketRepositoryHibernate
 * Created at 24/11/19 at 22:31
 * Author : rlasvenes
 */

public class TicketRepositoryHibernate implements TicketRepository {

    @Override
    public TicketInterface find(int id) {
        return null;
    }

    @Override
    public void persist(TicketInterface ticket) {
        
    }
}
