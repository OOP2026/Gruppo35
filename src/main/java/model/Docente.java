package model;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


public class Docente extends Utente {
    private final List<VincoloDocente> vincoli;
    private final boolean isResponsabile;
    private static final Logger LOGGER = Logger.getLogger(Docente.class.getName());

    public Docente(String nome, String cognome, String email, String password, boolean isResponsabile) {
        super(nome, cognome, email, password); //chiamata costruttore utente
        this.vincoli = new ArrayList<>();
        this.isResponsabile = isResponsabile;
    }

    public List<VincoloDocente> getVincoli() {
        return vincoli;
    }

    public void aggiungiVincolo(VincoloDocente v) {
        if (this.vincoli.size() < 3) {
            this.vincoli.add(v);
        } else {
            LOGGER.warning("Limite massimo di 3 vincoli raggiunto.");
        }
    }

    public boolean isResponsabile() {
        return isResponsabile;
    }
}