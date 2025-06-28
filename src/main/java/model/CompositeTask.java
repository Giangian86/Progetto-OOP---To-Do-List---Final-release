package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * La classe {@code CompositeTask} rappresenta un'attività composta da più sottotask.
 * <p>Estende {@link Task} e permette la gestione di attività gerarchiche.</p>
 * 
 * @author Gian Luca Baccani
 * @version 1.0
 */
public class CompositeTask extends Task {

    /** Lista dei sottotask associati a questa task composta */
    private List<Task> subtasks = new ArrayList<>();
    
    /**
     * Costruttore di {@code CompositeTask}.
     *
     * @param titolo il titolo della task
     * @param descrizione la descrizione della task
     * @param scadenza la data di scadenza
     * @param priorita la priorità della task
     */
    public CompositeTask(String titolo, String descrizione, LocalDate scadenza, Priorita priorita) {
        super(titolo, descrizione, scadenza, priorita);
    }
    
    /**
     * Aggiunge un sottotask alla lista.
     *
     * @param task il sottotask da aggiungere
     * @throws IllegalArgumentException se il task è {@code null} o uguale a se stesso
     */
    public void addSubtask(Task task) {
        if (task == null) {
            throw new IllegalArgumentException("Subtask non può essere null");
        }
        if (task == this) {
            throw new IllegalArgumentException("Un task non può contenere se stesso");
        }
        subtasks.add(task);
    }
    
    /**
     * Rimuove un sottotask dalla lista.
     *
     * @param task il sottotask da rimuovere
     */
    public void removeSubtask(Task task) {
        subtasks.remove(task);
    }
    
    /**
     * Restituisce la lista dei sottotask.
     *
     * @return una copia della lista dei sottotask
     */
    public List<Task> getSubtasks() {
        return new ArrayList<>(subtasks);
    }
    
    /**
     * Restituisce il numero di sottotask.
     *
     * @return il numero di sottotask
     */
    public int getNumeroSubtasks() {
        return subtasks.size();
    }
    
    /**
     * Indica se tutti i sottotask sono completati.
     * Se non ci sono sottotask, ritorna lo stato della task principale.
     *
     * @return {@code true} se tutti i sottotask sono completati
     */
    @Override
    public boolean isCompletato() {
        if (subtasks.isEmpty()) {
            return super.isCompletato();
        }
        return subtasks.stream().allMatch(Task::isCompletato);
    }
    
    /**
     * Segna come completata questa task e tutti i sottotask.
     */
    @Override
    public void completaTask() {
        super.completaTask();
        subtasks.forEach(Task::completaTask);
    }
    
    /**
     * Restituisce una rappresentazione testuale della task con numero di sottotask.
     *
     * @return una stringa descrittiva
     */
    @Override
    public String toString() {
        String base = super.toString();
        if (!subtasks.isEmpty()) {
            base += " [" + subtasks.size() + " subtasks]";
        }
        return base;
    }
    
    /**
     * Restituisce una rappresentazione gerarchica della task e dei suoi sottotask.
     *
     * @return una stringa formattata in stile gerarchico
     */
    public String toStringGerarchico() {
        return toStringGerarchico("");
    }
    
    /**
     * Metodo interno ricorsivo per costruire la rappresentazione gerarchica.
     *
     * @param indent l'indentazione corrente
     * @return stringa con struttura ad albero
     */
    private String toStringGerarchico(String indent) {
        StringBuilder sb = new StringBuilder();
        sb.append(indent).append(this.toString()).append("\n");
        
        for (Task subtask : subtasks) {
            if (subtask instanceof CompositeTask) {
                sb.append(((CompositeTask) subtask).toStringGerarchico(indent + "  "));
            } else {
                sb.append(indent).append("  ").append(subtask.toString()).append("\n");
            }
        }
        
        return sb.toString();
    }
}
