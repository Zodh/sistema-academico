package br.com.unip.sistema_academico.servico.recebe_entrada.comando;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class LeituraDeArquivos {

  public void mostrarArquivosDoDiretorio(String caminhoDoDiretorio) {

    List<File> arquivos;
    File diretorio = new File(caminhoDoDiretorio);
    arquivos = Arrays.asList(Objects.requireNonNull(diretorio.listFiles()));

    for (File arquivo : arquivos) {
      System.out.println(arquivo.toString());
    }
  }

  public List<File> listaDeArquivosDoDiretorio(String caminhoDoDiretorio) {

    List<File> arquivos;
    File diretorio = new File(caminhoDoDiretorio);
    arquivos = Arrays.asList(Objects.requireNonNull(diretorio.listFiles()));

    return arquivos;
  }

}