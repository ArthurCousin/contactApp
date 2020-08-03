package com.arthurcousin.contactsapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "contacts")
@EntityListeners(AuditingEntityListener.class)
public class Contact implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String Firstname;

    private String Lastname;

    private String Fullname;

    private String Address;

    private String Email;

    private String MobilePhoneNumber;

    private String Owner;

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
}