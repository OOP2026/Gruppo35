package model;

/**
 * Rappresenta uno studente registrato nel sistema.
 * Uno studente eredita i dati comuni di un utente e aggiunge
 * la matricola e l'anno di corso, usati per visualizzare l'orario
 * delle lezioni relativo al proprio anno.
 */
public class Studente extends Utente {
    private String matricola;
    private String annoCorso;

    /**
     * Crea un nuovo studente con i dati anagrafici, di accesso e universitari.
     *
     * @param nome nome dello studente
     * @param cognome cognome dello studente
     * @param email email utilizzata per l'accesso al sistema
     * @param password password associata all'account
     * @param matricola matricola universitaria dello studente
     * @param annoCorso anno di corso frequentato dallo studente
     */
    public Studente(String nome, String cognome, String email, String password, String matricola, String annoCorso) {
        super(nome, cognome, email, password);
        this.matricola = matricola;
        this.annoCorso = annoCorso;
    }

    /**
     * Restituisce la matricola universitaria dello studente.
     *
     * @return matricola dello studente
     */
    public String getMatricola() {
        return matricola;
    }

    /**
     * Modifica la matricola universitaria dello studente.
     *
     * @param matricola nuova matricola dello studente
     */
    public void setMatricola(String matricola) {
        this.matricola = matricola;
    }

    /**
     * Restituisce l'anno di corso frequentato dallo studente.
     *
     * @return anno di corso dello studente
     */
    public String getAnnoCorso() {
        return annoCorso;
    }

    /**
     * Modifica l'anno di corso frequentato dallo studente.
     *
     * @param annoCorso nuovo anno di corso dello studente
     */
    public void setAnnoCorso(String annoCorso) {
        this.annoCorso = annoCorso;
    }
}