package gui;

import javax.swing.*;
import java.awt.event.ActionListener;

public class GUIdocenteresponsabile extends JFrame {	// Finestra dedicata alle funzionalità del docente responsabile
    private JPanel mainPanel;			// Pannello radice collegato alla grafica del file .form
    private JButton VisualizzaRichieste;	// Bottone per aprire l'elenco delle richieste di spostamento in attesa
    private JButton Logout;			// Bottone di logout per uscire e tornare alla schermata principale
    private JLabel Titolo;			// Etichetta del titolo per mostrare il messaggio di benvenuto personalizzato

    public GUIdocenteresponsabile(String cognomeDocente) {	// Costruttore: riceve il cognome del responsabile che ha effettuato l'accesso
        setTitle("Area Docente Responsabile");	// Imposta la scritta sulla barra del titolo della finestra
        setContentPane(mainPanel);		// Associa il pannello principale del designer alla finestra
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	// Termina l'applicazione del tutto alla chiusura della finestra con la x
        setSize(600, 450);			// Risoluzione iniziale della finestra (larghezza e altezza)
        setLocationRelativeTo(null);		// Fa apparire la finestra esattamente al centro dello schermo

        if (Titolo != null) {			// Controllo di sicurezza per evitare NullPointerException prima di modificare il testo
            Titolo.setText("Benvenuto Coordinatore, Prof. " + cognomeDocente);	// Imposta il testo di benvenuto con il cognome dinamico
        }
    }

    public void addVisualizzaRichiesteListener(ActionListener listener) {	// Passa il listener del controller al bottone per esaminare le richieste pendenti
        if (VisualizzaRichieste != null) {
            VisualizzaRichieste.addActionListener(listener);
        } else {
            System.out.println("Errore: Il bottone delle richieste del responsabile è null!");	// Messaggio di log utile in console per controllare se il name field nel .form è corretto
        }
    }

    public void addLogoutListener(ActionListener listener) {	// Passa il listener del controller al bottone di logout per gestire la disconnessione
        if (Logout != null) {
            Logout.addActionListener(listener);
        }
    }
}
