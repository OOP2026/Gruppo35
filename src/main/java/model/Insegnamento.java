package model;

/**
 * Rappresenta un insegnamento attivo nell'anno accademico.
 * Ogni insegnamento è caratterizzato da un nome, un numero di CFU,
 * un anno di corso e un docente titolare.
 */
public class Insegnamento {
    private final String nome;
    private final int cfu;
    private final String anno;
    private final Docente docenteTitolare;

    /**
     * Crea un nuovo insegnamento con le informazioni specificate.
     *
     * @param nome nome dell'insegnamento
     * @param cfu numero di crediti formativi universitari associati all'insegnamento
     * @param anno anno di corso in cui l'insegnamento è previsto
     * @param docenteTitolare docente titolare dell'insegnamento
     */
    public Insegnamento(String nome, int cfu, String anno, Docente docenteTitolare) {
        this.nome = nome;
        this.cfu = cfu;
        this.anno = anno;
        this.docenteTitolare = docenteTitolare;
    }

    /**
     * Restituisce il nome dell'insegnamento.
     *
     * @return nome dell'insegnamento
     */
    public String getNome() {
        return nome;
    }

    /**
     * Restituisce il numero di CFU dell'insegnamento.
     *
     * @return numero di CFU
     */
    public int getCfu() {
        return cfu;
    }

    /**
     * Restituisce l'anno di corso associato all'insegnamento.
     *
     * @return anno di corso dell'insegnamento
     */
    public String getAnno() {
        return anno;
    }

    /**
     * Restituisce il docente titolare dell'insegnamento.
     *
     * @return docente titolare
     */
    public Docente getDocenteTitolare() {
        return docenteTitolare;
    }
}