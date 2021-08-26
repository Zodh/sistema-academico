package br.com.unip.sistema_academico.servico.processamento.criador;

import static br.com.unip.sistema_academico.servico.recebe_entrada.CaminhoParaDiretorio.getCaminhoParaDiretorio;

import br.com.unip.sistema_academico.modelo.Rendimento.Rendimento;
import br.com.unip.sistema_academico.servico.processamento.buscador.BuscadorDeCursos;
import br.com.unip.sistema_academico.servico.processamento.buscador.BuscadorDeDadosDeUmAluno;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CriadorDeRendimento extends Criador {

  List<String> listaDeCursos = new ArrayList<>();
  private Rendimento rendimento;
  private String nomeDoCurso;
  private final BuscadorDeCursos buscadorDeCursos = new BuscadorDeCursos();
  private final BuscadorDeDadosDeUmAluno buscadorDeDadosDeUmAluno = new BuscadorDeDadosDeUmAluno();

  public CriadorDeRendimento(String aId, String aNomeDoCurso, Rendimento aRendimento) {
    try {
      buscadorDeCursos.listarCursos().forEach(curso -> listaDeCursos.add(curso.getName()));
      if (!listaDeCursos.contains(aNomeDoCurso)) {
        throw new IllegalArgumentException("Este curso não existe!");
      }
      if (buscadorDeDadosDeUmAluno.pegarRendimentoDoAlunoEmUmCurso(aId, aNomeDoCurso) == null) {
        this.rendimento = aRendimento;
        this.nomeDoCurso = aNomeDoCurso;
      } else {
        this.existe = true;
      }
    } catch (Exception erroDuranteAVerificacaoDeExistenciaDoCurso) {
    }
  }

  @Override
  public void incluirNovo() throws Exception {
    if (nomeDoCurso == null) {
      throw new Exception("Este curso não existe.");
    }
    FileWriter escritorDeRendimento = new FileWriter(getCaminhoParaDiretorio() + nomeDoCurso, true);
    BufferedWriter bufferDeEscritorDeRendimento = new BufferedWriter(escritorDeRendimento);
    PrintWriter saidaEscritaDeRendimento = new PrintWriter(bufferDeEscritorDeRendimento);
    try {
      List<List<String>> registroDeRendimento = Arrays.asList(
          Arrays.asList(this.rendimento.getAluno().getId(),
              this.rendimento.getNotas().getNp1() + ";" + this.rendimento.getNotas().getNp2() + ";"
                  + this.rendimento.getNotas().getReposicao() + ";" + this.rendimento.getNotas()
                  .getExame()));
      for (List<String> dadoDoRendimento : registroDeRendimento) {
        escritorDeRendimento.append(String.join(";", dadoDoRendimento));
      }
      saidaEscritaDeRendimento.println();
      System.out.printf(
          "Rendimento " + this.rendimento + " adicionado com sucesso no curso " + this.nomeDoCurso
              + "!");
    } catch (Exception erroDuranteAEscritaDoArquivo) {
      System.out.println(
          "Erro ao adicionar rendimento " + this.rendimento + "! " + erroDuranteAEscritaDoArquivo
              .getMessage());
    } finally {
      escritorDeRendimento.flush();
      bufferDeEscritorDeRendimento.close();
      saidaEscritaDeRendimento.close();
      escritorDeRendimento.close();
    }
  }

  @Override
  public boolean verificarSeExiste() {
    return this.existe;
  }
}
