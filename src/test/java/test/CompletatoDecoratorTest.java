package test;

import decorator.CompletatoDecorator;
import model.Priorita;
import model.Task;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe di test per {@link CompletatoDecorator}, che verifica il corretto
 * funzionamento della decorazione di un task come completato.
 *
 * <p>Controlla che il metodo {@code isCompletato()} ritorni {@code true}
 * e che la rappresentazione testuale includa l'indicazione "COMPLETATO".</p>
 *
 * @author Gian Luca Baccani
 * @version 1.0
 */
public class CompletatoDecoratorTest {

    @Test
    public void testCompletatoDecorator() {
        Task task = new Task("Test", "Decorazione", LocalDate.now(), Priorita.BASSA);
        assertFalse(task.isCompletato());

        Task decorated = new CompletatoDecorator(task);
        assertTrue(decorated.isCompletato());
        assertTrue(decorated.toString().contains("COMPLETATO"));
    }
}
