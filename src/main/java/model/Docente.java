package model;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Rappresenta un docente registrato nel sistema di gestione dell'orario.
 * Un docente può visualizzare le proprie lezioni, indicare vincoli orari
 * e, se responsabile, gestire alcune funzionalità relative agli orari.
 */
public class Docente extends Utente {
    private final List<VincoloDocente> vincoli;
    private final boolean isResponsabile;
    private static final Logger LOGGER = Logger.getLogger(Docente.class.getName());

    /**
     * Crea un nuovo docente con i dati anagrafici e di accesso specificati.
     *
     * @param nome nome del docente
     * @param cognome cognome del docente
     * @param email email utilizzata dal docente per accedere al sistema
     * @param password password associata all'account del docente
     * @param isResponsabile true se il docente è anche responsabile degli orari,
     *                       false altrimenti
     */
    public Docente(String nome, String cognome, String email, String password, boolean isResponsabile) {
        super(nome, cognome, email, password); // chiamata costruttore utente
        this.vincoli = new ArrayList<>();
        this.isResponsabile = isResponsabile;
    }

    /**
     * Restituisce l'elenco dei vincoli orari indicati dal docente.
     * I vincoli rappresentano fasce orarie in cui il docente non può tenere lezione.
     *
     * @return lista dei vincoli orari del docente
     */
    public List<VincoloDocente> getVincoli() {
        return vincoli;
    }

    /**
     * Aggiunge un vincolo orario al docente.
     * Ogni docente può indicare al massimo tre vincoli; se il limite è già stato
     * raggiunto, il vincolo non viene aggiunto e viene registrato un messaggio di avviso.
     *
     * @param v vincolo orario da aggiungere
     */
    public void aggiungiVincolo(VincoloDocente v) {
        if (this.vincoli.size() < 3) {
            this.vincoli.add(v);
        } else {
            LOGGER.warning("Limite massimo di 3 vincoli raggiunto.");
        }
    }

    /**
     * Indica se il docente ha anche il ruolo di responsabile degli orari.
     *
     * @return true se il docente è responsabile degli orari, false altrimenti
     */
    public boolean isResponsabile() {
        return isResponsabile;
    }
}