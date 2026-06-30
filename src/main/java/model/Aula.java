package model;

/**
 * Rappresenta un'aula disponibile per lo svolgimento delle lezioni.
 * Ogni aula è identificata dal proprio nome, ad esempio "Aula A1".
 */
public class Aula {
    private final String nome;

    /**
     * Crea una nuova aula con il nome specificato.
     *
     * @param nome nome identificativo dell'aula
     */
    public Aula(String nome) {
        this.nome = nome;
    }

    /**
     * Restituisce il nome identificativo dell'aula.
     *
     * @return nome dell'aula
     */
    public String getNome() {
        return nome;
    }
}