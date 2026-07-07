package model;

import java.time.LocalTime;

public class Lezione {
    private final String giorno;
    private final LocalTime oraInizio;
    private final LocalTime oraFine;
    private final Insegnamento insegnamento;
    private final Aula aula;

    public Lezione(String giorno, LocalTime oraInizio, LocalTime oraFine, Insegnamento insegnamento, Aula aula) {
        this.giorno = giorno;
        this.oraInizio = oraInizio;
        this.oraFine = oraFine;
        this.insegnamento = insegnamento;
        this.aula = aula;
    }

    public String getGiorno() {
        return giorno; }
    public LocalTime getOraInizio() {
        return oraInizio; }
    public LocalTime getOraFine() {
        return oraFine; }
    public Insegnamento getInsegnamento() {
        return insegnamento; }
    public Aula getAula() {
        return aula; }
}