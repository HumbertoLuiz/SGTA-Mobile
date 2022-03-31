package br.edu.ifpr.sgtamobile.model;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {

    private Integer id;
    private String email;
    private String password;
    private Role roles;
    private Token token;

    public User() {
    }

    public User(Integer id, String email, String password, Role roles, Token token) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.token = token;
    }

    public User(String email, String password) {
        super();
        this.email = email;
        this.password = password;
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
}
