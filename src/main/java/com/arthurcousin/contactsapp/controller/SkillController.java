package com.arthurcousin.contactsapp.controller;

import com.arthurcousin.contactsapp.exception.ResourceNotFoundException;
import com.arthurcousin.contactsapp.model.Skill;
import com.arthurcousin.contactsapp.repository.SkillRepository;
import com.arthurcousin.contactsapp.service.SecurityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Api(value="Skills", description="Operations to manage the skills")
public class SkillController {

    @Autowired
    SkillRepository skillRepository;

    @Autowired
    SecurityService securityService;

    @GetMapping("/skills")
    @ApiOperation(value = "View a list of available skills", response = Iterable.class, authorizations = { @Authorization(value="Bearer") })
    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }

    @PostMapping("/skills")
    @ApiOperation(value = "Create a Skill")
    public Skill createSkill(@RequestBody Skill skill) {
        return skillRepository.save(skill);
    }

    @GetMapping("/skills/{id}")
    @ApiOperation(value = "Search a skill with an ID", response = Skill.class, authorizations = { @Authorization(value="Bearer") })
    public Skill getSkillById(@PathVariable(value = "id") Long skillId) {
        return skillRepository.findById(skillId)
                .orElseThrow(() -> new ResourceNotFoundException("Skill", "id", skillId));
    }

    @PutMapping("/skills/{id}")
    @ApiOperation(value = "Update a skill", authorizations = { @Authorization(value="Bearer") })
    public Skill updateSkill(@PathVariable(value = "id") Long skillId,
                               @RequestBody Skill updatedSkill) {

        Skill skill = skillRepository.findById(skillId)
                .orElseThrow(() -> new ResourceNotFoundException("Skill", "id", skillId));
        securityService.checkObjectOwnership(skill.getOwner());

        skill.setName(updatedSkill.getName());
        skill.setLevel(updatedSkill.getLevel());

        return skillRepository.save(skill);
    }

    @DeleteMapping("/skills/{id}")
    @ApiOperation(value = "Delete a skill", authorizations = { @Authorization(value="Bearer") })
    public ResponseEntity<?> deleteSkill(@PathVariable(value = "id") Long skillId) {
        Skill skill = skillRepository.findById(skillId)
                .orElseThrow(() -> new ResourceNotFoundException("Skill", "id", skillId));

        securityService.checkObjectOwnership(skill.getOwner());
        skillRepository.delete(skill);

        return ResponseEntity.ok().build();
    }
}