package br.edu.ifpr.sgtamobile.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Responsavel {

    private Integer id;

    private String nome;

    private String telefone;

    public Responsavel(String json) {
        try {
            Responsavel responsavel = new ObjectMapper().readValue(json, Responsavel.class);
            this.id = responsavel.getId();
            this.nome = responsavel.getNome();
            this.telefone = responsavel.getTelefone();
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }
    }

    public Responsavel() {
    }

    public Responsavel(String nome, String telefone) {
        this.nome = nome;
        this.telefone = telefone;
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String toJson() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException ex) {
            return null;
        }
    }


}
