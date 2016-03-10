package com.kiselev.ejb.model.entities;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class EntityBean implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "name")
    private String name;

    public EntityBean() {

    }

    public EntityBean(String username, String name) {
        this.username = username;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Entity [id=" + id + ", username=" + username + ", name=" + name + "]";
    }
}
