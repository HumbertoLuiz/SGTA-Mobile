package br.edu.ifpr.sgtamobile.model;

public enum Role  {

    ROLE_ADMIN("Admin"),
    ROLE_USER("User"),
    ROLE_GUEST("Guest");

   private String nome;

    @Override
    public String toString() {
        return nome;
    }

    private Role(String nome){
       this.nome =nome;


   }






}

