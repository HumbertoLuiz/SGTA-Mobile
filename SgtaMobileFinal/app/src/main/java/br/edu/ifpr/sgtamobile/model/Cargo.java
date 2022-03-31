package br.edu.ifpr.sgtamobile.model;

public enum Cargo {

    COORDENADOR("COORDENADOR"),
    PSICOLOGO("PSICOLOGO"),
    PROFESSOR("PROFESSOR");

    private String nome;


    @Override
    public String toString() {
        return nome;
    }

    private Cargo(String nome){
        this.nome =nome;


    }

}
