package br.com.unip.sistema_academico.servico.processamento.buscador;

import static br.com.unip.sistema_academico.servico.recebe_entrada.CaminhoParaDiretorio.getCaminhoParaDiretorio;

import br.com.unip.sistema_academico.modelo.Aluno;
import br.com.unip.sistema_academico.servico.recebe_entrada.LeArquivo;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BuscadorDeAlunos extends Buscador {

  @Override
  public List<?> listarValoresDoArquivo() throws IOException {
    try {
      LeArquivo leArquivo = new LeArquivo();
      List<String> listaDeAlunos;
      listaDeAlunos = leArquivo
          .pegarListaDeRegistrosDeUmArquivo(getCaminhoParaDiretorio() + "alunos.csv");
      ArrayList<Aluno> listaFormatadaDeAlunos = new ArrayList<>();
      listaDeAlunos.forEach(
          registroDeAluno -> listaFormatadaDeAlunos.add(
              new Aluno(
                  Arrays.asList(registroDeAluno.split(";")).get(0),   // Id
                  Arrays.asList(registroDeAluno.split(";")).get(1)))  // Nome
      );
      return listaFormatadaDeAlunos;
    } catch (RuntimeException erroDuranteATratativaDasInformacoesDoArquivo) {
      System.out.println(erroDuranteATratativaDasInformacoesDoArquivo.getMessage());
    }
    return Collections.emptyList();
  }

  @Override
  public boolean naoPossuiRetorno() throws IOException {
    return listarValoresDoArquivo().isEmpty();
  }

}