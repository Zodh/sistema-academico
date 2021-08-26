package br.com.unip.sistema_academico.servico.processamento.buscador;

import br.com.unip.sistema_academico.modelo.Aluno;
import br.com.unip.sistema_academico.modelo.Rendimento.Rendimento;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BuscadorDeDadosDeUmAluno {

  private final BuscadorDeCursos buscadorDeCursos = new BuscadorDeCursos();
  private Aluno aluno;

  public Aluno procurarAlunoPorId(String id) {
    BuscadorDeAlunos buscadorDeAlunos = new BuscadorDeAlunos();
    List<Aluno> listaDeAlunos;
    try {
      listaDeAlunos = (List<Aluno>) buscadorDeAlunos.listarValoresDoArquivo();
      this.aluno = listaDeAlunos.stream().filter(alunoDaLista -> alunoDaLista.getId().equals(id))
          .collect(Collectors.toList()).get(0);
      return this.aluno;
    } catch (RuntimeException | IOException erroDuranteABuscarDeAlunos) {
      System.out.println("Não existe nenhum aluno com o ID: " + id);
    }
    return null;
  }

  public Rendimento pegarRendimentoDoAlunoEmUmCurso(String aId, String nomeDoCurso) {
    if (this.aluno == null) {
      this.aluno = procurarAlunoPorId(aId);
    }
    try {
      return buscadorDeCursos.listarRendimentosDeUmCurso(nomeDoCurso).stream()
          .filter(rendimento -> rendimento.getAluno().getId().equals(this.aluno.getId()))
          .collect(Collectors.toList()).get(0);
    } catch (RuntimeException e) {
    }
    return null;
  }

  public void listaComTodosOsRendimentosDeUmAluno(String aId) {
    try {
      List<String> listaComNomeDeCursos = new ArrayList<>();
      buscadorDeCursos.listarCursos().forEach(curso -> listaComNomeDeCursos.add(curso.getName()));
      listaComNomeDeCursos.forEach(curso -> {
        if (pegarRendimentoDoAlunoEmUmCurso(aId, curso) != null) {
          System.out.println("Curso: " + curso.replace("_", " | ").replace(".csv", "") + " - \t"
              + pegarRendimentoDoAlunoEmUmCurso(aId, curso));
        }
      });
    } catch (Exception e) {
      System.out.println("Erro! " + e.getMessage());
    }
  }

}