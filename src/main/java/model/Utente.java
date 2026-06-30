package model;

/**
 * Rappresenta un utente generico del sistema.
 * Contiene le informazioni comuni a tutti gli utenti registrati,
 * come nome, cognome, email e password.
 * <p>
 * La classe è astratta perché nel sistema vengono utilizzati utenti
 * specializzati, come {@link Studente} e {@link Docente}.
 */
public abstract class Utente {
    private String nome;
    private String cognome;
    private String email;
    private String password;

    /**
     * Crea un nuovo utente con i dati di accesso e anagrafici specificati.
     * Il costruttore è protetto perché la classe è astratta e deve essere
     * istanziata solo tramite le sottoclassi.
     *
     * @param nome nome dell'utente
     * @param cognome cognome dell'utente
     * @param email email utilizzata per accedere al sistema
     * @param password password associata all'account dell'utente
     */
    protected Utente(String nome, String cognome, String email, String password) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.password = password;
    }

    /**
     * Restituisce il nome dell'utente.
     *
     * @return nome dell'utente
     */
    public String getNome() {
        return nome;
    }

    /**
     * Modifica il nome dell'utente.
     *
     * @param nome nuovo nome dell'utente
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Restituisce il cognome dell'utente.
     *
     * @return cognome dell'utente
     */
    public String getCognome() {
        return cognome;
    }

    /**
     * Modifica il cognome dell'utente.
     *
     * @param cognome nuovo cognome dell'utente
     */
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    /**
     * Restituisce l'email dell'utente.
     *
     * @return email dell'utente
     */
    public String getEmail() {
        return email;
    }

    /**
     * Modifica l'email dell'utente.
     *
     * @param email nuova email dell'utente
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Restituisce la password associata all'account dell'utente.
     *
     * @return password dell'utente
     */
    public String getPassword() {
        return password;
    }

    /**
     * Modifica la password associata all'account dell'utente.
     *
     * @param password nuova password dell'utente
     */
    public void setPassword(String password) {
        this.password = password;
    }
}