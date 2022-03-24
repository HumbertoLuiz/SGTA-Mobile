package br.edu.ifpr.sgtamobile.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Objects;

public abstract class Pessoa implements Serializable {

    @SerializedName("id")
    private Integer id;

    @SerializedName("nome")
    private String nome;

    @SerializedName("cpf")
    private String cpf;

    @SerializedName("telefone")
    private String telefone;

    @SerializedName("usuario")
    private  Usuario usuario;

  /*  public Pessoa(String json) {
        try {
            Pessoa pessoa = new ObjectMapper().readValue(json, Pessoa.class);
            this.id = pessoa.getId();
            this.nome = pessoa.getNome();
            this.cpf = pessoa.getCpf();
            this.telefone = pessoa.getTelefone();
            this.usuario = pessoa.getUsuario();
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }
    }*/

    public Pessoa(String nome, String cpf, String telefone,Usuario usuario) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.usuario = usuario;
    }

    public Pessoa(String nome, String cpf, String telefone) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
    }

    public Pessoa() {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public boolean equals(Object o){
        return this.id == ((Pessoa)o).id;
    }

    @Override
    public int hashCode(){
        return this.id;
    }
}
