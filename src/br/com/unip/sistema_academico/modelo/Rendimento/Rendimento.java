package br.com.unip.sistema_academico.modelo.Rendimento;

import br.com.unip.sistema_academico.modelo.Aluno;
import br.com.unip.sistema_academico.modelo.Nota;
import br.com.unip.sistema_academico.modelo.Notas;
import br.com.unip.sistema_academico.servico.processamento.buscador.BuscadorDeDadosDeUmAluno;

public abstract class Rendimento {

    protected Aluno aluno;
    private Notas notas;
    protected Nota media;
    BuscadorDeDadosDeUmAluno buscadorDeDadosDeUmAluno = new BuscadorDeDadosDeUmAluno();

    public Rendimento(String aId, Notas aNotas){
        if (buscadorDeDadosDeUmAluno.procurarAlunoPorId(aId) == null){
            throw new IllegalArgumentException("Não foi encontrado nenhum aluno com este ID! " + aId);
        }
        this.aluno = buscadorDeDadosDeUmAluno.procurarAlunoPorId(aId);
        this.notas = aNotas;
        this.media = getMedia();
    }

    public boolean getAprovacao(){
        return this.media.getNota() >= 5;
    }

    public abstract Nota getMedia();

    public Aluno getAluno() {
        return aluno;
    }

    public Notas getNotas() {
        return notas;
    }

    public String toString(){
        return "Aluno: " + this.aluno + "\t" + this.getNotas() + "\t" +
                "Media: " + this.media + "\tAprovacao: " + this.getAprovacao();
    }
}