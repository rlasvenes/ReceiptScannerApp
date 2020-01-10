package main.fr.ubordeaux.infrastructure;

import main.fr.ubordeaux.application.Command;
import main.fr.ubordeaux.application.Queue;
import main.fr.ubordeaux.application.Worker;
import main.fr.ubordeaux.domain.exception.GestionTicketException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Project :
 * Class : QueueImpl
 * Created at 26/12/19 at 14:21
 * Author : rlasvenes
 */

public class QueueImpl implements Queue {

    private List<Command> commands;
    private List<Worker> workers;

    public QueueImpl() {
        this.commands = new ArrayList<>();
        this.workers = new ArrayList<>();
    }

    @Override
    public void pushCommand(Command command) {
        this.commands.add(command);
        if (workers.isEmpty()) {
            throw new GestionTicketException("There is no worker to handle command : " + command);
        }

        getRandomWorker().handle(command);
        this.commands.remove(command);
    }

    @Override
    public void addWorker(Worker worker) {
        this.workers.add(worker);
    }

    private Worker getRandomWorker() {
        return workers.get(new Random().nextInt(workers.size()));
    }
}
