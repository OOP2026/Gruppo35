package model;

//classe astratta così che non si creano oggetti di tipo utente, data la generalizzazione con studente e docente
//inoltre definiamo gli attributi che verranno passati come attributi nelle classi figlie, senza doverli riscrivere
public abstract class Utente {
    private String nome;
    private String cognome;
    private String email;
    private String password;

    protected Utente(String nome, String cognome, String email, String password) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.password = password;
    }

    //get e set nel caso servissero
    public String getNome() {
        return nome; }
    public void setNome(String nome) {
        this.nome = nome; }

    public String getCognome() {
        return cognome; }
    public void setCognome(String cognome) {
        this.cognome = cognome; }

    public String getEmail() {
        return email; }
    public void setEmail(String email) {
        this.email = email; }

    public String getPassword() {
        return password; }
    public void setPassword(String password) {
        this.password = password; }
}
