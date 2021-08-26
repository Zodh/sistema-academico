package br.com.unip.sistema_academico.servico.processamento.criador;

import static br.com.unip.sistema_academico.servico.recebe_entrada.CaminhoParaDiretorio.getCaminhoParaDiretorio;

import br.com.unip.sistema_academico.modelo.Aluno;
import br.com.unip.sistema_academico.servico.processamento.buscador.BuscadorDeDadosDeUmAluno;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

public class CriadorDeAluno extends Criador {

  private final Aluno aluno;
  private final BuscadorDeDadosDeUmAluno buscadorDeDadosDeUmAluno = new BuscadorDeDadosDeUmAluno();

  public CriadorDeAluno(String aId, String nome) {
    if (buscadorDeDadosDeUmAluno.procurarAlunoPorId(aId) != null) {
      this.existe = true;
    }
    this.aluno = new Aluno(aId, nome);
  }

  @Override
  public void incluirNovo() throws IOException {
    FileWriter escritorDeAlunos = new FileWriter(getCaminhoParaDiretorio() + "alunos.csv", true);
    BufferedWriter bufferDeEscritorDeAlunos = new BufferedWriter(escritorDeAlunos);
    PrintWriter saidaEscritaAluno = new PrintWriter(bufferDeEscritorDeAlunos);
    try {
      List<List<String>> registroDeAluno = Arrays.asList(
          Arrays.asList(this.aluno.getId(), this.aluno.getNome()));
      for (List<String> dadoDoRegistro : registroDeAluno) {
        escritorDeAlunos.append(String.join(";", dadoDoRegistro));
      }
      saidaEscritaAluno.println();
      System.out.printf("Aluno " + aluno + " adicionado com sucesso!");
    } catch (IOException erroDuranteAEscritaDoArquivo) {
      System.out.println(
          "Erro ao adicionar o aluno " + aluno + "! " + erroDuranteAEscritaDoArquivo.getMessage());
    } finally {
      escritorDeAlunos.flush();
      bufferDeEscritorDeAlunos.close();
      saidaEscritaAluno.close();
      escritorDeAlunos.close();
    }
  }

  @Override
  public boolean verificarSeExiste() {
    return existe;
  }

}