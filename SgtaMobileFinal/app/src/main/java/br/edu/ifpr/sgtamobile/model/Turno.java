package br.edu.ifpr.sgtamobile.model;

import java.io.Serializable;

public enum Turno implements Serializable {

    MATUTINO("MATUTINO"),

    VESPERTINO("VESPERTINO"),

    NOTURNO("NOTURNO"),

    INTEGRAL("INTEGRAL");

    private String nome;
    private String valor;

    private Turno(String nome) {
        this.nome = nome;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }


    @Override
    public String toString() {
        return  nome ;
    }
}
