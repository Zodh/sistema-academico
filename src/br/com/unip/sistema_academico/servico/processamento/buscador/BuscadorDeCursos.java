package br.com.unip.sistema_academico.servico.processamento.buscador;

import static br.com.unip.sistema_academico.servico.recebe_entrada.CaminhoParaDiretorio.getCaminhoParaDiretorio;

import br.com.unip.sistema_academico.modelo.Nota;
import br.com.unip.sistema_academico.modelo.Notas;
import br.com.unip.sistema_academico.modelo.Rendimento.Rendimento;
import br.com.unip.sistema_academico.modelo.Rendimento.RendimentoGraduacao;
import br.com.unip.sistema_academico.modelo.Rendimento.RendimentoPosGraduacao;
import br.com.unip.sistema_academico.servico.recebe_entrada.LeArquivo;
import br.com.unip.sistema_academico.servico.recebe_entrada.comando.LeituraDeArquivos;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class BuscadorDeCursos extends Buscador {

  @Override
  public List<?> listarValoresDoArquivo() {
    try {
      List<File> listaDeArquivosDeCursos = listarCursos();
      ArrayList<Object> listaComNomeDosCursos = new ArrayList<>();
      listaDeArquivosDeCursos.forEach(
          arquivo -> listaComNomeDosCursos.add(arquivo.getName().replace(".csv", ""))
      );
      return listaComNomeDosCursos;
    } catch (RuntimeException erroDuranteATratativaDasInformacoesDoArquivo) {
      System.out.println(erroDuranteATratativaDasInformacoesDoArquivo.getMessage());
    }
    return Collections.emptyList();
  }

  @Override
  public boolean naoPossuiRetorno() {
    return listarValoresDoArquivo().isEmpty();
  }

  public List<File> listarCursos() {
    LeituraDeArquivos leituraDeArquivos = new LeituraDeArquivos();
    return leituraDeArquivos.listaDeArquivosDoDiretorio(getCaminhoParaDiretorio())
        .stream().filter(arquivo -> arquivo.getName().contains("GRADUACAO"))
        .collect(Collectors.toList());
  }

  public List<Rendimento> listarRendimentosDeUmCurso(String nomeDoCurso) {
    try {
      LeArquivo leArquivo = new LeArquivo();
      List<String> listaDeRendimentos = leArquivo
          .pegarListaDeRegistrosDeUmArquivo(getCaminhoParaDiretorio() + nomeDoCurso);
      if (nomeDoCurso.contains("POS_GRADUACAO")) {

      }
      List<Rendimento> listaFormatadaDeRendimentos = new ArrayList<>();
      if (nomeDoCurso.contains("POS_GRADUACAO")) {
        listaDeRendimentos.forEach(
            registroDeRendimento -> listaFormatadaDeRendimentos.add(
                new RendimentoPosGraduacao(
                    Arrays.asList(registroDeRendimento.split(";")).get(0),
                    // Id
                    new Notas(
                        new Nota(Double
                            .parseDouble(Arrays.asList(registroDeRendimento.split(";")).get(1))),
                        // NP1
                        new Nota(Double
                            .parseDouble(Arrays.asList(registroDeRendimento.split(";")).get(2))),
                        // NP2
                        new Nota(Double
                            .parseDouble(Arrays.asList(registroDeRendimento.split(";")).get(3))),
                        // SUB
                        new Nota(Double.parseDouble(
                            Arrays.asList(registroDeRendimento.split(";")).get(4)))))));    // EXAME

      } else {
        listaDeRendimentos.forEach(
            registroDeRendimento -> listaFormatadaDeRendimentos.add(
                new RendimentoGraduacao(
                    Arrays.asList(registroDeRendimento.split(";")).get(0),
                    // Id
                    new Notas(
                        new Nota(Double
                            .parseDouble(Arrays.asList(registroDeRendimento.split(";")).get(1))),
                        // NP1
                        new Nota(Double
                            .parseDouble(Arrays.asList(registroDeRendimento.split(";")).get(2))),
                        // NP2
                        new Nota(Double
                            .parseDouble(Arrays.asList(registroDeRendimento.split(";")).get(3))),
                        // SUB
                        new Nota(Double.parseDouble(
                            Arrays.asList(registroDeRendimento.split(";")).get(4)))))));    // EXAME

      }
      return listaFormatadaDeRendimentos;
    } catch (RuntimeException | IOException erroDuranteABuscaDosRendimentosDeUmCurso) {
      System.out.println("Erro durante a busca dos rendimentos de um curso! Erro: "
          + erroDuranteABuscaDosRendimentosDeUmCurso.getMessage());
    }
    return Collections.emptyList();
  }

}