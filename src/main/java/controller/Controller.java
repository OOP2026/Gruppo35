package controller;

import dao.DocenteDAO;
import dao.StudenteDAO;
import implementazionedao.DocentePostgresDAO;
import implementazionedao.StudentePostgresDAO;
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

/**
 * Classe centrale del modulo controller.
 * Gestisce la navigazione tra le schermate della GUI, coordina il login
 * degli utenti e collega le azioni dell'interfaccia grafica ai DAO e al model.
 * <p>
 * Il controller rappresenta l'intermediario tra la GUI e le classi del dominio,
 * secondo l'organizzazione Model-View-Controller adottata nel progetto.
 *
 * @author Alessandro Mormone
 */
public class Controller {
	private final GUIhome gui;
	private String tipoUtenteCorrente = "";
	private final DocenteDAO docenteDAO;
	private final StudenteDAO studenteDAO;
	private static final String SCHERMATA_LOGIN = "SCHERMATA_LOGIN";
	private static final String SCHERMATA_BOTTONI = "SchermataBottoni";
	private final ArrayList<RichiestaSpostamento> richiesteSpostamento = new ArrayList<>();

	/**
	 * Crea un nuovo controller associato alla finestra home dell'applicazione.
	 * Inizializza i DAO necessari per l'autenticazione e registra i listener
	 * sui pulsanti principali della GUI.
	 *
	 * @param gui finestra home principale dell'applicazione
	 */
	public Controller(GUIhome gui) {
		this.gui = gui;

		this.docenteDAO = new DocentePostgresDAO();
		this.studenteDAO = new StudentePostgresDAO();

		this.gui.addDocenteListener(new DocenteSelezionatoListener());
		this.gui.addStudenteListener(new StudenteSelezionatoListener());
		this.gui.addAccediListener(new AccediListener());
		this.gui.addIndietroListener(new IndietroListener());
		this.gui.addDocenteResponsabileListener(new DocenteResponsabileSelezionatoListener());
	}

	/**
	 * Listener associato alla selezione del login docente.
	 * Imposta il tipo di utente corrente e mostra la schermata di login.
	 */
	private class DocenteSelezionatoListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			tipoUtenteCorrente = "DOCENTE";
			gui.impostaVisibilitaCodice(false);
			gui.mostraSchermata(SCHERMATA_LOGIN);
		}
	}

	/**
	 * Listener associato alla selezione del login studente.
	 * Imposta il tipo di utente corrente e mostra la schermata di login.
	 */
	private class StudenteSelezionatoListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			tipoUtenteCorrente = "STUDENTE";
			gui.impostaVisibilitaCodice(false);
			gui.mostraSchermata(SCHERMATA_LOGIN);
		}
	}

	/**
	 * Listener associato alla selezione del login come docente responsabile.
	 * Mostra anche il campo per l'inserimento del codice responsabile.
	 */
	private class DocenteResponsabileSelezionatoListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			tipoUtenteCorrente = "RESPONSABILE";
			gui.impostaVisibilitaCodice(true);
			gui.mostraSchermata(SCHERMATA_LOGIN);
		}
	}

	/**
	 * Listener associato al pulsante per tornare alla schermata iniziale.
	 */
	private class IndietroListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			tipoUtenteCorrente = "";
			gui.mostraSchermata(SCHERMATA_BOTTONI);
		}
	}

	/**
	 * Listener che gestisce il tentativo di accesso dell'utente.
	 * In base al tipo di utente selezionato, richiama il DAO corretto
	 * e apre la dashboard corrispondente.
	 */
	private class AccediListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String emailInserita = gui.getEmailInput().trim();
			String passwordInserita = gui.getPasswordInput().trim();
			boolean loginSuccesso = false;

			switch (tipoUtenteCorrente) {
				case "DOCENTE":
					Docente d = docenteDAO.loginDocente(emailInserita, passwordInserita);
					if (d != null) {
						loginSuccesso = true;
						JOptionPane.showMessageDialog(gui, "Benvenuto Prof. " + d.getCognome() + "!", "Login Riuscito", JOptionPane.INFORMATION_MESSAGE);
						gui.dispose();

						GUIdocente dashboardDocente = new GUIdocente(d.getCognome());
						dashboardDocente.addRichiestaSpostamentoLezioneListener(new RichiestaSpostamentoLezioneListener(dashboardDocente, d));
						dashboardDocente.addVisualizzaOrarioDocenteListener(new VisualizzaOrarioListener());
						dashboardDocente.addLogoutListener(e1 -> {
							dashboardDocente.dispose();
							gui.mostraSchermata(SCHERMATA_BOTTONI);
							gui.setVisible(true);
						});
						dashboardDocente.setVisible(true);
					}
					break;

				case "RESPONSABILE":
					String codiceInserito = gui.getCodiceInput().trim();
					Docente dr = docenteDAO.loginDocente(emailInserita, passwordInserita);
					if (dr != null && codiceInserito.equals("2222")) {
						loginSuccesso = true;
						JOptionPane.showMessageDialog(gui, "Benvenuto Prof. " + dr.getCognome() + " in qualità di Responsabile!", "Login Riuscito", JOptionPane.INFORMATION_MESSAGE);
						gui.dispose();

						GUIdocenteresponsabile dashboardResp = new GUIdocenteresponsabile(dr.getCognome());
						dashboardResp.addVisualizzaRichiesteListener(new GestioneRichiesteResponsabileListener(dashboardResp));
						dashboardResp.addLogoutListener(e2 -> {
							dashboardResp.dispose();
							gui.mostraSchermata(SCHERMATA_BOTTONI);
							gui.setVisible(true);
						});
						dashboardResp.setVisible(true);
					}
					break;

				case "STUDENTE":
					Studente s = studenteDAO.loginStudente(emailInserita, passwordInserita);
					if (s != null) {
						loginSuccesso = true;
						JOptionPane.showMessageDialog(gui, "Benvenuto " + s.getNome() + " " + s.getCognome() + "!");
						gui.dispose();

						GUIstudente dashboardStudente = new GUIstudente(s.getNome());
						dashboardStudente.addVisualizzaOrarioListener(new VisualizzaOrarioListener());
						dashboardStudente.addIndietroListener(e3 -> {
							dashboardStudente.dispose();
							gui.mostraSchermata(SCHERMATA_BOTTONI);
							gui.setVisible(true);
						});
						dashboardStudente.setVisible(true);
					}
					break;

				default:
					break;
			}

			if (!loginSuccesso) {
				JOptionPane.showMessageDialog(gui, "Dati errati per la sezione " + tipoUtenteCorrente.toLowerCase() + ".", "Errore di Autenticazione", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	/**
	 * Listener che gestisce l'apertura e l'invio del form di richiesta
	 * di spostamento lezione da parte di un docente.
	 */
	private class RichiestaSpostamentoLezioneListener implements ActionListener {
		private final GUIdocente dashboard;
		private final Docente docenteLoggato;

		/**
		 * Crea il listener per la richiesta di spostamento lezione.
		 *
		 * @param dashboard dashboard del docente attualmente visualizzata
		 * @param docente docente che ha effettuato il login
		 */
		public RichiestaSpostamentoLezioneListener(GUIdocente dashboard, Docente docente) {
			this.dashboard = dashboard;
			this.docenteLoggato = docente;
		}

		/**
		 * Mostra il pannello per l'inserimento della richiesta e registra
		 * la richiesta nello stato iniziale se i dati inseriti sono validi.
		 *
		 * @param e evento generato dal pulsante della GUI
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			GUIrichiestaspostamentolezione formView = new GUIrichiestaspostamentolezione();

			formView.addInviaListener(ev -> {
				try {
					String giornoInserito = formView.getDataInput();
					LocalTime inizioInserito = LocalTime.parse(formView.getOraInizioInput());
					RichiestaSpostamento nuovaRichiesta = getRichiestaSpostamento(formView, giornoInserito, inizioInserito);
					richiesteSpostamento.add(nuovaRichiesta);

					JOptionPane.showMessageDialog(dashboard, "Richiesta registrata in stato: IN ATTESA!");
					dashboard.mostraPannelloIniziale();
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(dashboard, "Formato dati non valido! Controlla i campi (HH:MM).", "Errore", JOptionPane.ERROR_MESSAGE);
				}
			});

			formView.addAnnullaListener(ev -> dashboard.mostraPannelloIniziale());
			dashboard.mostraPannelloSpostamento(formView);
		}

		/**
		 * Crea una richiesta di spostamento a partire dai dati inseriti nel form.
		 * In questa versione viene costruita una lezione simulata per collegare
		 * la richiesta a un oggetto del model.
		 *
		 * @param formView form contenente i dati inseriti dal docente
		 * @param giornoInserito giorno proposto per lo spostamento
		 * @param inizioInserito ora di inizio proposta
		 * @return richiesta di spostamento costruita con i dati forniti
		 */
		private RichiestaSpostamento getRichiestaSpostamento(GUIrichiestaspostamentolezione formView, String giornoInserito, LocalTime inizioInserito) {
			LocalTime fineInserito = LocalTime.parse(formView.getOraFineInput());
			model.Aula aulaTest = new model.Aula("Aula 1");
			model.Insegnamento insegnamentoTest = new model.Insegnamento("Basi di dati", 6, "I", docenteLoggato);
			model.Lezione lezioneSimulata = new model.Lezione(
					giornoInserito,
					inizioInserito,
					fineInserito,
					insegnamentoTest,
					aulaTest
			);

			return new RichiestaSpostamento(
					lezioneSimulata,
					giornoInserito,
					inizioInserito,
					fineInserito
			);
		}
	}

	/**
	 * Listener che permette al docente responsabile di visualizzare e gestire
	 * le richieste di spostamento lezione inviate dai docenti.
	 */
	private class GestioneRichiesteResponsabileListener implements ActionListener {
		private final GUIdocenteresponsabile dashboard;

		/**
		 * Crea il listener per la gestione delle richieste da parte del responsabile.
		 *
		 * @param dashboard dashboard del docente responsabile
		 */
		public GestioneRichiesteResponsabileListener(GUIdocenteresponsabile dashboard) {
			this.dashboard = dashboard;
		}

		/**
		 * Mostra le richieste pendenti e permette al responsabile di approvarle
		 * o rifiutarle tramite una finestra di dialogo.
		 *
		 * @param e evento generato dal pulsante della GUI
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			if (richiesteSpostamento.isEmpty()) {
				JOptionPane.showMessageDialog(dashboard, "Non ci sono richieste di spostamento pendenti.", "Lista Vuota", JOptionPane.INFORMATION_MESSAGE);
				return;
			}

			for (RichiestaSpostamento r : richiesteSpostamento) {
				if (r.getStato().toString().equals("IN_ATTESA")) {
					String messaggio = "Richiesta Spostamento:\n" +
							"Insegnamento: " + r.getLezione().getInsegnamento().getNome() + "\n" +
							"Giorno: " + r.getGiorno() + "\n" +
							"Nuovo Orario: " + r.getOraInizio() + " - " + r.getOraFine() + "\n\n" +
							"Vuoi approvare questa richiesta?";

					int scelta = JOptionPane.showOptionDialog(
							dashboard,
							messaggio,
							"Gestione Richiesta",
							JOptionPane.YES_NO_CANCEL_OPTION,
							JOptionPane.QUESTION_MESSAGE,
							null,
							new Object[]{"Approva", "Rifiuta", "Passa Prossima"},
							"Approva"
					);

					if (scelta == JOptionPane.YES_OPTION) {
						r.setStato(model.StatoRichiesta.APPROVATA);
						JOptionPane.showMessageDialog(dashboard, "Richiesta Approvata!");
					} else if (scelta == JOptionPane.NO_OPTION) {
						r.setStato(model.StatoRichiesta.RIFIUTATA);
						JOptionPane.showMessageDialog(dashboard, "Richiesta Rifiutata!");
					}
				}
			}
		}
	}

	/**
	 * Listener condiviso per la visualizzazione dell'orario.
	 * Mostra un orario statico di esempio tramite una finestra di dialogo.
	 */
	private static class VisualizzaOrarioListener implements ActionListener {
		/**
		 * Mostra l'orario settimanale delle lezioni.
		 *
		 * @param e evento generato dal pulsante della GUI
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			String orarioStatico = "<html>" +
					"<h3><b>Il tuo Orario delle Lezioni Settimanale:</b></h3><br>" +
					"<b>Lunedì:</b> Algebra (08:45 - 10:45), Basi di dati (11:00 - 13:00), Programmazione OO (14:00 - 16:00)<br>" +
					"<b>Mercoledì:</b> Porgrammazione OO (14:00 - 16:00), Basi di dati (16:00 - 18:00)<br>" +
					"<b>Giovedì:</b> Programmazione OO (08:30 - 10:30), Basi di dati (11:00 - 13:00), Algebra (14:00 - 16:00)<br>" +
					"</html>";

			JOptionPane.showMessageDialog(null, orarioStatico, "Orario Lezioni", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}