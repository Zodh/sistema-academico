package br.com.unip.sistema_academico.modelo;

public class Nota {

    private double nota;

    public Nota(double aNota){
        if (aNota < 0.0 || aNota > 10.0){
            throw new IllegalArgumentException("A nota deve estar entre 0 a 10!");
        }
        this.nota = aNota;
    }

    public double getNota() {
        return this.nota;
    }

    @Override
    public String toString() {
        return " " + this.nota;
    }

}