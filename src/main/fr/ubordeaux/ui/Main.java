package main.fr.ubordeaux.ui;

import main.fr.ubordeaux.application.Command;
import main.fr.ubordeaux.application.CreateTicket;
import main.fr.ubordeaux.application.Queue;
import main.fr.ubordeaux.application.Worker;
import main.fr.ubordeaux.domain.model.TicketRepository;
import main.fr.ubordeaux.domain.type.EnseigneId;
import main.fr.ubordeaux.infrastructure.QueueImpl;
import main.fr.ubordeaux.infrastructure.WorkerImpl;
import main.fr.ubordeaux.infrastructure.inmemory.TicketRepositoryInMemory;

/**
 * Project :
 * Class : Main
 * Created at 26/12/19 at 15:16
 * Author : rlasvenes
 */

public class Main {

    public static void main(String[] args) {
        System.out.println("HELLO THERE !");

        TicketRepository rep = new TicketRepositoryInMemory();
        Queue queue = new QueueImpl();
        Worker worker = new WorkerImpl();

        queue.addWorker(worker);

        Command command1 = new CreateTicket(rep, EnseigneId.LECLERC, "25/12/2019 12:00");
        Command command2 = new CreateTicket(rep, EnseigneId.AUCHAN, "25/12/2019 12:00");
        Command command3 = new CreateTicket(rep, EnseigneId.INTERMARCHE, "25/12/2019 12:00");
        Command command4 = new CreateTicket(rep, EnseigneId.LIDLE, "25/12/2019 12:00");

        queue.pushCommand(command1);
        queue.pushCommand(command2);
        queue.pushCommand(command3);
        queue.pushCommand(command4);


    }
}
