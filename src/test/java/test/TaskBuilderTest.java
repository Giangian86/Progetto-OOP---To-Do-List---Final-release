package test;

import model.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe di test per {@link TaskBuilder}, che implementa il pattern builder
 * per la creazione di oggetti {@link Task} in modo fluido e controllato.
 * <p>
 * I test verificano che i task vengano costruiti correttamente con parametri validi
 * e che vengano lanciati errori in caso di parametri non validi.
 * </p>
 * 
 * @author Gian Luca Baccani
 * @version 1.0
 */
public class TaskBuilderTest {

    /**
     * Verifica che {@link TaskBuilder} costruisca correttamente un task
     * con tutti i parametri richiesti settati correttamente.
     * 
     * @throws TaskException se i parametri del task non sono validi
     */
    @Test
    public void testBuilderCreaTaskValido() throws TaskException {
        Task task = new TaskBuilder()
            .setTitolo("Test Builder")
            .setDescrizione("Descrizione")
            .setScadenza(LocalDate.of(2025, 7, 1))
            .setPriorita(Priorita.MEDIA)
            .build();

        assertNotNull(task);
        assertEquals("Test Builder", task.getTitolo());
        assertEquals(Priorita.MEDIA, task.getPriorita());
    }

    /**
     * Verifica che il {@link TaskBuilder} lanci un'eccezione {@link TaskException}
     * se viene fornito un titolo vuoto, violando i vincoli del costruttore.
     */
    @Test
    public void testBuilderConTitoloVuoto() {
        TaskBuilder builder = new TaskBuilder()
            .setTitolo("")
            .setScadenza(LocalDate.now());

        assertThrows(TaskException.class, builder::build);
    }
}
