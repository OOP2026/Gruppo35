package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUIhome extends JFrame {		// Finestra principale di ingresso (punto di partenza)
    private JPanel panel1;			// Pannello radice che contiene l'intera interfaccia
    private JPanel mainPanel;			// Pannello centrale configurato con CardLayout per switchare le schermate
    private JPanel panelBottoni;		// Pannello della schermata iniziale con la scelta del ruolo
    private JPanel panelLogin;			// Pannello con i campi di testo per l'autenticazione
    private JLabel Home;			// Etichetta del titolo principale del portale
    private JButton Docente;			// Bottone per selezionare l'accesso come docente normale
    private JButton Studente;			// Bottone per selezionare l'accesso come studente
    private JTextField Email;			// Campo di testo per l'inserimento dell'email utente
    private JTextField password;		// Campo di testo per l'inserimento della password
    private JButton Accedi;			// Bottone per confermare l'invio dei dati di login
    private JButton Indietro;			// Bottone per annullare il login e tornare alla scelta del ruolo
    private JButton DocenteResponsabile;	// Bottone per selezionare l'accesso come coordinatore/responsabile
    private JTextField CodiceResponsabile;	// Campo di testo aggiuntivo per il codice di sicurezza del responsabile

    public GUIhome() {				// Costruttore: configura le proprietà di base della finestra iniziale
        setTitle("Portale Universitario");	// Imposta il titolo sulla barra superiore della finestra
        setContentPane(panel1);			// Associa il pannello principale alla finestra
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	// Chiude l'applicazione quando si preme la x della finestra
        setSize(450, 350);			// Imposta le dimensioni della finestra di login
        setLocationRelativeTo(null);		// Centra la finestra sullo schermo dell'utente
    }

    public void mostraSchermata(String nomeCard) {	// Metodo per cambiare schermata
        CardLayout cl = (CardLayout) mainPanel.getLayout();	// Recupera il gestore CardLayout dal pannello centrale
        cl.show(mainPanel, nomeCard);		// Mostra la schermata corrispondente al nome passato come parametro
        mainPanel.revalidate();			// Ricalcola il layout per evitare glitch grafici dopo lo switch
        mainPanel.repaint();			// Rinfresca visivamente i componenti grafici del pannello
    }

    public void addDocenteListener(ActionListener l) {	// Aggancia il listener del controller al bottone Docente
        Docente.addActionListener(l); }
    public void addStudenteListener(ActionListener l) {	// Aggancia il listener del controller al bottone Studente
        Studente.addActionListener(l); }
    public void addAccediListener(ActionListener listener) {	// Aggancia il listener al bottone Accedi per verificare le credenziali
        Accedi.addActionListener(listener);
    }
    public void addIndietroListener(ActionListener listener) {	// Aggancia il listener al bottone Indietro per tornare alla home
        Indietro.addActionListener(listener);
    }
    public void addDocenteResponsabileListener(ActionListener listener) {	// Aggancia il listener al bottone del Docente Responsabile
        DocenteResponsabile.addActionListener(listener);
    }
    public String getCodiceInput() {		// Restituisce il codice inserito dal responsabile eliminando spazi vuoti
        return CodiceResponsabile.getText().trim();
    }
    public void impostaVisibilitaCodice(boolean visibile) {	// Mostra o nasconde il campo codice a seconda del ruolo selezionato
        CodiceResponsabile.setVisible(visibile);	// Cambia lo stato di visibilità del componente
        CodiceResponsabile.revalidate();	// Aggiorna la struttura del layout dopo il cambio di visibilità
        CodiceResponsabile.repaint();		// Ridisegna il componente a schermo
    }
    public String getEmailInput() {		// Restituisce il testo digitato nel campo Email
        return Email.getText(); }
    public String getPasswordInput() {		// Restituisce il testo digitato nel campo Password
        return password.getText();
    }
}