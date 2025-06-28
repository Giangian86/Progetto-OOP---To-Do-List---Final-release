package test;

import model.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe di test per {@link Task}, che rappresenta una singola attività nel sistema.
 * <p>
 * I test verificano la corretta inizializzazione dell'oggetto e
 * il contenuto restituito dal metodo {@code toString()}.
 * </p>
 * 
 * @author Gian Luca Baccani
 * @version 1.0
 */
public class TaskTest {

    /**
     * Verifica che un {@link Task} venga creato con i dati forniti
     * e che i metodi getter restituiscano i valori attesi.
     */
    @Test
    public void testTaskCreation() {
        Task task = new Task("Titolo", "Descrizione", LocalDate.of(2025, 7, 1), Priorita.MEDIA);
        assertEquals("Titolo", task.getTitolo());
        assertEquals("Descrizione", task.getDescrizione());
        assertEquals(Priorita.MEDIA, task.getPriorita());
    }

    /**
     * Verifica che il metodo {@link Task#toString()} includa
     * le informazioni principali come titolo e priorità.
     */
    @Test
    public void testToString() {
        Task task = new Task("Titolo", "Descrizione", LocalDate.of(2025, 7, 1), Priorita.ALTA);
        assertTrue(task.toString().contains("Titolo"));
        assertTrue(task.toString().contains("ALTA"));
    }
}
