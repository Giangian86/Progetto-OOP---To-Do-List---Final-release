package strategy;

import model.Task;
import java.util.*;

/**
 * {@code OrdinaPerPriorita} è una strategia di ordinamento che ordina i {@link Task}
 * in base alla loro priorità, dalla più alta alla più bassa.
 * 
 * <p>Implementa l'interfaccia {@link TaskSortStrategy} secondo il pattern Strategy.</p>
 * 
 * @see TaskSortStrategy
 * @see Task
 * @author Gian Luca Baccani
 * @version 1.0
 */
public class OrdinaPerPriorita implements TaskSortStrategy {


    /**
     * Costruttore di default.
     */
    public OrdinaPerPriorita() {
        // Nessuna inizializzazione
    }

    /**
     * Ordina una lista di task in base alla priorità decrescente.
     *
     * @param tasks la lista di task da ordinare
     * @return una nuova lista ordinata per priorità
     */
    public List<Task> ordina(List<Task> tasks) {
        return tasks.stream()
                .sorted(Comparator.comparing(Task::getPriorita).reversed())
                .toList();
    }
}
