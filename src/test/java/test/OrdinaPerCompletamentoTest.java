package test;

import model.Priorita;
import model.Task;
import strategy.OrdinaPerCompletamento;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe di test per {@link OrdinaPerCompletamento}, strategia di ordinamento
 * che posiziona i task non completati prima di quelli completati.
 * <p>
 * Il test verifica che i task vengano ordinati correttamente in base allo
 * stato di completamento.
 * </p>
 * 
 * @author Gian Luca Baccani
 * @version 1.0
 */
public class OrdinaPerCompletamentoTest {

    /**
     * Verifica che l'algoritmo {@link OrdinaPerCompletamento#ordina(List)} ordini
     * correttamente i task mettendo quelli non completati prima di quelli completati.
     *
     * <p>Nel test, t2 è completato mentre t1 no. Dopo l'ordinamento,
     * t1 dovrebbe comparire prima.</p>
     */
    @Test
    public void testOrdinaPerCompletamento() {
        Task t1 = new Task("A", "desc", LocalDate.now(), Priorita.ALTA);
        Task t2 = new Task("B", "desc", LocalDate.now(), Priorita.BASSA);
        t2.completaTask();

        List<Task> tasks = new ArrayList<>();
        tasks.add(t2);
        tasks.add(t1);

        List<Task> ordinati = new OrdinaPerCompletamento().ordina(tasks);
        assertEquals("A", ordinati.get(0).getTitolo()); // Non completato prima
    }
}
