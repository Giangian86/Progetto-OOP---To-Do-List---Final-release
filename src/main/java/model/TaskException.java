package model;

/**
 * {@code TaskException} è un'eccezione personalizzata utilizzata per segnalare errori
 * nella creazione o gestione di una {@link Task}, ad esempio quando mancano dati obbligatori.
 * 
 * <p>È utilizzata principalmente dal {@link TaskBuilder}.</p>
 * 
 * @author Gian Luca Baccani
 * @version 1.0
 */
public class TaskException extends Exception {

    /**
     * Costruisce una nuova eccezione {@code TaskException} con il messaggio specificato.
     *
     * @param message il messaggio di errore
     */
    public TaskException(String message) {
        super(message);
    }
}
