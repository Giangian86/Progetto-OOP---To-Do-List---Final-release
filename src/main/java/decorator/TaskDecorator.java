package decorator;

import model.Task;

/**
 * {@code TaskDecorator} è una classe astratta che implementa il Decorator Pattern
 * per estendere dinamicamente le funzionalità di un oggetto {@link Task}.
 * 
 * <p>Tutte le sottoclassi devono definire nuove funzionalità senza modificare la struttura della classe base.</p>
 * 
 * @see CompletatoDecorator
 * @see UrgenteDecorator
 * @see Task
 * 
 * @author Gian Luca Baccani
 * @version 1.0
 */
public abstract class TaskDecorator extends Task {

    /** Il task decorato (a cui si delegano i comportamenti base) */
    protected Task task;

    /**
     * Costruttore del decoratore.
     *
     * @param task il task da decorare
     */
    public TaskDecorator(Task task) {
        super(task.getTitolo(), task.getDescrizione(), task.getScadenza(), task.getPriorita());
        this.task = task;
    }

    /**
     * Restituisce la rappresentazione testuale del task decorato.
     *
     * @return la stringa rappresentativa
     */
    @Override
    public String toString() {
        return task.toString();
    }
}
