package vn.edu.t3h.employeemananger.model;

import java.io.Serializable;

public class UserModel implements Serializable {

    private Integer id;

    private String username;
    private String password;
    private String full_name;
    private Boolean deleted;
    private Integer role_id;

    public UserModel() {
    }

    public UserModel(Integer id, String username, String password, String full_name, Boolean deleted, Integer role_id) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.full_name = full_name;
        this.deleted = deleted;
        this.role_id = role_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Integer getRole_id() {
        return role_id;
    }

    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
    }
}
