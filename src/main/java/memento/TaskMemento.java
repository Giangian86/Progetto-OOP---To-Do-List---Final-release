package memento;

import model.Task;

/**
 * {@code TaskMemento} rappresenta uno stato salvato di un oggetto {@link Task}.
 * 
 * <p>Fa parte del Memento Pattern, consentendo il salvataggio e il ripristino
 * dello stato di una task in un determinato momento.</p>
 * 
 * @see TaskCaretaker
 * @see Task
 * 
 * @author Gian Luca Baccani
 * @version 1.0
 */
public class TaskMemento {

    /** Lo stato immutabile salvato del task */
    private final Task stato;

    /**
     * Crea un memento con lo stato corrente del task.
     *
     * @param stato il task da salvare
     */
    public TaskMemento(Task stato) {
        this.stato = stato;
    }

    /**
     * Restituisce lo stato salvato del task.
     *
     * @return il task salvato
     */
    public Task getStatoSalvato() {
        return stato;
    }
}
