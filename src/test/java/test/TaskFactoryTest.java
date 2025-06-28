package test;

import factory.TaskFactory;
import model.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe di test per {@link TaskFactory}, che fornisce un'interfaccia statica
 * per creare istanze di {@link Task}.
 * <p>
 * I test verificano il corretto comportamento nella creazione di task validi
 * e nella gestione di input non validi, come un titolo vuoto.
 * </p>
 * 
 * @author Gian Luca Baccani
 * @version 1.0
 */
public class TaskFactoryTest {

    /**
     * Verifica che {@link TaskFactory#creaTask(String, String, LocalDate, Priorita)}
     * ritorni un'istanza valida di {@link Task} quando tutti i parametri sono corretti.
     * 
     * @throws TaskException se la creazione del task fallisce (non atteso in questo test)
     */
    @Test
    public void testCreaTaskValido() throws TaskException {
        Task task = TaskFactory.creaTask("Valido", "Test", LocalDate.now(), Priorita.BASSA);
        assertNotNull(task);
        assertEquals("Valido", task.getTitolo());
    }

    /**
     * Verifica che {@link TaskFactory#creaTask(String, String, LocalDate, Priorita)}
     * lanci una {@link TaskException} se viene fornito un titolo vuoto.
     */
    @Test
    public void testCreaTaskTitoloVuoto() {
        assertThrows(TaskException.class, () -> {
            TaskFactory.creaTask("", "No titolo", LocalDate.now(), Priorita.MEDIA);
        });
    }
}
