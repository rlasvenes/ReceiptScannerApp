package main.fr.ubordeaux.application;

public interface Queue {
    public void pushCommand(Command command);

    public void addWorker(Worker worker);
}
