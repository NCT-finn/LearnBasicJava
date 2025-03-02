package vn.edu.t3h.employeemananger.model;

import java.io.Serializable;

public class RoleModel implements Serializable {

    private Integer id;
    private String name;
    private String code;

    public RoleModel() {
    }

    public RoleModel(Integer id, String name, String code) {
        this.id = id;
        this.name = name;
        this.code = code;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
