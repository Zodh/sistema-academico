package br.com.unip.sistema_academico.visualizacao;

import br.com.unip.sistema_academico.controlador.Controller;
import br.com.unip.sistema_academico.modelo.Nota;
import br.com.unip.sistema_academico.modelo.Notas;
import br.com.unip.sistema_academico.modelo.Rendimento.Rendimento;
import br.com.unip.sistema_academico.modelo.Rendimento.RendimentoGraduacao;
import br.com.unip.sistema_academico.modelo.Rendimento.RendimentoPosGraduacao;
import java.util.Locale;
import java.util.Scanner;

public class Integracao {

  private final Scanner scanner = new Scanner(System.in);
  private final Controller controller = new Controller();

  public void menu() {

    // Código mal feito - Apenas uma execução por opção.

    int controle = 100;
    while (controle != 0) {

      System.out.println("\n\nEscolha uma operação: \n" +
          "1 - Listar todos os cursos dados.\n" +
          "2 - Listar todos os alunos inscritos.\n" +
          "3 - Listar o histórico de um determinado aluno.\n" +
          "4 - Listar o relatório de rendimento de cada curso.\n" +
          "5 - Incluir um novo aluno.\n" +
          "6 - Incluir um novo curso.\n" +
          "7 - Incluir um novo rendimento.\n" +
          "0 - Encerrar.");

      System.out.println("Operação: ");
      controle = scanner.nextInt();

      try {
        switch (controle) {
          case 1:
            listarTodosOsCursosDados();
            System.out.println("\nFim da operação de listar todos os cursos dados!");
            break;
          case 2:
            listarTodosOsAlunosInscritos();
            System.out.println("\nFim da operação de listar todos os alunos inscritos!");
            break;
          case 3:
            listarOHistoricoDeUmDeterminadoAluno();
            System.out
                .println("\nFim da operação de listar todos o historico de um determinado aluno!");
            break;
          case 4:
            listarORelatorioDeRendimentoDeUmCurso();
            System.out
                .println("\nFim da operação de listar o relatorio de rendimento de um curso!");
            break;
          case 5:
            incluirUmNovoAluno();
            System.out.println("\nFim da operação de incluir um novo aluno!");
            break;
          case 6:
            incluirUmNovoCurso();
            System.out.println("\nFim da operação de incluir um novo curso!");
            break;
          case 7:
            incluirUmNovoRendimento();
            System.out.println("\nFim da operação de incluir um novo rendimento!");
            break;
          case 0:
            System.exit(0);
            break;
        }
      } catch (Exception e) {
        e.getMessage();
      }
      controle = 0;
    }
  }

  public void listarTodosOsCursosDados() {
    controller.listarTodosOsCursosDados();
  }

  public void listarTodosOsAlunosInscritos() {
    controller.listarTodosOsAlunosInscritos();
  }

  public void listarOHistoricoDeUmDeterminadoAluno() {
    System.out.println("Digite o ID do aluno: ");
    String idDoAluno = scanner.next();
    controller.listarOHistoricoDeUmAluno(idDoAluno);
  }

  public void listarORelatorioDeRendimentoDeUmCurso() {
    System.out
        .println("Copie o nome do curso desejado (incluindo o .csv), cole e pressione enter: ");
    controller.listarOsNomesDoCursoParaCopia();
    System.out.println();
    controller.listarORelatorioDeRendimentoDeUmCurso(scanner.next());
  }

  public void incluirUmNovoAluno() {
    System.out.println("Incluir um novo aluno.");
    System.out.println("Digite o ID: ");
    String id = scanner.next();
    System.out.println("Digite o nome: ");
    String nome = scanner.next();
    controller.incluirUmNovoAluno(id, nome);
  }

  public void incluirUmNovoCurso() {
    System.out.println("Incluir um novo curso!");
    System.out.println("Informe o nome: ");
    String nome = scanner.next();
    System.out.println(
        "Informe o tipo (GRADUACAO ou POS_GRADUACAO) (obs, só pode ser esses dois tipos, incluindo o _ ): ");
    String tipo = scanner.next().toUpperCase(Locale.ROOT);
    System.out.println("Ano: ");
    int ano = scanner.nextInt();
    controller.incluirUmNovoCurso(nome, tipo, ano);
  }

  public void incluirUmNovoRendimento() {
    System.out.println("Incluir um novo rendimento!");
    System.out.println("Informe o ID do aluno: ");
    String id = scanner.next();
    controller.listarOsNomesDoCursoParaCopia();
    System.out.println(
        "\nInforme o nome do curso (copie um dos acima, incluindo o .csv, cole e pressione enter): ");
    String curso = scanner.next();
    System.out.println("Digite a NP1: ");
    Nota np1 = new Nota(Double.parseDouble(scanner.next()));
    System.out.println("Digite a NP2: ");
    Nota np2 = new Nota(Double.parseDouble(scanner.next()));
    System.out.println("Digite a SUB: ");
    Nota sub = new Nota(Double.parseDouble(scanner.next()));
    System.out.println("Digite a EXAME: ");
    Nota exame = new Nota(Double.parseDouble(scanner.next()));
    Rendimento rendimento;
    if (curso.contains("POS_GRADUACAO")) {
      rendimento = new RendimentoPosGraduacao(id, new Notas(np1, np2, sub, exame));
    } else {
      rendimento = new RendimentoGraduacao(id, new Notas(np1, np2, sub, exame));
    }
    controller.incluirUmNovoRendimento(id, curso, rendimento);
  }

}
