package br.com.unip.sistema_academico.servico.processamento.criador;

import static br.com.unip.sistema_academico.servico.recebe_entrada.CaminhoParaDiretorio.getCaminhoParaDiretorio;

import br.com.unip.sistema_academico.modelo.Curso;
import br.com.unip.sistema_academico.servico.processamento.buscador.BuscadorDeCursos;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class CriadorDeCurso extends Criador {

  private final Curso curso;
  private final String nomeArquivoCurso;

  public CriadorDeCurso(String nomeDoCurso, String grade, int ano) {
    nomeDoCurso.toUpperCase(Locale.ROOT);
    grade.toUpperCase(Locale.ROOT);
    nomeArquivoCurso = nomeDoCurso + "_" + grade + "_" + ano + ".csv";
    this.curso = new Curso(nomeDoCurso, grade, ano);
  }

  @Override
  public void incluirNovo() throws IOException {
    // Adiciona curso no arquivo de cursos.
    FileWriter escritorDeCursos = new FileWriter(getCaminhoParaDiretorio() + "cursos.csv", true);
    BufferedWriter bufferDeEscritorDeCursos = new BufferedWriter(escritorDeCursos);
    PrintWriter saidaEscritaCurso = new PrintWriter(bufferDeEscritorDeCursos);
    try {
      List<List<String>> registroDeCurso = Arrays.asList(
          Arrays.asList(this.curso.getNome(), this.curso.getNivel() + ";" + this.curso.getAno()));
      for (List<String> dadoDoRegistro : registroDeCurso) {
        saidaEscritaCurso.println();
        escritorDeCursos.append(String.join(";", dadoDoRegistro));
      }
      System.out.printf("Curso " + nomeArquivoCurso + " listado no arquivo Cursos com sucesso!");
    } catch (IOException erroDuranteAEscritaDoArquivo) {
      System.out.println("Erro ao listar o curso " + nomeArquivoCurso + " no arquivo cursos.csv! "
          + erroDuranteAEscritaDoArquivo.getMessage());
    } finally {
      escritorDeCursos.flush();
      bufferDeEscritorDeCursos.close();
      saidaEscritaCurso.close();
      escritorDeCursos.close();
    }
    // Cria arquivo.
    FileWriter criadorDeArquivoDeCurso = new FileWriter(
        getCaminhoParaDiretorio() + nomeArquivoCurso);
    try {
      criadorDeArquivoDeCurso.append("");
      System.out.println("Arquivo " + nomeArquivoCurso + " criado com sucesso!");
    } catch (IOException erroDuranteAEscritaDoArquivo) {
      System.out.println(
          "Erro ao criar o curso " + nomeArquivoCurso + "! " + erroDuranteAEscritaDoArquivo
              .getMessage());
    } finally {
      criadorDeArquivoDeCurso.close();
    }
  }

  @Override
  public boolean verificarSeExiste() {
    BuscadorDeCursos buscadorDeCursos = new BuscadorDeCursos();
    try {
        if (buscadorDeCursos.listarCursos().stream()
            .filter(arquivo -> arquivo.getName().equals(nomeArquivoCurso))
            .collect(Collectors.toList()).get(0) == null) {
        }
    } catch (IndexOutOfBoundsException naoExisteNenhumCurso) {
      return false;
    }
    return true;
  }

}