package com.arthurcousin.contactsapp.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.arthurcousin.contactsapp.model.DAOUser;

@Repository
public interface UserDao extends CrudRepository<DAOUser, Integer> {
    DAOUser findByUsername(String username);
}
