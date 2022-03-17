package br.edu.ifpr.sgtamobile.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Tarefa {

    private Integer id;

    private String descricao;

    private String local;

    private String dtCriacaoTarefa;

    private String dtFinalizacaoTarefa;


    public Tarefa(String json) {
        try {
            Tarefa tarefa = new ObjectMapper().readValue(json, Tarefa.class);
            this.id = tarefa.getId();
            this.descricao = tarefa.getDescricao();
            this.local = tarefa.getLocal();
            this.dtCriacaoTarefa = tarefa.getDtCriacaoTarefa();
            this.dtFinalizacaoTarefa = getDtFinalizacaoTarefa();
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }
    }

    public Tarefa() {
    }

    public Tarefa(String descricao, String local, String dtCriacaoTarefa, String dtFinalizacaoTarefa) {
        this.descricao = descricao;
        this.local = local;
        this.dtCriacaoTarefa = dtCriacaoTarefa;
        this.dtFinalizacaoTarefa = dtFinalizacaoTarefa;
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

    public String toJson() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException ex) {
            return null;
        }
    }

}
