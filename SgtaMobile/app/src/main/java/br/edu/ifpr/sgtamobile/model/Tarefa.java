package br.edu.ifpr.sgtamobile.model;

import java.util.List;

public class Tarefa {

    private Integer id;

    private String descricao;

    private String local;

    private String dtCriacaoTarefa;

    private String dtFinalizacaoTarefa;

    private Disciplina disciplina ;

    public Tarefa() {
    }

    public Tarefa(String descricao, String local, String dtCriacaoTarefa, String dtFinalizacaoTarefa, Disciplina disciplina) {
        this.descricao = descricao;
        this.local = local;
        this.dtCriacaoTarefa = dtCriacaoTarefa;
        this.dtFinalizacaoTarefa = dtFinalizacaoTarefa;
        this.disciplina = disciplina;
    }

    public Tarefa(Integer id, String descricao, String local, String dtCriacaoTarefa, String dtFinalizacaoTarefa, Disciplina disciplina) {
        this.id = id;
        this.descricao = descricao;
        this.local = local;
        this.dtCriacaoTarefa = dtCriacaoTarefa;
        this.dtFinalizacaoTarefa = dtFinalizacaoTarefa;
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

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getDtCriacaoTarefa() {
        return dtCriacaoTarefa;
    }

    public void setDtCriacaoTarefa(String dtCriacaoTarefa) {
        this.dtCriacaoTarefa = dtCriacaoTarefa;
    }

    public String getDtFinalizacaoTarefa() {
        return dtFinalizacaoTarefa;
    }

    public void setDtFinalizacaoTarefa(String dtFinalizacaoTarefa) {
        this.dtFinalizacaoTarefa = dtFinalizacaoTarefa;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }
}
