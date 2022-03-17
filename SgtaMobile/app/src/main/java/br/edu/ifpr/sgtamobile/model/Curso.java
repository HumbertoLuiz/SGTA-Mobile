package br.edu.ifpr.sgtamobile.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;
import java.util.List;

public class Curso implements Serializable {

    private Integer id;

    private String descricao;

    private String ano;

    private Turno turno;

    private List<Disciplina> disciplina;


    public Curso() {
    }

    public Curso(String descricao, String ano,Turno turno, List<Disciplina> disciplina) {
        this.descricao = descricao;
        this.ano = ano;
        this.turno = turno;

        this.disciplina = disciplina;
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

    public Turno getTurno() {
        return turno;
    }

    public void setTurno(Turno turno) {
        this.turno = turno;
    }

    public List<Disciplina> getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(List<Disciplina> disciplina) {
        this.disciplina = disciplina;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }
}
