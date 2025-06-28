package decorator;

import model.Task;

/**
 * {@code CompletatoDecorator} è un decoratore concreto che indica visivamente
 * che un {@link Task} è stato completato.
 * 
 * <p>Applica il completamento al task e aggiunge la stringa "[COMPLETATO]" alla sua descrizione.</p>
 * 
 * @see TaskDecorator
 * @see Task
 * @author Gian Luca Baccani
 * @version 1.0
 */
public class CompletatoDecorator extends TaskDecorator {

    /**
     * Costruttore del decoratore. Segna automaticamente il task come completato.
     *
     * @param task il task da decorare
     */
    public CompletatoDecorator(Task task) {
        super(task);
        this.task.completaTask();
    }

    /**
     * Rende il task logicamente completato.
     *
     * @return true sempre, poiché questo decoratore implica il completamento
     */
    @Override
    public boolean isCompletato() {
        return task.isCompletato();
    }

    /**
     * Restituisce la rappresentazione testuale del task con indicazione di completamento.
     *
     * @return la stringa descrittiva con "[COMPLETATO]"
     */
    @Override
    public String toString() {
        return task.toString() + " [COMPLETATO]";
    }
}
