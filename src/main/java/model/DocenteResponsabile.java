package model;

/**
 * Rappresenta un docente che possiede anche il ruolo di responsabile degli orari.
 * Il responsabile può gestire la pianificazione delle lezioni, le aule e le
 * richieste di spostamento inviate dai docenti.
 */
public class DocenteResponsabile extends Docente {

    /**
     * Crea un nuovo docente responsabile degli orari.
     * Il parametro {@code isResponsabile} viene impostato automaticamente a true
     * tramite il costruttore della classe {@link Docente}.
     *
     * @param nome nome del docente responsabile
     * @param cognome cognome del docente responsabile
     * @param email email utilizzata per l'accesso al sistema
     * @param password password associata all'account
     */
    public DocenteResponsabile(String nome, String cognome, String email, String password) {
        super(nome, cognome, email, password, true);
    }
}