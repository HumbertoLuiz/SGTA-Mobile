package br.edu.ifpr.sgtamobile.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Disciplina {

    private Integer id;

    private String descricao;

    private Professor professor;


    public Disciplina() {
    }

    public Disciplina(String descricao, Professor professor) {
        this.descricao = descricao;
        this.professor = professor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }


}
