package br.edu.ifpr.sgtamobile.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Usuario implements Serializable {

    @SerializedName("id")
    private Integer id;

    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String password;

  //  @SerializedName("roles")
    private List<String> roleList;

    @SerializedName("roles")
    private Role roles;

    @SerializedName("token")
    private Token token;


    public Usuario(String json) {
        try {
            Usuario usuario = new ObjectMapper().readValue(json, Usuario.class);
            this.id = usuario.getId();
            this.email = usuario.getEmail();
            this.password = usuario.getPassword();
            this.roles = usuario.getRoles();
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }
    }

    public Usuario() {
    }

    public Usuario(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Usuario(String email, String password, Role roles, Token token) {
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.token = token;
    }

    public Usuario(Integer id, String email, String password, Role roles, Token token) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.token = token;
    }

    public Usuario(String email, String password, List<String> roleList) {
        this.email = email;
        this.password = password;
        this.roleList = roleList;
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

    public List<String> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<String> roleList) {
        this.roleList = roleList;
    }

    public Role getRoles() {
        return roles;
    }

    public void setRoles(Role roles) {
        this.roles = roles;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }


    public String toJson() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException ex) {
            return null;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(email, usuario.email) && Objects.equals(password, usuario.password) && Objects.equals(roleList, usuario.roleList) && roles == usuario.roles && Objects.equals(token, usuario.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password, roleList, roles, token);
    }

    public void getRoles(String role_admin) {
    }

    public void setRoles(String valueOf) {
    }
}
