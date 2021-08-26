package br.com.unip.sistema_academico.modelo;

import java.util.List;

public class Curso {

    private String nome;
    private String nivel;
    private int ano;


    public Curso(String aNome, String aNivel, int aAno){
        if (aNivel.equals("GRADUACAO") || aNivel.equals("POS_GRADUACAO")){
            this.nome = aNome;
            this.nivel = aNivel;
            this.ano = aAno;
        }
        else {
            throw new IllegalArgumentException("Tipo de graduação inválido!");
        }
    }

    public String getNome() {
        return nome;
    }

    public String getNivel() {
        return nivel;
    }

    public int getAno() {
        return ano;
    }

}