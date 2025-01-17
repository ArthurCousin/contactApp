package com.arthurcousin.contactsapp.controller;
import com.arthurcousin.contactsapp.model.Contact;
import com.arthurcousin.contactsapp.model.Skill;
import com.arthurcousin.contactsapp.repository.ContactRepository;
import com.arthurcousin.contactsapp.exception.ResourceNotFoundException;
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
@Api(value="Contacts", description="Operations to manage the contacts", authorizations = { @Authorization(value="Bearer") })
public class ContactController {

    @Autowired
    ContactRepository contactRepository;

    @Autowired
    SkillRepository skillRepository;

    @Autowired
    SecurityService securityService;

    @GetMapping("/contacts")
    @ApiOperation(value = "View a list of available contacts", response = Iterable.class, authorizations = { @Authorization(value="Bearer") })
    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    @PostMapping("/contacts")
    @ApiOperation(value = "Create a contact", authorizations = { @Authorization(value="Bearer") })
    public Contact createContact(@RequestBody Contact contact) {
        return contactRepository.save(contact);
    }

    @GetMapping("/contacts/{id}")
    @ApiOperation(value = "Search a contact with an ID", response = Contact.class, authorizations = { @Authorization(value="Bearer") })
    public Contact getContactById(@PathVariable(value = "id") Long contactId) {
        return contactRepository.findById(contactId)
                .orElseThrow(() -> new ResourceNotFoundException("Contact", "id", contactId));
    }

    @PutMapping("/contacts/{id}")
    @ApiOperation(value = "Update a contact", authorizations = { @Authorization(value="Bearer") })
    public Contact updateContact(@PathVariable(value = "id") Long contactId,
                           @RequestBody Contact updatedContact) {
        Contact contact = contactRepository.findById(contactId)
                .orElseThrow(() -> new ResourceNotFoundException("Contact", "id", contactId));

        securityService.checkObjectOwnership(contact.getOwner());
        contact.setFirstname(updatedContact.getFirstname());
        contact.setLastname(updatedContact.getLastname());
        contact.setFullname(updatedContact.getFullname());
        contact.setAddress(updatedContact.getAddress());
        contact.setEmail(updatedContact.getEmail());
        contact.setMobilePhoneNumber(updatedContact.getMobilePhoneNumber());

        return contactRepository.save(contact);
    }

    @DeleteMapping("/contacts/{id}")
    @ApiOperation(value = "Delete a contact", authorizations = { @Authorization(value="Bearer") })
    public ResponseEntity<?> deleteContact(@PathVariable(value = "id") Long contactId) {
        Contact contact = contactRepository.findById(contactId)
                .orElseThrow(() -> new ResourceNotFoundException("Contact", "id", contactId));

        securityService.checkObjectOwnership(contact.getOwner());
        contactRepository.delete(contact);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/contacts/{id}/skills")
    @ApiOperation(value = "Add a owned skill for this contact", response = Contact.class, authorizations = { @Authorization(value="Bearer") })
    public Contact addOwnedSkill(@PathVariable(value = "id") Long contactId,@RequestParam Long skillId) {
        Contact contact = contactRepository.findById(contactId)
                .orElseThrow(() -> new ResourceNotFoundException("Contact", "id", skillId));

        Skill skill = skillRepository.findById(skillId)
                .orElseThrow(() -> new ResourceNotFoundException("Skill", "id", skillId));

        securityService.checkObjectOwnership(skill.getOwner());
        securityService.checkObjectOwnership(contact.getOwner());

        if(!contact.getSkills().contains(skill))
            contact.addSkill(skill);
        else
            throw new RuntimeException("Skill already owned");

        return contactRepository.save(contact);
    }

    @GetMapping("/contacts/{id}/skills")
    @ApiOperation(value = "Get the owned skills for this contact", authorizations = { @Authorization(value="Bearer") })
    public List<Skill> getOwnedSkills(@PathVariable(value = "id") Long contactId) {
        Contact contact = contactRepository.findById(contactId)
                .orElseThrow(() -> new ResourceNotFoundException("contact", "id", contactId));

        securityService.checkObjectOwnership(contact.getOwner());

        return contact.getSkills();
    }

    @DeleteMapping("/contacts/{id}/skills/{id}")
    @ApiOperation(value = "Delete a owned skill for this contact", authorizations = { @Authorization(value="Bearer") })
    public ResponseEntity<?> deleteOwnedSkill(@PathVariable(value = "id") Long contactId,@RequestParam Long skillId) {
        Contact contact = contactRepository.findById(contactId)
                .orElseThrow(() -> new ResourceNotFoundException("Contact", "id", skillId));

        Skill skill = skillRepository.findById(skillId)
                .orElseThrow(() -> new ResourceNotFoundException("Skill", "id", skillId));

        securityService.checkObjectOwnership(skill.getOwner());
        securityService.checkObjectOwnership(contact.getOwner());

        contact.removeSkill(skill);

        return ResponseEntity.ok().build();
    }
}