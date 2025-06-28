package decorator;

import model.Task;

/**
 * {@code UrgenteDecorator} è un decoratore concreto che evidenzia un {@link Task}
 * come urgente, aggiungendo il prefisso "[URGENTE]" alla sua rappresentazione.
 * 
 * <p>Non modifica lo stato logico del task, solo la sua rappresentazione visiva.</p>
 * 
 * @see TaskDecorator
 * @see Task
 * @see CompletatoDecorator
 * 
 * @author Gian Luca Baccani
 * @version 1.0
 */
public class UrgenteDecorator extends TaskDecorator {

    /**
     * Costruttore del decoratore urgente.
     *
     * @param task il task da decorare
     */
    public UrgenteDecorator(Task task) {
        super(task);
    }

    /**
     * Restituisce la rappresentazione testuale del task con indicazione di urgenza.
     *
     * @return la stringa descrittiva con "[URGENTE]"
     */
    @Override
    public String toString() {
        return "[URGENTE] " + task.toString();
    }
}
