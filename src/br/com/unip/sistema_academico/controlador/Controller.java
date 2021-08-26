package br.com.unip.sistema_academico.controlador;

import br.com.unip.sistema_academico.modelo.Aluno;
import br.com.unip.sistema_academico.modelo.Rendimento.Rendimento;
import br.com.unip.sistema_academico.servico.processamento.buscador.BuscadorDeAlunos;
import br.com.unip.sistema_academico.servico.processamento.buscador.BuscadorDeCursos;
import br.com.unip.sistema_academico.servico.processamento.buscador.BuscadorDeDadosDeUmAluno;
import br.com.unip.sistema_academico.servico.processamento.criador.CriadorDeAluno;
import br.com.unip.sistema_academico.servico.processamento.criador.CriadorDeCurso;
import br.com.unip.sistema_academico.servico.processamento.criador.CriadorDeRendimento;
import java.util.List;

public class Controller {

  public void listarOsNomesDoCursoParaCopia() {
    BuscadorDeCursos buscadorDeCursos = new BuscadorDeCursos();
    buscadorDeCursos.listarCursos().forEach(curso -> System.out.println(curso.getName()));
  }

  public void listarTodosOsCursosDados() {
    try {
      BuscadorDeCursos buscadorDeCursos = new BuscadorDeCursos();
      buscadorDeCursos.listarCursos().forEach(
          curso -> System.out.println(curso.getName().replace("_", " ").replace(".csv", " ")));
    } catch (Exception e) {
      System.out.println("Erro ao listar todos os cursos cadastrados - " + e.getMessage());
    }
  }

  public void listarTodosOsAlunosInscritos() {
    try {
      BuscadorDeAlunos buscadorDeAlunos = new BuscadorDeAlunos();
      buscadorDeAlunos.executarTarefa();
    } catch (Exception e) {
      System.out.println("Erro ao listar todos os alunos cadastrados - " + e.getMessage());
    }
  }

  public void listarOHistoricoDeUmAluno(String id) {
    try {
      BuscadorDeDadosDeUmAluno buscadorDeDadosDeUmAluno = new BuscadorDeDadosDeUmAluno();
      Aluno aluno = buscadorDeDadosDeUmAluno.procurarAlunoPorId(id);
      buscadorDeDadosDeUmAluno.listaComTodosOsRendimentosDeUmAluno(aluno.getId());
    } catch (Exception e) {
      System.out.println("Erro ao listar o historico de um aluno - " + e.getMessage());
    }
  }

  public void listarORelatorioDeRendimentoDeUmCurso(String nomeDoCurso) {
    try {
      BuscadorDeCursos buscadorDeCursos = new BuscadorDeCursos();
      List<Rendimento> listaDeInfosDeUmCurso = buscadorDeCursos
          .listarRendimentosDeUmCurso(nomeDoCurso);
      System.out.println("Relatório rendimento do curso: " + nomeDoCurso);
      listaDeInfosDeUmCurso.forEach(System.out::println);
    } catch (Exception e) {
      System.out
          .println("Erro ao listar o relatorio de rendimento de um curso - " + e.getMessage());
    }
  }

  public void incluirUmNovoAluno(String id, String nome) {
    try {
      CriadorDeAluno criadorDeAluno = new CriadorDeAluno(id, nome);
      criadorDeAluno.executarTarefa();
    } catch (Exception e) {
      System.out.println("Erro ao cadastrar um novo aluno - " + e.getMessage());
    }

  }

  public void incluirUmNovoCurso(String nome, String tipo, int ano) {
    try {
      CriadorDeCurso criadorDeCurso = new CriadorDeCurso(nome, tipo, ano);
      criadorDeCurso.executarTarefa();
    } catch (Exception e) {
      System.out.println("Erro ao cadastrar um novo curso - " + e.getMessage());
    }

  }

  public void incluirUmNovoRendimento(String id, String nomeDoCurso, Rendimento rendimento) {
    try {
      CriadorDeRendimento criadorDeRendimento = new CriadorDeRendimento(id, nomeDoCurso,
          rendimento);
      criadorDeRendimento.executarTarefa();
    } catch (Exception e) {
      System.out.println("Erro ao cadastrar um novo rendimento - " + e.getMessage());
    }
  }
}
