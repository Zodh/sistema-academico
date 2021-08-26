package br.com.unip.sistema_academico.servico.processamento.criador;

import br.com.unip.sistema_academico.servico.processamento.Executar;

public abstract class Criador implements Executar {

  protected boolean existe = false;

  @Override
  public void executarTarefa() throws Exception {
    if (verificarSeExiste()) {
      throw new IllegalArgumentException("Já existe um cadastro com estes dados!");
    }
    incluirNovo();
  }

  public abstract void incluirNovo() throws Exception;

  public abstract boolean verificarSeExiste();
}
