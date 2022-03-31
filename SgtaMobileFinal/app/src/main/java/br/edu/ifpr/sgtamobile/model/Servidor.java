package br.edu.ifpr.sgtamobile.model;

import android.widget.Spinner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Servidor extends Pessoa{


    private String matricula;

    private Cargo cargo;

    public Servidor() {
    }

    public Servidor(String nome, String cpf, String telefone, String matricula, Cargo cargo, Usuario usuario) {
        super(nome, cpf, telefone, usuario);
        this.matricula = matricula;
        this.cargo = cargo;
    }

    public Servidor(String nome, String cpf, String telefone, String matricula) {
        super(nome, cpf, telefone);
        this.matricula = matricula;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }


    public String getDados() {
        return "Servidor{" +
                "matricula='" + matricula + '\'' +
                ", cargo=" + cargo +
                '}';
    }


}
