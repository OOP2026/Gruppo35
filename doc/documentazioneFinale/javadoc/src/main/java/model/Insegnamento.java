package model;

public class Insegnamento {
    private final String nome;
    private final int cfu;
    private final String anno;
    private final Docente docenteTitolare;

    public Insegnamento(String nome, int cfu, String anno, Docente docenteTitolare) {
        this.nome = nome;
        this.cfu = cfu;
        this.anno = anno;
        this.docenteTitolare = docenteTitolare;
    }

    public String getNome() {
        return nome; }
    public int getCfu() {
        return cfu; }
    public String getAnno() {
        return anno; }
    public Docente getDocenteTitolare() {
        return docenteTitolare; }
}