package model;

import java.time.LocalTime;

public class RichiestaSpostamento {
    private final Lezione lezione;
    private final String giorno;
    private final LocalTime oraInizio;
    private final LocalTime oraFine;
    private StatoRichiesta stato;

    public RichiestaSpostamento(Lezione lezione, String giorno, LocalTime oraInizio, LocalTime oraFine) {
        this.lezione = lezione;
        this.giorno = giorno;
        this.oraInizio = oraInizio;
        this.oraFine = oraFine;
        this.stato = StatoRichiesta.IN_ATTESA;
    }

    public Lezione getLezione() {
        return lezione; }

    public StatoRichiesta getStato() {
        return stato; }
    public void setStato(StatoRichiesta stato) {
        this.stato = stato; }

    public String getGiorno() {
        return giorno; }

    public LocalTime getOraInizio() {
        return oraInizio; }

    public LocalTime getOraFine() {
        return oraFine; }
}