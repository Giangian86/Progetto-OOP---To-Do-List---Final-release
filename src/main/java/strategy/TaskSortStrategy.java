package strategy;

import model.Task;
import java.util.List;

/**
 * {@code TaskSortStrategy} è un'interfaccia che definisce una strategia
 * per ordinare una lista di {@link Task}.
 * 
 * <p>Fa parte del pattern Strategy e viene implementata da classi
 * come {@link OrdinaPerPriorita}, {@link OrdinaPerScadenza} e {@link OrdinaPerCompletamento}.</p>
 * 
 * @author Gian Luca Baccani
 * @version 1.0
 */
public interface TaskSortStrategy {

    /**
     * Ordina la lista dei task secondo una logica specifica.
     *
     * @param tasks la lista di task da ordinare
     * @return una nuova lista ordinata
     */
    List<Task> ordina(List<Task> tasks);
}
