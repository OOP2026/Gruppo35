package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

@SuppressWarnings("java:S6212")
public class GUIdocente extends JFrame {
    private static final long serialVersionUID = 1L;

    private JPanel mainPanel;
    private JLabel messaggioDocente;
    private JButton visualizzaOrarioDocente;
    private JButton richiestaSpostamentoLezione;
    private JButton logout;
    private final CardLayout cardLayout;
    private final JPanel pannelloContenitore;

    public GUIdocente(String cognomeDocente) {
        setTitle("Area Riservata Docente");

        cardLayout = new CardLayout();
        pannelloContenitore = new JPanel(cardLayout);

        pannelloContenitore.add(mainPanel, "DASHBOARD_INIZIALE");
        setContentPane(pannelloContenitore);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);

        inizializzaDati(cognomeDocente);
    }

    private void inizializzaDati(String cognomeDocente) {	// Metodo di supporto per personalizzare il messaggio di benvenuto
        if (messaggioDocente != null) {		// Controllo di sicurezza se il designer ha agganciato bene la label
            messaggioDocente.setText("Benvenuto nel portale docenti, Prof. " + cognomeDocente + "!");	// Imposta il testo dinamico con il cognome
        } else {
            System.out.println("Errore: MessaggioDocente è nullo! Controlla il file .form");	// Log di debug in console se qualcosa non va nel .form
        }
    }

    public void mostraPannelloSpostamento(JPanel nuovoPannello) {	// Mostra il form per lo spostamento inserendolo nel CardLayout a runtime
        try {
            pannelloContenitore.remove(nuovoPannello);	// Rimuove eventuali vecchie istanze del form per evitare duplicati
        } catch (Exception ignored) {}		// Ignora l'eccezione se il pannello non esisteva ancora

        pannelloContenitore.add(nuovoPannello, "FORM_SPOSTAMENTO");	// Aggiunge il nuovo form associandogli una chiave testuale
        cardLayout.show(pannelloContenitore, "FORM_SPOSTAMENTO");	// Dice al layout di switchare visivamente sul form
    }

    public void addVisualizzaOrarioDocenteListener(ActionListener listener) {	// Aggancia il listener del controller al bottone dell'orario
        if (visualizzaOrarioDocente != null) {
            visualizzaOrarioDocente.addActionListener(listener);
        }
    }

    public void addRichiestaSpostamentoLezioneListener(ActionListener listener) {	// Aggancia il listener del controller al bottone di spostamento lezione
        if (richiestaSpostamentoLezione != null) {
            richiestaSpostamentoLezione.addActionListener(listener);
        }
    }

    public void addLogoutListener(ActionListener listener) {	// Aggancia il listener del controller al bottone di logout
        if (logout != null) {
            logout.addActionListener(listener);
        }
    }

    public void mostraPannelloIniziale() {	// Metodo richiamato dal controller per tornare alla schermata iniziale del prof
        cardLayout.show(pannelloContenitore, "DASHBOARD_INIZIALE");
    }
}