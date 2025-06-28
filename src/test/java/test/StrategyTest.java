package test;

import model.*;
import strategy.*;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe di test per le implementazioni dell'interfaccia {@link TaskSortStrategy},
 * che forniscono strategie di ordinamento per i task in base a diversi criteri.
 * <p>
 * I test inclusi verificano il corretto funzionamento delle strategie:
 * {@link OrdinaPerScadenza} e {@link OrdinaPerPriorita}.
 * </p>
 * 
 * @author Gian Luca Baccani
 * @version 1.0
 */
public class StrategyTest {

    /**
     * Verifica che la strategia {@link OrdinaPerScadenza} ordini correttamente
     * i task in base alla data di scadenza, dal più prossimo al più lontano.
     *
     * <p>Nel test, t2 ha una data più vicina rispetto a t1, quindi
     * deve comparire per primo.</p>
     */
    @Test
    public void testOrdinaPerScadenza() {
        Task t1 = new Task("A", "Test", LocalDate.of(2025, 7, 3), Priorita.MEDIA);
        Task t2 = new Task("B", "Test", LocalDate.of(2025, 7, 1), Priorita.MEDIA);

        TaskSortStrategy strategy = new OrdinaPerScadenza();
        List<Task> ordinati = strategy.ordina(List.of(t1, t2));

        assertEquals("B", ordinati.get(0).getTitolo());
    }

    /**
     * Verifica che la strategia {@link OrdinaPerPriorita} ordini correttamente
     * i task in base alla priorità, dalla più alta alla più bassa.
     *
     * <p>Nel test, t2 ha priorità ALTA mentre t1 è BASSA: t2 deve venire prima.</p>
     */
    @Test
    public void testOrdinaPerPriorita() {
        Task t1 = new Task("A", "Test", LocalDate.now(), Priorita.BASSA);
        Task t2 = new Task("B", "Test", LocalDate.now(), Priorita.ALTA);

        TaskSortStrategy strategy = new OrdinaPerPriorita();
        List<Task> ordinati = strategy.ordina(List.of(t1, t2));

        assertEquals("B", ordinati.get(0).getTitolo());
    }
}
