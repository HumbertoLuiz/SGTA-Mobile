package br.edu.ifpr.sgtamobile.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Usuario implements Serializable {

    @SerializedName("id")
    private Integer id;

    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String password;

  //  @SerializedName("roles")
    private List<String> roles;

 /*   public Usuario(String json) {
        try {
            Usuario usuario = new ObjectMapper().readValue(json, Usuario.class);
            this.id = usuario.getId();
            this.email = usuario.getEmail();
            this.password = usuario.getPassword();
            this.roles = usuario.getRoles();
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }
    }*/

    public Usuario() {
    }

    public Usuario(String email, String password) {
        this.email = email;
        this.password = password;
    }
/* public Usuario(String email, String password, Role roles) {
        this.email = email;
        this.password = password;
        this.roles = roles;
    }*/

    public Usuario(String email, String password, List<String> roles) {
        this.email =email;
        this.password = password;
        this.roles = roles;
    }

    public Usuario(String email, String password, Role roles) {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

 /*   public Role getRoles() {
        return roles;
    }

    public void setRoles(Role roles) {
        this.roles = roles;
    }*/

    public List<String> getRoles() {
        return roles;
    }

    public String toJson() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException ex) {
            return null;
        }
    }
}
