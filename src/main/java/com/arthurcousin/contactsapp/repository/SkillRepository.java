package com.arthurcousin.contactsapp.repository;

import com.arthurcousin.contactsapp.model.Contact;
import com.arthurcousin.contactsapp.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {
}
