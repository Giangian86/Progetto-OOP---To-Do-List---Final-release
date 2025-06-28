package test;

import model.*;
import service.TaskManager;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe di test per la classe {@link TaskManager}, con particolare attenzione
 * alla funzionalità dell'iteratore personalizzato e alla gestione dei subtasks.
 * <p>
 * I test coprono scenari di iterazione esplicita, gestione di eccezioni,
 * compatibilità con i loop for-each, e recupero ricorsivo dei task.
 * </p>
 * 
 * @author Gian Luca Baccani
 * @version 1.0
 */
public class TaskManagerIteratorTest {
    
    /**
     * Verifica che l'iteratore fornito da {@link TaskManager#iterator()} funzioni correttamente
     * restituendo i task nell'ordine di inserimento.
     */
    @Test
    public void testIterator() {
        TaskManager manager = new TaskManager();
        Task t1 = new Task("Task1", "Desc", LocalDate.now(), Priorita.ALTA);
        Task t2 = new Task("Task2", "Desc", LocalDate.now(), Priorita.MEDIA);
        
        manager.aggiungiTask(t1);
        manager.aggiungiTask(t2);
        
        // Test con iterator
        Iterator<Task> iterator = manager.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(t1, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(t2, iterator.next());
        assertFalse(iterator.hasNext());
    }
    
    /**
     * Verifica che l'iteratore lanci {@link NoSuchElementException}
     * se si chiama {@code next()} su una collezione vuota.
     */
    @Test
    public void testIteratorNoSuchElement() {
        TaskManager manager = new TaskManager();
        Iterator<Task> iterator = manager.iterator();
        
        assertThrows(NoSuchElementException.class, () -> {
            iterator.next();
        });
    }
    
    /**
     * Verifica che {@link TaskManager} supporti correttamente il costrutto
     * for-each (internamente usa l'iteratore).
     */
    @Test
    public void testForEachLoop() {
        TaskManager manager = new TaskManager();
        Task t1 = new Task("Task1", "Desc", LocalDate.now(), Priorita.ALTA);
        Task t2 = new Task("Task2", "Desc", LocalDate.now(), Priorita.MEDIA);
        
        manager.aggiungiTask(t1);
        manager.aggiungiTask(t2);
        
        List<Task> collected = new ArrayList<>();
        for (Task task : manager) {
            collected.add(task);
        }
        
        assertEquals(2, collected.size());
        assertTrue(collected.contains(t1));
        assertTrue(collected.contains(t2));
    }
    
    /**
     * Verifica che il metodo {@link TaskManager#getTuttiConSubtasks()} restituisca
     * tutti i task, includendo quelli annidati in {@link CompositeTask}.
     */
    @Test
    public void testGetTuttiConSubtasks() {
        TaskManager manager = new TaskManager();
        CompositeTask composite = new CompositeTask(
            "Composite", "Desc", LocalDate.now(), Priorita.ALTA
        );
        Task subtask = new Task("Subtask", "Desc", LocalDate.now(), Priorita.MEDIA);
        
        composite.addSubtask(subtask);
        manager.aggiungiTask(composite);
        
        List<Task> tutti = manager.getTuttiConSubtasks();
        assertEquals(2, tutti.size());
        assertTrue(tutti.contains(composite));
        assertTrue(tutti.contains(subtask));
    }
}