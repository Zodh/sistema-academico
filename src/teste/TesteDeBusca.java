package teste;

import br.com.unip.sistema_academico.modelo.Aluno;
import br.com.unip.sistema_academico.modelo.Nota;
import br.com.unip.sistema_academico.modelo.Notas;
import br.com.unip.sistema_academico.modelo.Rendimento.Rendimento;
import br.com.unip.sistema_academico.modelo.Rendimento.RendimentoGraduacao;
import br.com.unip.sistema_academico.servico.processamento.buscador.BuscadorDeDadosDeUmAluno;
import br.com.unip.sistema_academico.servico.processamento.buscador.BuscadorDeAlunos;
import br.com.unip.sistema_academico.servico.processamento.buscador.BuscadorDeCursos;

import br.com.unip.sistema_academico.servico.processamento.criador.CriadorDeAluno;
import br.com.unip.sistema_academico.servico.processamento.criador.CriadorDeCurso;
import br.com.unip.sistema_academico.servico.processamento.criador.CriadorDeRendimento;
import org.junit.After;
import org.junit.Test;

import java.util.List;

// TODO: Adicionar assertividade sobre os testes.

public class TesteDeBusca {

    @After
    public void pulaLinha(){
        System.out.println();
    }

    @Test
    public void procurarAlunoPorId(){
        BuscadorDeDadosDeUmAluno buscadorDeDadosDeUmAluno = new BuscadorDeDadosDeUmAluno();
        System.out.println(buscadorDeDadosDeUmAluno.procurarAlunoPorId("11s"));
    }

    @Test
    public void buscarRendimentosDeUmCurso(){
        BuscadorDeCursos buscadorDeCursos = new BuscadorDeCursos();
        List<Rendimento> listaDeInfosDeUmCurso = buscadorDeCursos.listarRendimentosDeUmCurso("ALPOO_POS_GRADUACAO_2019.csv");
        System.out.println("Relatório rendimento do curso: ALPOO_POS_GRADUACAO_2019.csv");
        listaDeInfosDeUmCurso.forEach(System.out::println);

        System.out.println();
        List<Rendimento> listaDeInfosDeOutroCurso = buscadorDeCursos.listarRendimentosDeUmCurso("ALPOO_GRADUACAO_2019.csv");
        System.out.println("Relatório rendimento do curso: ALPOO_GRADUACAO_2019.csv");
        listaDeInfosDeOutroCurso.forEach(System.out::println);

    }

    @Test
    public void listarTodosOsDadosDeRendimentoDeUmAluno(){
        BuscadorDeDadosDeUmAluno buscadorDeDadosDeUmAluno = new BuscadorDeDadosDeUmAluno();
        System.out.println("Todos os rendimentos de um aluno!");
        buscadorDeDadosDeUmAluno.listaComTodosOsRendimentosDeUmAluno("123");
    }

    @Test
    public void incluirNovoAluno() throws Exception {
        CriadorDeAluno criadorDeAluno = new CriadorDeAluno("11s", "Felipe Carvalho");
        criadorDeAluno.executarTarefa();
        System.out.println();
    }

    @Test
    public void listarValoresDoArquivo() throws Exception {
        BuscadorDeAlunos buscadorDeAlunos = new BuscadorDeAlunos();
        buscadorDeAlunos.executarTarefa();
    }

    @Test
    public void criarNovoCurso() throws Exception {
        CriadorDeCurso criadorDeCurso = new CriadorDeCurso("LPOO", "POS_GRADUACAO", 2021);
        criadorDeCurso.executarTarefa();
    }

    @Test
    public void listarCursos(){
        BuscadorDeCursos buscadorDeCursos = new BuscadorDeCursos();
        buscadorDeCursos.listarCursos().forEach(curso -> System.out.println(curso.getName()));
    }

    @Test
    public void criarNovoRendimento() throws Exception {
        Notas notas = new Notas(new Nota(5.0), new Nota(10.0), new Nota(5.2), new Nota(0));
        Rendimento rendimento = new RendimentoGraduacao("12f", notas);
        CriadorDeRendimento criadorDeRendimento = new CriadorDeRendimento(rendimento.getAluno().getId(), "LPOO_GRADUACAO_2019.csv", rendimento);
        criadorDeRendimento.executarTarefa();
    }


    // Listar todos os alunos inscritos: para cada aluno deve ser impresso seu nome e id, somente;
    @Test
    public void listarOHistoricoDeUmAluno(){
        BuscadorDeDadosDeUmAluno buscadorDeDadosDeUmAluno = new BuscadorDeDadosDeUmAluno();
        Aluno aluno = buscadorDeDadosDeUmAluno.procurarAlunoPorId("123");
        buscadorDeDadosDeUmAluno.listaComTodosOsRendimentosDeUmAluno(aluno.getId());
    }



}