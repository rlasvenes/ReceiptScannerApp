package main.fr.ubordeaux.infrastructure;

import main.fr.ubordeaux.application.Command;
import main.fr.ubordeaux.application.Worker;

/**
 * Project :
 * Class : WorkerImpl
 * Created at 26/12/19 at 14:20
 * Author : rlasvenes
 */

public class WorkerImpl implements Worker {

    @Override
    public void handle(Command command) {
        command.execute();
    }
}
