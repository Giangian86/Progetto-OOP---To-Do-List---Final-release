package strategy;

import model.Task;
import java.util.*;

/**
 * {@code OrdinaPerScadenza} è una strategia di ordinamento che ordina i {@link Task}
 * in base alla data di scadenza, dalla più prossima alla più lontana.
 * 
 * <p>Implementa l'interfaccia {@link TaskSortStrategy} secondo il pattern Strategy.</p>
 * 
 * @see TaskSortStrategy
 * @see Task
 * @author Gian Luca Baccani
 * @version 1.0
 */
public class OrdinaPerScadenza implements TaskSortStrategy {

    /**
     * Costruttore di default.
     */
    public OrdinaPerScadenza() {
        // Nessuna inizializzazione
    }

    /**
     * Ordina una lista di task per data di scadenza crescente.
     *
     * @param tasks la lista di task da ordinare
     * @return una nuova lista ordinata per scadenza
     */
    public List<Task> ordina(List<Task> tasks) {
        return tasks.stream()
                .sorted(Comparator.comparing(Task::getScadenza))
                .toList();
    }
}
