package com.example.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "`role`")//use `role` instead of role as it seems role is a reserved word in mqsql and it gives you syntax error
public class Role {

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "role_id")
    private int roleId;

    @Column(name = "`role`")
    private String role;

    public Role() {
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
