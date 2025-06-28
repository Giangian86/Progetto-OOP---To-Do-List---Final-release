package test;

import model.*;
import service.TaskManager;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

/**
 * Classe di test per {@link TaskManager}, che rappresenta il gestore centrale
 * dei task nell'applicazione. Verifica le funzionalità di aggiunta e filtro dei task.
 * <p>
 * I test coprono scenari base come l'inserimento di un nuovo task e il filtro
 * per priorità specifica.
 * </p>
 * 
 * @author Gian Luca Baccani
 * @version 1.0
 */
public class TaskManagerTest {

    /**
     * Verifica che un task venga aggiunto correttamente al gestore
     * e che sia accessibile tramite {@link TaskManager#getTutti()}.
     */
    @Test
    public void testAggiuntaTask() {
        TaskManager manager = new TaskManager();
        Task t = new Task("Test", "Task di test", LocalDate.now(), Priorita.MEDIA);
        manager.aggiungiTask(t);

        assertEquals(1, manager.getTutti().size());
        assertEquals("Test", manager.getTutti().get(0).getTitolo());
    }

    /**
     * Verifica che il metodo {@link TaskManager#filtraPerPriorita(Priorita)}
     * restituisca correttamente solo i task con la priorità specificata.
     */
    @Test
    public void testFiltroPriorita() {
        TaskManager manager = new TaskManager();
        Task t1 = new Task("Alta", "Task urgente", LocalDate.now(), Priorita.ALTA);
        Task t2 = new Task("Bassa", "Non urgente", LocalDate.now(), Priorita.BASSA);
        manager.aggiungiTask(t1);
        manager.aggiungiTask(t2);

        List<Task> filtrati = manager.filtraPerPriorita(Priorita.ALTA);
        assertEquals(1, filtrati.size());
        assertEquals("Alta", filtrati.get(0).getTitolo());
    }
}
