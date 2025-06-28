package test;

import memento.TaskCaretaker;
import memento.TaskMemento;
import model.Priorita;
import model.Task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe di test per il pattern Memento applicato ai task tramite
 * {@link TaskMemento} e {@link TaskCaretaker}.
 * <p>
 * Verifica che lo stato di un task possa essere salvato e successivamente
 * ripristinato correttamente.
 * </p>
 * 
 * @author Gian Luca Baccani
 * @version 1.0
 */
public class TaskMementoTest {

    /**
     * Verifica che sia possibile salvare lo stato corrente di un {@link Task}
     * tramite {@link TaskMemento}, modificarlo, e successivamente
     * ripristinare lo stato originale con {@link TaskCaretaker#annulla()}.
     */
    @Test
    public void testSalvataggioRipristino() {
        Task task = new Task("Originale", "desc", LocalDate.now(), Priorita.MEDIA);
        TaskCaretaker caretaker = new TaskCaretaker();

        TaskMemento snapshot = new TaskMemento(new Task(task.getTitolo(), task.getDescrizione(), task.getScadenza(), task.getPriorita()));
        caretaker.salva(snapshot);

        task.completaTask();
        assertTrue(task.isCompletato());

        TaskMemento ripristinato = caretaker.annulla();
        assertFalse(ripristinato.getStatoSalvato().isCompletato());
    }
}
