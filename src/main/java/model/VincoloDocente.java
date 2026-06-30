package model;

import java.time.LocalTime;

/**
 * Rappresenta un vincolo orario indicato da un docente.
 * Un vincolo identifica una fascia oraria di un determinato giorno
 * in cui il docente non è disponibile a svolgere lezione.
 */
public class VincoloDocente {
    private String giorno;
    private LocalTime oraInizio;
    private LocalTime oraFine;

    /**
     * Crea un nuovo vincolo orario per un docente.
     *
     * @param giorno giorno della settimana in cui il docente non è disponibile
     * @param oraInizio ora di inizio della fascia non disponibile
     * @param oraFine ora di fine della fascia non disponibile
     */
    public VincoloDocente(String giorno, LocalTime oraInizio, LocalTime oraFine) {
        this.giorno = giorno;
        this.oraInizio = oraInizio;
        this.oraFine = oraFine;
    }

    /**
     * Restituisce il giorno associato al vincolo.
     *
     * @return giorno del vincolo
     */
    public String getGiorno() {
        return giorno;
    }

    /**
     * Modifica il giorno associato al vincolo.
     *
     * @param giorno nuovo giorno del vincolo
     */
    public void setGiorno(String giorno) {
        this.giorno = giorno;
    }

    /**
     * Restituisce l'ora di inizio della fascia non disponibile.
     *
     * @return ora di inizio del vincolo
     */
    public LocalTime getOraInizio() {
        return oraInizio;
    }

    /**
     * Modifica l'ora di inizio della fascia non disponibile.
     *
     * @param oraInizio nuova ora di inizio del vincolo
     */
    public void setOraInizio(LocalTime oraInizio) {
        this.oraInizio = oraInizio;
    }

    /**
     * Restituisce l'ora di fine della fascia non disponibile.
     *
     * @return ora di fine del vincolo
     */
    public LocalTime getOraFine() {
        return oraFine;
    }

    /**
     * Modifica l'ora di fine della fascia non disponibile.
     *
     * @param oraFine nuova ora di fine del vincolo
     */
    public void setOraFine(LocalTime oraFine) {
        this.oraFine = oraFine;
    }
}