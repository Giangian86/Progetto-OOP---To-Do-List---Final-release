package test;

import model.*;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe di test per {@link CompositeTask}, che rappresenta un'attività composta
 * da più subtasks. Questa suite verifica la corretta gestione dei subtasks,
 * la propagazione dello stato di completamento e la validità delle gerarchie.
 * <p>
 * I test includono la creazione, l'aggiunta di subtasks, la gestione di completamenti
 * annidati e i controlli per evitare riferimenti circolari.
 * </p>
 * 
 * @author Gian Luca Baccani
 * @version 1.0
 */
public class CompositeTaskTest {
    
    /**
     * Verifica che un oggetto {@link CompositeTask} venga creato correttamente
     * e che inizialmente non contenga subtasks né risulti completato.
     */
    @Test
    public void testCompositeTaskCreation() {
        CompositeTask composite = new CompositeTask(
            "Progetto", "Descrizione", LocalDate.now(), Priorita.ALTA
        );
        
        assertNotNull(composite);
        assertEquals(0, composite.getNumeroSubtasks());
        assertFalse(composite.isCompletato());
    }
    
    /**
     * Verifica che sia possibile aggiungere un subtask a un {@link CompositeTask}
     * e che venga correttamente registrato.
     */
    @Test
    public void testAddSubtask() {
        CompositeTask composite = new CompositeTask(
            "Progetto", "Desc", LocalDate.now(), Priorita.ALTA
        );
        Task subtask = new Task("Subtask", "Desc", LocalDate.now(), Priorita.MEDIA);
        
        composite.addSubtask(subtask);
        assertEquals(1, composite.getNumeroSubtasks());
        assertTrue(composite.getSubtasks().contains(subtask));
    }
    
    /**
     * Verifica la logica di completamento: un {@link CompositeTask}
     * risulta completato solo se tutti i suoi subtasks sono completati.
     */
    @Test
    public void testCompositeCompletion() {
        CompositeTask composite = new CompositeTask(
            "Progetto", "Desc", LocalDate.now(), Priorita.ALTA
        );
        Task subtask1 = new Task("Sub1", "Desc", LocalDate.now(), Priorita.MEDIA);
        Task subtask2 = new Task("Sub2", "Desc", LocalDate.now(), Priorita.MEDIA);
        
        composite.addSubtask(subtask1);
        composite.addSubtask(subtask2);
        
        // Composite non completato se i subtask non sono completati
        assertFalse(composite.isCompletato());
        
        // Completa un subtask
        subtask1.completaTask();
        assertFalse(composite.isCompletato());
        
        // Completa tutti i subtask
        subtask2.completaTask();
        assertTrue(composite.isCompletato());
    }
    
    /**
     * Verifica che sia possibile creare una gerarchia annidata di {@link CompositeTask},
     * e che il completamento delle foglie si propaghi correttamente verso la radice.
     */
    @Test
    public void testNestedComposite() {
        CompositeTask root = new CompositeTask(
            "Root", "Desc", LocalDate.now(), Priorita.ALTA
        );
        CompositeTask child = new CompositeTask(
            "Child", "Desc", LocalDate.now(), Priorita.MEDIA
        );
        Task leaf = new Task("Leaf", "Desc", LocalDate.now(), Priorita.BASSA);
        
        child.addSubtask(leaf);
        root.addSubtask(child);
        
        assertEquals(1, root.getNumeroSubtasks());
        assertEquals(1, child.getNumeroSubtasks());
        
        // Test completamento gerarchico
        assertFalse(root.isCompletato());
        leaf.completaTask();
        assertTrue(child.isCompletato());
        assertTrue(root.isCompletato());
    }
    
    /**
     * Verifica che non sia possibile aggiungere un {@link CompositeTask} a se stesso
     * come subtask, per evitare cicli infiniti o ricorsioni logiche.
     */
    @Test
    public void testCannotAddSelfAsSubtask() {
        CompositeTask composite = new CompositeTask(
            "Test", "Desc", LocalDate.now(), Priorita.ALTA
        );
        
        assertThrows(IllegalArgumentException.class, () -> {
            composite.addSubtask(composite);
        });
    }
}