package br.edu.ifpr.sgtamobile.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class Disciplina {

    private int discId;

    private String descricao;

    private Professor professor;

    private  Disciplina  disciplina;

    public Disciplina() {
    }


    public Disciplina(int discId, String descricao) {
        this.discId = discId;
        this.descricao = descricao;
    }

    public Disciplina(int discId) {
        this.discId = discId;
    }

    public Disciplina(String descricao, Professor professor) {
        this.descricao = descricao;
        this.professor = professor;
    }

/*   public Disciplina(String dDescricao, Professor professor) {
    }*/

    public int getDiscId() {
        return discId;
    }

    public void setDiscId(int discId) {
        this.discId = discId;
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

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }
}
