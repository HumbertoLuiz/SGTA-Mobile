package br.edu.ifpr.sgtamobile.model;

public enum Turno {

    MATUTINO("Matutino"),
    VESPERTINO("Vespertino"),
    NOTURNO("Noturno"), INTEGRAL("Integral");

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
    public String getValor() {
        return valor;
    }
    public void setValor(String valor) {
        this.valor = valor;
    }

}
