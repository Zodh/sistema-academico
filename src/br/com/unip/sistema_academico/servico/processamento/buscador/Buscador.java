package br.com.unip.sistema_academico.servico.processamento.buscador;

import br.com.unip.sistema_academico.servico.processamento.Executar;
import java.io.IOException;
import java.util.List;

public abstract class Buscador implements Executar {

  @Override
  public void executarTarefa() throws Exception {
    if (naoPossuiRetorno()) {
      throw new Exception("A lista não retornou nenhum valor! ");
    }
    listarValoresDoArquivo().forEach(System.out::println);
  }

  public abstract List<?> listarValoresDoArquivo() throws IOException;

  public abstract boolean naoPossuiRetorno() throws IOException;

}