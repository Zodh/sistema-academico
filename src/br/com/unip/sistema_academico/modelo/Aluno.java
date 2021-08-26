package br.com.unip.sistema_academico.modelo;

import br.com.unip.sistema_academico.modelo.Rendimento.Rendimento;
import br.com.unip.sistema_academico.servico.processamento.buscador.BuscadorDeAlunos;
import br.com.unip.sistema_academico.servico.processamento.buscador.BuscadorDeDadosDeUmAluno;

import java.util.ArrayList;
import java.util.List;

public class Aluno {

    private String id;
    private String nome;
    BuscadorDeDadosDeUmAluno buscadorDeDadosDeUmAluno = new BuscadorDeDadosDeUmAluno();

    public Aluno (String aId){
        if (buscadorDeDadosDeUmAluno.procurarAlunoPorId(aId) == null){
            throw new IllegalArgumentException("Não existe nenhum aluno com este Id!");
        }
        this.id = aId;
        this.nome = buscadorDeDadosDeUmAluno.procurarAlunoPorId(aId).getNome();
    }

    public Aluno(String aId, String aNome){
        this.id = aId;
        this.nome = aNome;
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String toString(){
        return "Nome: " + this.nome + "\tID: " + this.id + "\t";
    }

}