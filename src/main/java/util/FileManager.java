package util;

import model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

/**
 * {@code FileManager} fornisce metodi statici per salvare e caricare una lista di {@link Task}
 * da e verso un file di testo.
 * 
 * <p>Ogni task viene serializzato in una riga con campi separati da punto e virgola (;).</p>
 * 
 * @author Gian Luca Baccani
 * @version 1.0
 */
public class FileManager {


    /**
     * Costruttore di default di FileManager.
     */
    public FileManager() {
        // Nessuna inizializzazione necessaria
    }
    
    /**
     * Salva una lista di task in un file di testo specificato.
     *
     * @param tasks la lista di task da salvare
     * @param filePath il percorso del file dove salvare i dati
     */ 
    public static void salvaTask(List<Task> tasks, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Task t : tasks) {
                writer.write(t.getTitolo() + ";" + t.getDescrizione() + ";" +
                             t.getScadenza() + ";" + t.getPriorita()); //+ ";" +
                             //t.isCompletato());
                writer.newLine();
            }
        } catch (IOException e) {
            LoggerUtil.getLogger().warning("Errore nel salvataggio: " + e.getMessage());
        }
    }

    /**
     * Carica una lista di task da un file di testo.
     * 
     * @param filePath il percorso del file da cui leggere i dati
     * @return una lista di task ricostruiti dal file
     */
    public static List<Task> caricaTask(String filePath) {
        List<Task> tasks = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] parti = linea.split(";");
                String titolo = parti[0];
                String descrizione = parti[1];
                LocalDate scadenza = LocalDate.parse(parti[2]);
                Priorita priorita = Priorita.valueOf(parti[3]);
                tasks.add(new Task(titolo, descrizione, scadenza, priorita));
            }
        } catch (IOException | ArrayIndexOutOfBoundsException e) {
            LoggerUtil.getLogger().warning("Errore nel caricamento: " + e.getMessage());
        }
        return tasks;
    }
}
