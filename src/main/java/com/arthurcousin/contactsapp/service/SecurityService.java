package com.arthurcousin.contactsapp.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {
     public void checkObjectOwnership(String objectOwner){
         String userName = SecurityContextHolder.getContext().getAuthentication().getName();
         if(!userName.equals(objectOwner))
             throw new SecurityException();
     }
}
