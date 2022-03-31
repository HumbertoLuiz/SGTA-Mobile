package br.edu.ifpr.sgtamobile.model;

import java.util.List;

public enum Role  {

    ROLE_ADMIN("ROLE_ADMIN" ),
    ROLE_USER("ROLE_USER"),
    ROLE_GUEST("ROLE_GUEST");

   private String nome;


    @Override
    public String toString() {
        return nome;
    }

    public String getNome() {
        return nome;
    }

    Role(String nome) {
        this.nome = nome;
    }
}








