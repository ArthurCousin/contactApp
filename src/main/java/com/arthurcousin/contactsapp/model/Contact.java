package com.arthurcousin.contactsapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@Table(name = "contacts")
@EntityListeners(AuditingEntityListener.class)
public class Contact implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty (hidden = true)
    private Long id;

    private String Firstname;

    private String Lastname;

    private String Fullname;

    private String Address;

    private String Email;

    private String MobilePhoneNumber;

    private String Owner;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "contact_skill",
            joinColumns = @JoinColumn(name = "contact_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id"))
    private List<Skill> skills;

    public String getFirstname() {
        return Firstname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstname(String firstname) {
        Firstname = firstname;
    }

    public String getLastname() {
        return Lastname;
    }

    public void setLastname(String lastname) {
        Lastname = lastname;
    }

    public String getFullname() {
        return Fullname;
    }

    public void setFullname(String fullname) {
        Fullname = fullname;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getMobilePhoneNumber() {
        return MobilePhoneNumber;
    }

    public void setMobilePhoneNumber(String mobilePhoneNumber) {
        MobilePhoneNumber = mobilePhoneNumber;
    }

    public String getOwner() {
        return Owner;
    }

    public void setOwner(String owner) {
        Owner = owner;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public void addSkill(Skill skill) {
        this.skills.add(skill);
    }

    public void removeSkill(Skill skill){
        this.skills.remove(skill);
    }
}