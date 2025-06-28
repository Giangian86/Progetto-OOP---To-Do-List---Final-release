package test;

import model.*;
import util.FileManager;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe di test per {@link FileManager}, responsabile della
 * serializzazione e deserializzazione dei task su file.
 * <p>
 * Questo test verifica che il salvataggio e il caricamento funzionino
 * correttamente, mantenendo la coerenza dei dati.
 * </p>
 * 
 * @author Gian Luca Baccani
 * @version 1.0
 */
public class FileManagerTest {

    /**
     * Verifica che un task possa essere correttamente salvato su file
     * e successivamente caricato tramite i metodi di {@link FileManager}.
     * <p>
     * Il test crea un task, lo salva su un file temporaneo, verifica
     * l'esistenza del file, ricarica i dati e controlla che il titolo
     * del task corrisponda. Infine elimina il file di test.
     * </p>
     */
    @Test
    public void testSalvaECarica() {
        String path = "test_output.txt";
        Task task = new Task("FileTest", "Descrizione", LocalDate.now(), Priorita.ALTA);
        FileManager.salvaTask(List.of(task), path);

        File file = new File(path);
        assertTrue(file.exists());

        List<Task> caricati = FileManager.caricaTask(path);
        assertFalse(caricati.isEmpty());
        assertEquals("FileTest", caricati.get(0).getTitolo());

        file.delete(); // pulizia file
    }
}
