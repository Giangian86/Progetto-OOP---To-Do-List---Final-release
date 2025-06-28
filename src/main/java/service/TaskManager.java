package service;

import model.*;
import java.util.*;

/**
 * La classe {@code TaskManager} gestisce un elenco di oggetti {@link Task},
 * fornendo metodi per aggiungere, rimuovere, filtrare e iterare le attività.
 * <p>Supporta anche task composite e implementa l'iterator pattern.</p>
 * 
 * @author Gian Luca Baccani
 * @version 1.0
 */
public class TaskManager implements Iterable<Task> {

    /**
     * Costruttore di default.
     */
    public TaskManager() {
        // Nessuna inizializzazione
    }

    /** Elenco delle task gestite */
    private List<Task> elenco = new ArrayList<>();

    /**
     * Aggiunge un task all'elenco.
     *
     * @param t il task da aggiungere
     * @throws IllegalArgumentException se {@code t} è null
     */
    public void aggiungiTask(Task t) {
        if (t == null) {
            throw new IllegalArgumentException("Il task non può essere null");
        }
        elenco.add(t);
    }

    /**
     * Restituisce una copia dell'elenco di tutti i task.
     *
     * @return lista dei task
     */
    public List<Task> getTutti() {
        return new ArrayList<>(elenco);
    }

    /**
     * Filtra i task in base alla priorità specificata.
     *
     * @param priorita la priorità da filtrare
     * @return lista di task con la priorità data
     */
    public List<Task> filtraPerPriorita(Priorita priorita) {
        return elenco.stream()
                .filter(t -> t.getPriorita() == priorita)
                .toList();
    }
    
    /**
     * Rimuove un task dall'elenco.
     *
     * @param t il task da rimuovere
     */
    public void rimuoviTask(Task t) {
        elenco.remove(t);
    }
    
    /**
     * Restituisce il numero totale di task gestiti.
     *
     * @return numero di task
     */
    public int getNumeroTasks() {
        return elenco.size();
    }
    
    /**
     * Restituisce un iteratore personalizzato per scorrere i task.
     *
     * @return un {@code Iterator} su {@code Task}
     */
    @Override
    public Iterator<Task> iterator() {
        return new TaskIterator();
    }
    
    /**
     * Classe interna per l'iteratore personalizzato.
     * Implementa una copia difensiva della lista per sicurezza.
     */
    private class TaskIterator implements Iterator<Task> {
        private int currentIndex = 0;
        private final List<Task> tasksCopy;
        
        public TaskIterator() {
            // Copia difensiva per evitare ConcurrentModificationException
            this.tasksCopy = new ArrayList<>(elenco);
        }
        
        @Override
        public boolean hasNext() {
            return currentIndex < tasksCopy.size();
        }
        
        @Override
        public Task next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Nessun altro task disponibile");
            }
            return tasksCopy.get(currentIndex++);
        }
        
        @Override
        public void remove() {
            throw new UnsupportedOperationException("Rimozione non supportata dall'iterator");
        }
    }
    
    /**
     * Restituisce la lista di tutti i task, inclusi i subtasks ricorsivamente.
     *
     * @return lista completa con subtasks
     */
    public List<Task> getTuttiConSubtasks() {
        List<Task> tutti = new ArrayList<>();
        for (Task task : elenco) {
            tutti.add(task);
            if (task instanceof CompositeTask) {
                aggiungiSubtasksRicorsivo((CompositeTask) task, tutti);
            }
        }
        return tutti;
    }
    
    /**
     * Metodo ausiliario per aggiungere i subtasks ricorsivamente a una lista.
     *
     * @param composite task contenente subtasks
     * @param lista la lista dove aggiungere i subtasks
     */
    private void aggiungiSubtasksRicorsivo(CompositeTask composite, List<Task> lista) {
        for (Task subtask : composite.getSubtasks()) {
            lista.add(subtask);
            if (subtask instanceof CompositeTask) {
                aggiungiSubtasksRicorsivo((CompositeTask) subtask, lista);
            }
        }
    }
}