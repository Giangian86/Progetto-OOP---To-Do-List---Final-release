package strategy;

import model.Task;
import java.util.List;
import java.util.Comparator;
import java.util.stream.Collectors;

/**
 * {@code OrdinaPerCompletamento} è una strategia di ordinamento che ordina i {@link Task}
 * in base al loro stato di completamento.
 * <p>Le task non completate vengono ordinate prima di quelle completate.</p>
 * 
 * <p>Implementa l'interfaccia {@link TaskSortStrategy} secondo il pattern Strategy.</p>
 * 
 * @see TaskSortStrategy
 * @see Task
 * @author Gian Luca Baccani
 * @version 1.0
 */
public class OrdinaPerCompletamento implements TaskSortStrategy {

    /**
     * Costruttore di default.
     */
    public OrdinaPerCompletamento() {
        // Nessuna inizializzazione
    }

    /**
     * Ordina una lista di task per stato di completamento: incompleti prima, completi dopo.
     *
     * @param lista la lista di task da ordinare
     * @return una nuova lista ordinata per stato di completamento
     */
    @Override
    public List<Task> ordina(List<Task> lista) {
        return lista.stream()
                .sorted(Comparator.comparing(Task::isCompletato))
                .collect(Collectors.toList());
    }
}
