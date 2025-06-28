package mainApp;

import model.*;
import service.TaskManager;
import factory.TaskFactory;
import util.FileManager;
import util.LoggerUtil;
import java.util.logging.Logger;
import strategy.*;
import decorator.CompletatoDecorator;
import memento.TaskCaretaker;
import memento.TaskMemento;

import java.time.LocalDate;
import java.util.List;

import decorator.UrgenteDecorator;

/**
 * Classe principale dell'applicazione To-Do List.
 * <p>Dimostra l'utilizzo combinato di vari design pattern, tra cui:</p>
 * <ul>
 *   <li>Factory</li>
 *   <li>Decorator</li>
 *   <li>Builder</li>
 *   <li>Memento</li>
 *   <li>Composite</li>
 *   <li>Strategy</li>
 *   <li>Iterator</li>
 * </ul>
 * 
 * @author Gian Luca Baccani
 * @version 1.0
 */
public class Main {

    /** Logger utilizzato per tracciare eventi e messaggi d'errore. */
    private static final Logger logger = LoggerUtil.getLogger();

    /**
     * Costruttore di default.
     */
    public Main() {
        // Nessuna inizializzazione
    }

    /**
     * Metodo principale che avvia l'applicazione e dimostra l'uso di vari design pattern.
     * 
     * @param args Argomenti da linea di comando (non utilizzati).
     */
    public static void main(String[] args) {

        // Memento Pattern : gestione dello stato iniziale del sistema
        TaskCaretaker caretaker = new TaskCaretaker();
        
        // Gestione dello stato iniziale del sistema
        TaskManager manager = new TaskManager();

        // Factory Pattern : creazione di tasks
        try {
            Task t1 = TaskFactory.creaTask("Studiare per l'esame di System", "Ripassare tutto il programma del corso", LocalDate.of(2025, 7, 25), Priorita.ALTA);
            
            // Decorator Pattern : esempio di task marcato con l'etichetta URGENTE
            Task t1Urgente = new UrgenteDecorator(t1);
            caretaker.salva(new TaskMemento(new Task(t1Urgente.getTitolo(), t1Urgente.getDescrizione(), t1Urgente.getScadenza(), t1Urgente.getPriorita())));
            manager.aggiungiTask(t1Urgente);

            Task t2 = TaskFactory.creaTask("Pulire garage", "Rimuovere scatoloni", LocalDate.of(2025, 6, 20), Priorita.BASSA);
            
            // Decorator Pattern : esempio di task marcato con l'etichetta COMPLETATO
            Task t2Completato = new CompletatoDecorator(t2);
            manager.aggiungiTask(t2Completato);
            
            Task t3 = TaskFactory.creaTask("Esercizi di DSA", "Terminare gli esercizi", LocalDate.of(2025, 7, 18), Priorita.ALTA);
            manager.aggiungiTask(t3);
            
            Task t4 = TaskFactory.creaTask("Fare la spesa", "Compra latte e pane", LocalDate.now(), Priorita.MEDIA);
            manager.aggiungiTask(t4);
            
        } catch (TaskException e) {
            System.out.println("Errore: " + e.getMessage());
        }

        // Builder Pattern: costruzione personalizzata step by step e sicura di un Task
        try {
            Task taskBuilder = new TaskBuilder()
            .setTitolo("Consegnare progetto Computer Networks")
            .setDescrizione("Completare il saggio e inviare il progetto")
            .setScadenza(LocalDate.of(2025, 7, 15))
            .setPriorita(Priorita.MEDIA)
            .build();

            manager.aggiungiTask(taskBuilder);
            System.out.println("Task aggiunto con Builder: " + taskBuilder);

        } catch (TaskException e) {
            System.out.println("Errore nella creazione con builder: " + e.getMessage());
            logger.warning("Errore builder: " + e.getMessage());
        }

        // Memento Pattern: ripristinare lo stato originale del task
        TaskMemento ripristino = caretaker.annulla();
        if (ripristino != null) {
        System.out.println("Task ripristinato: " + ripristino.getStatoSalvato());
        } else {
        System.out.println("Nessun task da ripristinare.");
        }

        // Composite Pattern: creazione di task con subtasks
        
        try {
            CompositeTask progettoOOP = new CompositeTask(
                "Progetto OOP Completo",
                "Sviluppare un'applicazione Java completa",
                LocalDate.of(2025, 6, 28),
                Priorita.ALTA
            );
    
            // Aggiungi subtasks
            Task analisi = TaskFactory.creaTask(
                "Analisi requisiti",
                "Definire requisiti funzionali",
                LocalDate.of(2025, 6, 10),
                Priorita.ALTA
            );
    
            Task implementazione = TaskFactory.creaTask(
                "Implementazione",
                "Scrivere il codice",
                LocalDate.of(2025, 6, 20),
                Priorita.ALTA
            );
    
            Task testing = TaskFactory.creaTask(
                "Testing",
                "Test unitari e integrazione",
                LocalDate.of(2025, 7, 25),
                Priorita.ALTA
            );
    
            progettoOOP.addSubtask(analisi);
            progettoOOP.addSubtask(implementazione);
            progettoOOP.addSubtask(testing);
    
            // Completa solo l'analisi
            analisi.completaTask();

            // Progetto completato
            implementazione.completaTask();
            testing.completaTask();

            System.out.println("Task composito creato:");
            System.out.println(progettoOOP.toStringGerarchico());
            System.out.println("Il progetto è completato? " + progettoOOP.isCompletato());
    
            manager.aggiungiTask(progettoOOP);
    
        } catch (TaskException e) {
            System.out.println("Errore nella creazione del task composito: " + e.getMessage());
        }

        // Iterator Pattern: esempio di utilizzo di iteratore personalizzato
        
        System.out.println("Iterazione con Iterator personalizzato:");
        int count = 0;
        for (Task task : manager) {
            System.out.println("  " + (++count) + ". " + task.getTitolo());
        }

        // Iterazione e stampa di tutti i tasks con indentazione dei subtasks
        System.out.println("\nTutti i task (inclusi subtasks):");
        List<Task> tuttiConSubtasks = manager.getTuttiConSubtasks();
        for (int i = 0; i < tuttiConSubtasks.size(); i++) {
            Task t = tuttiConSubtasks.get(i);
            String indent = (t instanceof CompositeTask || 
                            tuttiConSubtasks.stream()
                                .filter(ct -> ct instanceof CompositeTask)
                                .anyMatch(ct -> ((CompositeTask)ct).getSubtasks().contains(t))) 
                            ? "  " : "";
            System.out.println(indent + (i+1) + ". " + t);
        }

        // Strategy Pattern : visualizzazione tasks e filtraggio

        // Stampa di tutti i tasks
        System.out.println("Tutti i task:");
        for (Task t : manager.getTutti()) {
            System.out.println(t);
        }
        // Filtraggio e stampa dei tasks marcati con priorità ALTA
        System.out.println("\nTask ad alta priorità:");
        List<Task> urgenti = manager.filtraPerPriorita(Priorita.ALTA);
        urgenti.forEach(System.out::println);

        // Gestore File che genera un file di testo contenente tutti i tasks
        FileManager.salvaTask(manager.getTutti(), "tasks_output.txt");

        // Strategy Pattern: ordinare i tasks per scadenza
        TaskSortStrategy sorter = new OrdinaPerScadenza();
        System.out.println("\nTask ordinati per scadenza:");
        sorter.ordina(manager.getTutti()).forEach(System.out::println);

        // Gestore File che genera un file di testo contenente tutti i tasks ordinati per scadenza
        FileManager.salvaTask(new OrdinaPerScadenza().ordina(manager.getTutti()), "tasks_per_scadenza.txt");

        // Strategy Pattern: ordinare i tasks per priorità
        System.out.println("\n Tasks ordinati per priorità:");
        sorter = new OrdinaPerPriorita();
        sorter.ordina(manager.getTutti()).forEach(System.out::println);

        // Gestore File che genera un file di testo contenente tutti i tasks ordinati per priorità
        FileManager.salvaTask(new OrdinaPerPriorita().ordina(manager.getTutti()), "tasks_per_priorita.txt");

        // Strategy Pattern: ordinare i tasks per completamento
        System.out.println("\n Tasks ordinati per stato di completamento:");
        sorter = new OrdinaPerCompletamento();
        sorter.ordina(manager.getTutti()).forEach(System.out::println);

        // Gestore File che genera un file di testo contenente tutti i tasks ordinati per completamento
        FileManager.salvaTask(new OrdinaPerCompletamento().ordina(manager.getTutti()), "tasks_per_completamento.txt");
    }
}
