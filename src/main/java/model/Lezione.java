package model;

import java.time.LocalTime;

/**
 * Rappresenta una lezione presente nell'orario settimanale.
 * Ogni lezione è associata a un giorno, a una fascia oraria,
 * a un insegnamento e a un'aula.
 */
public class Lezione {
    private final String giorno;
    private final LocalTime oraInizio;
    private final LocalTime oraFine;
    private final Insegnamento insegnamento;
    private final Aula aula;

    /**
     * Crea una nuova lezione con le informazioni specificate.
     *
     * @param giorno giorno della settimana in cui si svolge la lezione
     * @param oraInizio ora di inizio della lezione
     * @param oraFine ora di fine della lezione
     * @param insegnamento insegnamento associato alla lezione
     * @param aula aula in cui si svolge la lezione
     */
    public Lezione(String giorno, LocalTime oraInizio, LocalTime oraFine, Insegnamento insegnamento, Aula aula) {
        this.giorno = giorno;
        this.oraInizio = oraInizio;
        this.oraFine = oraFine;
        this.insegnamento = insegnamento;
        this.aula = aula;
    }

    /**
     * Restituisce il giorno della settimana in cui si svolge la lezione.
     *
     * @return giorno della lezione
     */
    public String getGiorno() {
        return giorno;
    }

    /**
     * Restituisce l'ora di inizio della lezione.
     *
     * @return ora di inizio
     */
    public LocalTime getOraInizio() {
        return oraInizio;
    }

    /**
     * Restituisce l'ora di fine della lezione.
     *
     * @return ora di fine
     */
    public LocalTime getOraFine() {
        return oraFine;
    }

    /**
     * Restituisce l'insegnamento associato alla lezione.
     *
     * @return insegnamento della lezione
     */
    public Insegnamento getInsegnamento() {
        return insegnamento;
    }

    /**
     * Restituisce l'aula assegnata alla lezione.
     *
     * @return aula della lezione
     */
    public Aula getAula() {
        return aula;
    }
}