package test;

import model.*;
import decorator.*;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe di test per {@link UrgenteDecorator}, che verifica
 * il comportamento del decoratore per segnare un task come urgente.
 * <p>
 * Il test si concentra sulla corretta modifica della rappresentazione
 * testuale del task tramite {@code toString()}.
 * </p>
 * 
 * @author Gian Luca Baccani
 * @version 1.0
 */
public class DecoratorTest {

    /**
     * Verifica che la decorazione con {@link UrgenteDecorator}
     * aggiunga correttamente il tag "[URGENTE]" nella rappresentazione testuale del task.
     */
    @Test
    public void testUrgenteDecorator() {
        Task base = new Task("Decorato", "Test", LocalDate.now(), Priorita.ALTA);
        Task decorato = new UrgenteDecorator(base);

        assertTrue(decorato.toString().contains("[URGENTE]"));
    }
}
