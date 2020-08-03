package com.arthurcousin.contactsapp.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "contacts")
@EntityListeners(AuditingEntityListener.class)
public class Skill implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String level;

    private String Owner;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getOwner() {
        return Owner;
    }
    public void setOwner(String owner) {
        Owner = owner;
    }
}