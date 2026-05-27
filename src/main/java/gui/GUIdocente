package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUIdocente extends JFrame {	// Finestra principale per l'interfaccia del docente
    private JPanel mainPanel;			// Pannello principale collegato al file .form
    private JLabel MessaggioDocente;		// Etichetta per il testo di benvenuto al prof
    private JButton VisualizzaOrarioDocente;	// Bottone per consultare l'orario delle lezioni
    private JButton RichiestaSpostamentoLezione;	// Bottone per aprire il modulo di spostamento lezione
    private JButton Logout;			// Bottone per disconnettersi e tornare alla home
    private CardLayout cardLayout;		// Layout manager per switchare tra la home del docente e i sotto-pannelli
    private JPanel pannelloContenitore;		// Pannello contenitore per lo switch

    public GUIdocente(String cognomeDocente) {	// Costruttore della gui: riceve il cognome del docente loggato
        setTitle("Area Riservata Docente");	// Imposta il titolo in alto sulla finestra

        cardLayout = new CardLayout();		// Inizializza il CardLayout per la gestione delle schermate
        pannelloContenitore = new JPanel(cardLayout);	// Crea il pannello che userà il CardLayout
        pannelloContenitore.add(mainPanel, "DASHBOARD_INIZIALE");	// Carica il pannello del designer come schermata iniziale
        setContentPane(pannelloContenitore);	// Imposta il contenitore appena creato come pannello principale della finestra
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	// Chiude definitivamente il processo java quando si clicca sulla x
        setSize(500, 400);			// Dimensioni di base della finestra
        setLocationRelativeTo(null);		// Centra la finestra sullo schermo all'avvio

        inizializzaDati(cognomeDocente);	// Riempie la label con il cognome passato dal login
    }

    private void inizializzaDati(String cognomeDocente) {	// Metodo di supporto per personalizzare il messaggio di benvenuto
        if (MessaggioDocente != null) {		// Controllo di sicurezza se il designer ha agganciato bene la label
            MessaggioDocente.setText("Benvenuto nel portale docenti, Prof. " + cognomeDocente + "!");	// Imposta il testo dinamico con il cognome
        } else {
            System.out.println("Errore: MessaggioDocente è nullo! Controlla il file .form");	// Log di debug in console se qualcosa non va nel .form
        }
    }

    public void mostraPannelloSpostamento(JPanel nuovoPannello) {	// Mostra il form per lo spostamento inserendolo nel CardLayout a runtime
        try {
            pannelloContenitore.remove(nuovoPannello);	// Rimuove eventuali vecchie istanze del form per evitare duplicati
        } catch (Exception e) {}		// Ignora l'eccezione se il pannello non esisteva ancora

        pannelloContenitore.add(nuovoPannello, "FORM_SPOSTAMENTO");	// Aggiunge il nuovo form associandogli una chiave testuale
        cardLayout.show(pannelloContenitore, "FORM_SPOSTAMENTO");	// Dice al layout di switchare visivamente sul form
    }

    public void addVisualizzaOrarioDocenteListener(ActionListener listener) {	// Aggancia il listener del controller al bottone dell'orario
        if (VisualizzaOrarioDocente != null) {
            VisualizzaOrarioDocente.addActionListener(listener);
        }
    }

    public void addRichiestaSpostamentoLezioneListener(ActionListener listener) {	// Aggancia il listener del controller al bottone di spostamento lezione
        if (RichiestaSpostamentoLezione != null) {
            RichiestaSpostamentoLezione.addActionListener(listener);
        }
    }

    public void addLogoutListener(ActionListener listener) {	// Aggancia il listener del controller al bottone di logout
        if (Logout != null) {
            Logout.addActionListener(listener);
        }
    }

    public void mostraPannelloIniziale() {	// Metodo richiamato dal controller per tornare alla schermata iniziale del prof
        cardLayout.show(pannelloContenitore, "DASHBOARD_INIZIALE");
    }
}
