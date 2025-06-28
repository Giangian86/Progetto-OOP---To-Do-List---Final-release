package memento;

import java.util.Stack;

/**
 * {@code TaskCaretaker} gestisce la cronologia degli stati di {@link model.Task}
 * salvati come {@link TaskMemento}, permettendo il ripristino tramite operazioni di undo.
 * 
 * <p>Fa parte del Memento Pattern, dove agisce da "caretaker" (custode).</p>
 * 
 * @see TaskMemento
 * @see model.Task
 * 
 * @author Gian Luca Baccani
 * @version 1.0
 */
public class TaskCaretaker {

    /**
     * Costruttore di default.
     */
    public TaskCaretaker() {
        // Nessuna inizializzazione
    }

    /** Stack che memorizza gli stati precedenti del task */
    private final Stack<TaskMemento> cronologia = new Stack<>();

    /**
     * Salva uno stato nella cronologia.
     *
     * @param stato il memento da salvare
     */
    public void salva(TaskMemento stato) {
        cronologia.push(stato);
    }

    /**
     * Ripristina l'ultimo stato salvato (undo).
     *
     * @return il memento più recente, oppure {@code null} se la cronologia è vuota
     */
    public TaskMemento annulla() {
        if (!cronologia.isEmpty()) {
            return cronologia.pop();
        }
        return null;
    }
}
