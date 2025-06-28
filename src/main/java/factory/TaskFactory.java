package factory;

import model.*;
import util.LoggerUtil;

import java.time.LocalDate;
import java.util.logging.Logger;

/**
 * {@code TaskFactory} è una classe factory che fornisce un metodo statico
 * per creare istanze di {@link Task}, applicando controlli di validazione e gestione degli errori.
 * 
 * <p>Utilizza un logger per segnalare eventuali problemi durante la creazione.</p>
 * 
 * @author Gian Luca Baccani
 * @version 1.0
 */
public class TaskFactory {

    /** Logger per registrare eventuali errori */
    private static final Logger logger = LoggerUtil.getLogger();

    /**
     * Classe utility: non deve essere istanziata.
     */
    private TaskFactory() {
        throw new UnsupportedOperationException("Classe utility, non istanziabile.");
    }

    /**
     * Crea e restituisce un nuovo oggetto {@link Task} con i parametri specificati.
     *
     * @param titolo il titolo della task (obbligatorio)
     * @param descrizione la descrizione della task (opzionale)
     * @param scadenza la data di scadenza (obbligatoria)
     * @param priorita la priorità della task (opzionale)
     * @return un nuovo oggetto {@code Task}
     * @throws TaskException se i dati forniti non sono validi
     */
    public static Task creaTask(String titolo, String descrizione, LocalDate scadenza, Priorita priorita) throws TaskException {
        try {
            if (titolo == null || titolo.isBlank()) {
                throw new IllegalArgumentException("Il titolo è obbligatorio.");
            }
            if (scadenza == null) {
                throw new IllegalArgumentException("La data di scadenza è obbligatoria.");
            }
            return new Task(titolo, descrizione, scadenza, priorita);
        } catch (Exception e) {
            logger.warning("Errore nella creazione del task: " + e.getMessage());
            throw new TaskException("Errore durante la creazione del task. Controlla i dati inseriti.");
        }
    }
}
