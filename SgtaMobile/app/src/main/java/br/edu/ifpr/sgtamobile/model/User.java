package br.edu.ifpr.sgtamobile.model;

import java.util.List;

public class User {

    private Integer id;
    private String email;
    private String password;
    private Role roles;

    public User() {
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
}
