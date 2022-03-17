package br.edu.ifpr.sgtamobile.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class Aluno extends Pessoa implements Serializable {

    @SerializedName("matricula")
    private String matricula;

    @SerializedName("curso")
    private Curso curso;

    @SerializedName("responsavel")
    private Responsavel responsavel;

    public Aluno(String nome, String cpf, String telefone, String matricula, Responsavel responsavel, Usuario usuario) {
        super( nome, cpf, telefone, usuario);
        this.matricula = matricula;
        this.responsavel = responsavel;
    }


    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Responsavel getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Responsavel responsavel) {
        this.responsavel = responsavel;
    }

  /*  public String toJson() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException ex) {
            return null;
        }
    }*/


}
