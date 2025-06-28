package util;

import java.util.logging.*;

/**
 * {@code LoggerUtil} è una classe utility per la configurazione e il recupero
 * di un logger condiviso, utilizzato per la gestione dei log nel progetto.
 * 
 * <p>Il logger è configurato per mostrare solo messaggi a livello {@code WARNING} o superiore.</p>
 * 
 * @see java.util.logging.Logger
 * @see FileManager
 * @see factory.TaskFactory
 * 
 * @author Gian Luca Baccani
 * @version 1.0
 */
public class LoggerUtil {

    /**
     * Classe utility: non deve essere istanziata.
     */
    private LoggerUtil() {
        // Previene l'istanziazione
    }

    /** Logger condiviso per tutto il progetto */
    private static final Logger logger = Logger.getLogger("TaskLogger");

    // Blocco statico di inizializzazione per configurare il logger
    static {
        LogManager.getLogManager().reset();
        ConsoleHandler handler = new ConsoleHandler();
        handler.setLevel(Level.WARNING);
        logger.addHandler(handler);
        logger.setLevel(Level.WARNING);
    }

    /**
     * Restituisce il logger configurato del progetto.
     *
     * @return il logger condiviso
     */
    public static Logger getLogger() {
        return logger;
    }
}
