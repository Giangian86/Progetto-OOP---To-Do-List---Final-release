# To Do List - Progetto OOP in Java

> Applicazione console modulare scritta in Java SE per la gestione di attività (task), che impiega i principali pattern della programmazione orientata agli oggetti, logging, I/O e test.

---

## Panoramica del progetto

Questa applicazione gestisce una **lista di attività** in cui l’utente può:

- Creare, visualizzare, rimuovere e completare task
- Applicare etichette decorative (es. URGENTE, COMPLETATO)
- Ordinare i task con strategie differenti
- Gestire lo stato tramite salvataggio/caricamento su file
- Annullare modifiche con Memento

---

## Design Pattern implementati

### Pattern richiesti

| Pattern                  | Utilizzo nel progetto                                                  |
|--------------------------|------------------------------------------------------------------------|
| **Factory**              | `TaskFactory` centralizza e valida la creazione dei task              |
| **Composite**            | `CompositeTask` consente di creare task composti                      |
| **Iterator**             | `TaskManager` implementa `Iterable` per scorrere i task               |
| **Exception Shielding**  | `TaskException` maschera errori evitando la stampa di stack trace     |

### Pattern opzionali usati

| Pattern        | Scopo                                                             |
|----------------|-------------------------------------------------------------------|
| **Decorator**  | Etichette come `[URGENTE]`, `[COMPLETATO]` via `TaskDecorator`    |
| **Strategy**   | Ordinamenti personalizzati (scadenza, priorità, completati)       |
| **Memento**    | Ripristino stato dei task tramite `TaskCaretaker`                 |
| **Builder**    | `TaskBuilder` semplifica la costruzione di oggetti `Task`         |
| **Singleton**  | Logger centralizzato in `LoggerUtil` tramite istanza statica      |

---

## Tecnologie & funzionalità Java

| Funzionalità       | Descrizione                                                      |
|--------------------|------------------------------------------------------------------|
| **Java I/O**        | Lettura e scrittura su file tramite `FileManager`               |
| **Logging**         | Utilizzo dell’API Logger di Java con `LoggerUtil`               |
| **JUnit 5**         | Copertura test con framework moderno                            |
| **Collezioni**      | Uso di `List`, `Iterator`, `Generics`                           |
| **Gestione errori** | Catch con messaggi chiari e rilancio tramite `TaskException`    |

---

## Buone pratiche di sicurezza

- Niente credenziali hardcoded
- Tutti gli input sono validati
- Eccezioni gestite in modo controllato
- Nessun `stack trace` mostrato all’utente
- Logging centralizzato

---

## Esecuzione e test

### Compilazione

```bash
mvn clean compile
```

### Esecuzione

```bash
mvn exec:java
```

### Test

```bash
mvn test
```

---

## Generazione documentazione Javadoc

```bash
mvn javadoc:javadoc
```

Output disponibile in:  
`target/reports/apidocs/index.html`

---

## Generazione diagramma UML

```bash
mvn generate-sources
```

File generato:  
`target/plantuml/diagramma-uml.puml`

Convertibile in PNG con strumenti PlantUML.

---

## Struttura del progetto

```
src/
├── main/
│   └── java/
│       ├── mainApp/         → Classe Main (entry point)
│       ├── model/           → Task e priorità
│       ├── decorator/       → Etichette decorative
│       ├── factory/         → Creazione Task
│       ├── strategy/        → Ordinamenti
│       ├── memento/         → Gestione stato
│       ├── service/         → Gestione attività
│       └── util/            → Logging e salvataggio
└── test/
    └── java/                → Classi di test JUnit 5
```

---

## Limitazioni note

- Solo interfaccia a linea di comando
- Salvataggio su file semplice
- Undo solo per modifiche ai task

---

## Miglioramenti futuri

- Interfaccia grafica JavaFX
- Salvataggio in formato JSON/XML
- Multiutente con autenticazione
- Notifiche per scadenze imminenti

---

## Autore

**Gian Luca Baccani** – Progetto finale per il corso di Programmazione a Oggetti (2025)

---

## Licenza

Distribuito con licenza **MIT**
