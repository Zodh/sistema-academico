package br.com.unip.sistema_academico.visualizacao.saida;

import br.com.unip.sistema_academico.visualizacao.Integracao;

public class Main {

  public static void main(String[] args) {
    Integracao integracao = new Integracao();
    try {
      integracao.menu();
    } catch (Exception e) {
      System.out.println("Erro: " + e.getMessage());
    }
  }
}