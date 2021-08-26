package br.com.unip.sistema_academico.modelo;

public class Notas {

    private Nota np1;
    private Nota np2;
    private Nota reposicao;

    private Nota exame;

    public Notas(Nota aNp1, Nota aNp2, Nota aReposicao, Nota aExame){
        this.np1 = aNp1;
        this.np2 = aNp2;
        this.reposicao = aReposicao;
        this.exame = aExame;
    }

    public Nota getNp1() {
        return np1;
    }

    public Nota getNp2() {
        return np2;
    }

    public Nota getReposicao() {
        return reposicao;
    }

    public Nota getExame() {
        return exame;
    }

    @Override
    public String toString() {
        return  "NP1: " + this.np1 + "\t" +
                "NP2: " + this.np2 + "\t" +
                "SUB: " + this.reposicao + "\t" +
                "EXAME: " + this.exame + "\t";
    }

}