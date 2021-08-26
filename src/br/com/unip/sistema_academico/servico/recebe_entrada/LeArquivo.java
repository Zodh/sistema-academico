package br.com.unip.sistema_academico.servico.recebe_entrada;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LeArquivo {

  private InputStream fis;
  private Reader isr;
  private BufferedReader br;

  public List<String> pegarListaDeRegistrosDeUmArquivo(String arquivo) throws IOException {
    List<String> arquivoCompleto;
    try {
      this.fis = new FileInputStream(arquivo);
      this.isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
      this.br = new BufferedReader(isr);
      arquivoCompleto = br.lines().collect(Collectors.toList());
      return arquivoCompleto;
    } catch (IOException e) {
      System.out.println("Erro de leitura de arquivo. " + e.getMessage());
      e.printStackTrace();
    } finally {
      br.close();
    }
    return Collections.emptyList();
  }

}