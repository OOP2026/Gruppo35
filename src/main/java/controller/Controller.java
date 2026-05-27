package controller;

import gui.GUIdocente;
import gui.GUIdocenteresponsabile;
import gui.GUIstudente;
import gui.GUIrichiestaspostamentolezione;
import model.Docente;
import model.Studente;
import model.RichiestaSpostamento;
import gui.GUIhome;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Controller {							// Classe centrale per la gestione degli eventi
    private GUIhome gui;							// Riferimento alla finestra di login principale
    private String tipoUtenteCorrente = "";					// Stringa per tenere traccia del ruolo selezionato (DOCENTE/STUDENTE/RESPONSABILE)
    private ArrayList<Docente> docentiDisponibili;				// Lista in memoria dei docenti registrati nel sistema
    private ArrayList<Studente> studentiDisponibili;				// Lista in memoria degli studenti registrati nel sistema
    private ArrayList<RichiestaSpostamento> richiesteSpostamento = new ArrayList<>();	// Archivio dinamico delle richieste di spostamento create a runtime

    public Controller(GUIhome gui, ArrayList<Docente> docenti, ArrayList<Studente> studenti) {	// Costruttore: collega la home e inizializza i dati fittizi (in attesa del db)
        this.gui = gui;
        this.docentiDisponibili = docenti;
        this.studentiDisponibili = studenti;

        this.gui.addDocenteListener(new DocenteSelezionatoListener());		// Collega il click sul ruolo Docente
        this.gui.addStudenteListener(new StudenteSelezionatoListener());	// Collega il click sul ruolo Studente
        this.gui.addAccediListener(new AccediListener());			// Collega il click sul pulsante di Login
        this.gui.addIndietroListener(new IndietroListener());			// Collega il pulsante per tornare alla scelta ruolo
        this.gui.addDocenteResponsabileListener(new DocenteResponsabileSelezionatoListener());	// Collega il click sul ruolo Responsabile
    }

    private class DocenteSelezionatoListener implements ActionListener {	// Gestore del click sul ruolo Docente
        @Override
        public void actionPerformed(ActionEvent e) {
            tipoUtenteCorrente = "DOCENTE";					// Imposta il flag sul ruolo docente
            gui.impostaVisibilitaCodice(false);				// Nasconde il campo del codice di sicurezza del responsabile
            gui.mostraSchermata("SchermataLogin");				// Carica la schermata di login
        }
    }

    private class StudenteSelezionatoListener implements ActionListener {	// Gestore del click sul ruolo Studente
        @Override
        public void actionPerformed(ActionEvent e) {
            tipoUtenteCorrente = "STUDENTE";					// Imposta il flag sul ruolo studente
            gui.impostaVisibilitaCodice(false);				// Nasconde il campo del codice di sicurezza
            gui.mostraSchermata("SchermataLogin");				// Carica la schermata di login
        }
    }

    private class DocenteResponsabileSelezionatoListener implements ActionListener {	// Gestore del click sul ruolo Coordinatore
        @Override
        public void actionPerformed(ActionEvent e) {
            tipoUtenteCorrente = "RESPONSABILE";				// Imposta il flag sul ruolo del responsabile
            gui.impostaVisibilitaCodice(true);					// Mostra a schermo la casella per il codice numerico di sicurezza
            gui.mostraSchermata("SchermataLogin");				// Carica la schermata di login
        }
    }

    private class IndietroListener implements ActionListener {			// Gestore del tasto di annullamento login
        @Override
        public void actionPerformed(ActionEvent e) {
            tipoUtenteCorrente = "";						// Svuota il ruolo precedentemente selezionato
            gui.mostraSchermata("SchermataBottoni");				// Torna alla schermata iniziale dei tre bottoni grandi
        }
    }

    private class AccediListener implements ActionListener {			// Logica di autenticazione ed smistamento degli utenti nelle rispettive aree
        @Override
        public void actionPerformed(ActionEvent e) {
            String emailInserita = gui.getEmailInput();				// Recupera la stringa inserita nella casella email
            String passwordInserita = gui.getPasswordInput();			// Recupera la stringa inserita nella casella password
            boolean loginSuccesso = false;					// Flag per verificare se le credenziali combaciano con un utente esistente

            if (tipoUtenteCorrente.equals("DOCENTE")) {				// Flusso di login per un docente ordinario
                for (Docente d : docentiDisponibili) {
                    if (d.getEmail().equals(emailInserita) && d.getPassword().equals(passwordInserita)) {	// Controllo email e password nel vettore
                        loginSuccesso = true;					// Segna che l'autenticazione è andata a buon fine
                        JOptionPane.showMessageDialog(gui, "Benvenuto Prof. " + d.getCognome() + "!", "Login Riuscito", JOptionPane.INFORMATION_MESSAGE);	// Mostra popup di successo

                        gui.dispose();						// Chiude del tutto la vecchia finestra di login

                        GUIdocente dashboardDocente = new GUIdocente(d.getCognome());	// Istanzia la nuova dashboard per il docente

                        dashboardDocente.addRichiestaSpostamentoLezioneListener(new RichiestaSpostamentoLezioneListener(dashboardDocente, d));	// Passa la view e l'oggetto del prof loggato al form spostamenti
                        dashboardDocente.addVisualizzaOrarioDocenteListener(new VisualizzaOrarioListener());	// Collega l'apertura del popup dell'orario

                        dashboardDocente.addLogoutListener(new ActionListener() {	// Gestione disconnessione direttamente in linea
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                dashboardDocente.dispose();			// Distrugge la schermata privata del docente
                                gui.mostraSchermata("SchermataBottoni");	// Reimposta la home iniziale
                                gui.setVisible(true);				// Rende di nuovo visibile la schermata di login principale
                            }
                        });

                        dashboardDocente.setVisible(true);			// Mostra a video la dashboard docente
                        break;							// Ferma il ciclo for dopo aver trovato l'utente corretto
                    }
                }
            }
            else if (tipoUtenteCorrente.equals("RESPONSABILE")) {		// Flusso di login per il docente di governance
                String codiceInserito = gui.getCodiceInput();			// Estrae il codice numerico dal campo dedicato

                for (Docente d : docentiDisponibili) {
                    if (d.getEmail().equals(emailInserita) && d.getPassword().equals(passwordInserita)) {	// Verifica le credenziali del professore
                        if (codiceInserito.equals("2222")) {			// Verifica l'esattezza del codice di sicurezza statico
                            loginSuccesso = true;				// Autenticazione autorizzata
                            JOptionPane.showMessageDialog(gui, "Benvenuto Prof. " + d.getCognome() + " in qualità di Responsabile!", "Login Riuscito", JOptionPane.INFORMATION_MESSAGE);

                            gui.dispose();					// Chiude la finestra di login precedente

                            GUIdocenteresponsabile dashboardResp = new GUIdocenteresponsabile(d.getCognome());	// Crea la finestra amministrativa
                            dashboardResp.addVisualizzaRichiesteListener(new GestioneRichiesteResponsabileListener(dashboardResp));	// Associa il gestore per valutare le richieste

                            dashboardResp.addLogoutListener(new ActionListener() {	// Gestione logout del responsabile
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    dashboardResp.dispose();			// Chiude l'area amministrativa
                                    gui.mostraSchermata("SchermataBottoni");	// Ripristina lo stato iniziale del login
                                    gui.setVisible(true);			// Mostra nuovamente la schermata home
                                }
                            });
                            dashboardResp.setVisible(true);			// Visualizza la dashboard responsabile
                            break;						// Interrompe la ricerca
                        }
                    }
                }
            }
            else if (tipoUtenteCorrente.equals("STUDENTE")) {			// Flusso di login per l'utenza studente
                for (Studente s : studentiDisponibili) {
                    if (s.getEmail().equals(emailInserita) && s.getPassword().equals(passwordInserita)) {	// Controlla la corrispondenza dati dello studente
                        loginSuccesso = true;					// Autenticazione completata con successo
                        JOptionPane.showMessageDialog(gui, "Benvenuto " + s.getNome() + " " + s.getCognome() + "!");

                        gui.dispose();					// Elimina la schermata di login corrente

                        GUIstudente dashboardStudente = new GUIstudente(s.getNome());	// Inizializza la dashboard dello studente passando il nome
                        dashboardStudente.addVisualizzaOrarioListener(new VisualizzaOrarioListener());	// Associa l'evento per la visualizzazione dell'orario lezioni

                        dashboardStudente.addIndietroListener(new ActionListener() {	// Logica pulsante indietro/logout studente
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                dashboardStudente.dispose();			// Chiude la dashboard studente
                                gui.mostraSchermata("SchermataBottoni");	// Azzera la vista del login portandolo all'inizio
                                gui.setVisible(true);				// Riapre il login primario
                            }
                        });
                        dashboardStudente.setVisible(true);			// Mostra l'area privata dello studente
                        break;							// Esce dal ciclo di scansione
                    }
                }
            }

            if (!loginSuccesso) {						// Schermata di errore se nessun record combacia con i dati passati
                JOptionPane.showMessageDialog(gui, "Dati errati per la sezione " + tipoUtenteCorrente.toLowerCase() + ".", "Errore di Autenticazione", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class RichiestaSpostamentoLezioneListener implements ActionListener {		// Gestore per l'apertura e la sottomissione del form spostamento lezioni
        private GUIdocente dashboard;						// Riferimento alla dashboard del docente richiedente
        private Docente docenteLoggato;						// Oggetto del docente loggato per legarlo all'insegnamento

        public RichiestaSpostamentoLezioneListener(GUIdocente dashboard, Docente docente) {
            this.dashboard = dashboard;
            this.docenteLoggato = docente;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            GUIrichiestaspostamentolezione formView = new GUIrichiestaspostamentolezione();	// Genera l'istanza del pannello del form

            formView.addInviaListener(new ActionListener() {			// Aggancia l'azione al tasto Invia all'interno del form
                @Override
                public void actionPerformed(ActionEvent ev) {
                    try {
                        String giornoInserito = formView.getDataInput();	// Prende la stringa digitata nel campo data
                        LocalTime inizioInserito = LocalTime.parse(formView.getOraInizioInput());	// Parsifica la stringa in formato LocalTime per l'orario inizio
                        LocalTime fineInserito = LocalTime.parse(formView.getOraFineInput());		// Parsifica la stringa per l'orario di fine

                        model.Aula aulaTest = new model.Aula("Aula 1");		// Genera un oggetto aula fittizio per soddisfare il modello
                        model.Insegnamento insegnamentoTest = new model.Insegnamento("Basi di dati", 6, "I", docenteLoggato);	// Costruisce la materia associando il prof loggato
                        model.Lezione lezioneSimulata = new model.Lezione(	// Crea l'istanza della lezione originaria da spostare
                                giornoInserito,
                                inizioInserito,
                                fineInserito,
                                insegnamentoTest,
                                aulaTest
                        );

                        RichiestaSpostamento nuovaRichiesta = new RichiestaSpostamento(	// Genera l'oggetto richiesta con i nuovi dati proposti
                                lezioneSimulata,
                                giornoInserito,
                                inizioInserito,
                                fineInserito
                        );

                        richiesteSpostamento.add(nuovaRichiesta);		// Inserisce la richiesta creata nell'elenco globale dei dati in memoria

                        JOptionPane.showMessageDialog(dashboard, "Richiesta registrata in stato: IN ATTESA!");

                        dashboard.mostraPannelloIniziale();			// Richiama il CardLayout per nascondere il form e tornare alla home del docente

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(dashboard, "Formato dati non valido! Controlla i campi (HH:MM).", "Errore", JOptionPane.ERROR_MESSAGE);	// Gestisce i crash di parsing orario errato
                    }
                }
            });

            formView.addAnnullaListener(new ActionListener() {			// Gestore del tasto di annullamento interno al modulo
                @Override
                public void actionPerformed(ActionEvent ev) {
                    dashboard.mostraPannelloIniziale();				// Chiude semplicemente il form a runtime tornando alla dashboard senza salvare
                }
            });

            dashboard.mostraPannelloSpostamento(formView);			// Sostituisce visivamente il pannello inserendo la schermata del form inserimento
        }
    }

    private class GestioneRichiesteResponsabileListener implements ActionListener {	// Logica decisionale del responsabile per esaminare la lista delle richieste inoltrate
        private GUIdocenteresponsabile dashboard;

        public GestioneRichiesteResponsabileListener(GUIdocenteresponsabile dashboard) {
            this.dashboard = dashboard;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (richiesteSpostamento.isEmpty()) {				// Controllo preliminare per verificare se la lista in memoria contiene elementi
                JOptionPane.showMessageDialog(dashboard, "Non ci sono richieste di spostamento pendenti.", "Lista Vuota", JOptionPane.INFORMATION_MESSAGE);
                return;								// Interrompe l'esecuzione del listener
            }

            for (RichiestaSpostamento r : richiesteSpostamento) {		// Scansiona l'intero archivio delle richieste inserite
                if (r.getStato().toString().equals("IN_ATTESA")) {		// Filtra mostrando solo quelle non ancora valutate

                    String messaggio = "Richiesta Spostamento:\n" +		// Compone la stringa informativa leggendo i campi dai modelli collegati
                            "Insegnamento: " + r.getLezione().getInsegnamento().getNome() + "\n" +
                            "Giorno: " + r.getGiorno() + "\n" +
                            "Nuovo Orario: " + r.getOraInizio() + " - " + r.getOraFine() + "\n\n" +
                            "Vuoi approvare questa richiesta?";

                    int scelta = JOptionPane.showOptionDialog(dashboard, messaggio, "Gestione Richiesta",	// Genera la finestra di dialogo interattiva con tre bottoni custom
                            JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                            new Object[]{"Approva", "Rifiuta", "Passa Prossima"}, "Approva");

                    if (scelta == JOptionPane.YES_OPTION) {			// Intercetta il click sul tasto Approva
                        r.setStato(model.StatoRichiesta.APPROVATA);		// Varia lo stato interno dell'oggetto modificando l'enum su APPROVATA
                        JOptionPane.showMessageDialog(dashboard, "Richiesta Approvata!");
                    } else if (scelta == JOptionPane.NO_OPTION) {		// Intercetta il click sul tasto Rifiuta
                        r.setStato(model.StatoRichiesta.RIFIUTATA);		// Varia lo stato interno dell'oggetto impostandolo su RIFIUTATA
                        JOptionPane.showMessageDialog(dashboard, "Richiesta Rifiutata!");
                    }
                }
            }
        }
    }

    private class VisualizzaOrarioListener implements ActionListener {		// Gestore comune per l'output dell'orario scolastico fisso
        @Override
        public void actionPerformed(ActionEvent e) {
            String orarioStatico = "<html>" +					// Stringa formattata con tag HTML per andare a capo nel popup standard
                    "<h3><b>Il tuo Orario delle Lezioni Settimanale:</b></h3><br>" +
                    "<b>Lunedì:</b> Algebra (08:45 - 10:45), Basi di dati (11:00 - 13:00), Programmazione OO (14:00 - 16:00)<br>" +
                    "<b>Mercoledì:</b> Porgrammazione OO (14:00 - 16:00), Basi di dati (16:00 - 18:00)<br>" +
                    "<b>Giovedì:</b> Programmazione OO (08:30 - 10:30), Basi di dati (11:00 - 13:00), Algebra (14:00 - 16:00)<br>" +
                    "</html>";

            JOptionPane.showMessageDialog(null, orarioStatico, "Orario Lezioni", JOptionPane.INFORMATION_MESSAGE);	// Apre la finestra modale per visualizzare l'orario a schermo
        }
    }
}
