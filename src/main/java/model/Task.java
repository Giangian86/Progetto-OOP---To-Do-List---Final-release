package model;

import java.time.LocalDate;
import java.util.Objects;

/**
 * La classe {@code Task} rappresenta un'attività singola con titolo, descrizione,
 * data di scadenza, priorità e stato di completamento.
 * 
 * <p>Questa classe è usata come base per costruire una to-do list.</p>
 * 
 * @author Gian Luca Baccani
 * @version 1.0
 */

public class Task {

    /** Il titolo della task */
    private String titolo;
    /** La descrizione della task */
    private String descrizione;
    /** La data di scadenza della task */
    private LocalDate scadenza;
    /** La priorità associata alla task */
    private Priorita priorita;
    /** Stato che indica se la task è completata */
    private boolean completato;

    /**
     * Costruttore della classe {@code Task}.
     *
     * @param titolo il titolo dell'attività
     * @param descrizione la descrizione dell'attività
     * @param scadenza la data entro cui completare l'attività
     * @param priorita il livello di priorità dell'attività
     */
    public Task(String titolo, String descrizione, LocalDate scadenza, Priorita priorita) {
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.scadenza = scadenza;
        this.priorita = priorita;
        this.completato = false;
    }

     /**
     * Restituisce il titolo della task.
     *
     * @return il titolo
     */
    public String getTitolo() { return titolo; }
    
    /**
     * Restituisce la descrizione della task.
     *
     * @return la descrizione
     */
    public String getDescrizione() { return descrizione; }
    
    /**
     * Restituisce la data di scadenza della task.
     *
     * @return la data di scadenza
     */
    public LocalDate getScadenza() { return scadenza; }

    /**
     * Restituisce la priorità della task.
     *
     * @return la priorità
     */
    public Priorita getPriorita() { return priorita; }

    /**
     * Indica se la task è stata completata.
     *
     * @return {@code true} se completata, altrimenti {@code false}
     */
    public boolean isCompletato() { return completato; }

    /**
     * Segna la task come completata.
     */
    public void completaTask() {
        this.completato = true;
    }

    /**
     * Restituisce una rappresentazione testuale della task.
     *
     * @return una stringa con priorità, titolo e scadenza
     */
    @Override
    public String toString() {
        return "[" + priorita + "] " + titolo + " - Scade il: " + scadenza;
    }

    /**
     * Verifica se due task sono uguali confrontando il titolo.
     *
     * @param o oggetto da confrontare
     * @return {@code true} se i titoli sono uguali, altrimenti {@code false}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;
        Task task = (Task) o;
        return Objects.equals(titolo, task.titolo);
    }

    /**
     * Restituisce l'hash code basato sul titolo della task.
     *
     * @return il valore hash
     */
    @Override
    public int hashCode() {
        return Objects.hash(titolo);
    }
}
