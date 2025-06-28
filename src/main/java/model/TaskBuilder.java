package model;

import java.time.LocalDate;

/**
 * {@code TaskBuilder} è una classe che implementa il Builder Pattern per creare oggetti {@link Task}.
 * <p>Permette di impostare passo-passo i parametri di una task in modo flessibile e leggibile.</p>
 * 
 * @author Gian Luca Baccani
 * @version 1.0
 */
public class TaskBuilder {
    private String titolo;
    private String descrizione;
    private LocalDate scadenza;
    private Priorita priorita;

    /**
     * Costruttore di default.
     */
    public TaskBuilder() {
        // Nessuna inizializzazione
    }

     /**
     * Imposta il titolo della task.
     *
     * @param titolo il titolo da assegnare
     * @return il builder stesso
     */
    public TaskBuilder setTitolo(String titolo) {
        this.titolo = titolo;
        return this;
    }

    /**
     * Imposta la descrizione della task.
     *
     * @param descrizione la descrizione da assegnare
     * @return il builder stesso
     */
    public TaskBuilder setDescrizione(String descrizione) {
        this.descrizione = descrizione;
        return this;
    }

    /**
     * Imposta la data di scadenza della task.
     *
     * @param scadenza la data di scadenza
     * @return il builder stesso
     */
    public TaskBuilder setScadenza(LocalDate scadenza) {
        this.scadenza = scadenza;
        return this;
    }

    /**
     * Imposta la priorità della task.
     *
     * @param priorita il livello di priorità
     * @return il builder stesso
     */
    public TaskBuilder setPriorita(Priorita priorita) {
        this.priorita = priorita;
        return this;
    }

    /**
     * Costruisce e restituisce un oggetto {@link Task} con i dati impostati.
     *
     * @return l'oggetto {@code Task} costruito
     * @throws TaskException se titolo o scadenza non sono stati impostati
     */
    public Task build() throws TaskException {
        if (titolo == null || titolo.isBlank()) {
            throw new TaskException("Il titolo è obbligatorio.");
        }
        if (scadenza == null) {
            throw new TaskException("La scadenza è obbligatoria.");
        }
        return new Task(titolo, descrizione, scadenza, priorita);
    }
}
