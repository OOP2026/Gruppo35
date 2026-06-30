package model;

import java.time.LocalTime;

/**
 * Rappresenta una richiesta di spostamento di una lezione.
 * La richiesta contiene la lezione da spostare, il nuovo giorno proposto,
 * la nuova fascia oraria proposta e lo stato della richiesta.
 */
public class RichiestaSpostamento {
    private final Lezione lezione;
    private final String giorno;
    private final LocalTime oraInizio;
    private final LocalTime oraFine;
    private StatoRichiesta stato;

    /**
     * Crea una nuova richiesta di spostamento per una lezione.
     * Al momento della creazione, la richiesta viene impostata automaticamente
     * nello stato {@link StatoRichiesta#IN_ATTESA}.
     *
     * @param lezione lezione per cui viene richiesto lo spostamento
     * @param giorno nuovo giorno proposto per la lezione
     * @param oraInizio nuova ora di inizio proposta
     * @param oraFine nuova ora di fine proposta
     */
    public RichiestaSpostamento(Lezione lezione, String giorno, LocalTime oraInizio, LocalTime oraFine) {
        this.lezione = lezione;
        this.giorno = giorno;
        this.oraInizio = oraInizio;
        this.oraFine = oraFine;
        this.stato = StatoRichiesta.IN_ATTESA;
    }

    /**
     * Restituisce la lezione associata alla richiesta di spostamento.
     *
     * @return lezione da spostare
     */
    public Lezione getLezione() {
        return lezione;
    }

    /**
     * Restituisce lo stato attuale della richiesta.
     *
     * @return stato della richiesta
     */
    public StatoRichiesta getStato() {
        return stato;
    }

    /**
     * Modifica lo stato della richiesta.
     * Lo stato può essere aggiornato, ad esempio, quando il responsabile approva
     * o rifiuta la richiesta.
     *
     * @param stato nuovo stato della richiesta
     */
    public void setStato(StatoRichiesta stato) {
        this.stato = stato;
    }

    /**
     * Restituisce il nuovo giorno proposto per la lezione.
     *
     * @return giorno proposto
     */
    public String getGiorno() {
        return giorno;
    }

    /**
     * Restituisce la nuova ora di inizio proposta.
     *
     * @return ora di inizio proposta
     */
    public LocalTime getOraInizio() {
        return oraInizio;
    }

    /**
     * Restituisce la nuova ora di fine proposta.
     *
     * @return ora di fine proposta
     */
    public LocalTime getOraFine() {
        return oraFine;
    }
}