package com.sagar.smartcontactmanager.dao;

import java.util.List;
/*
 * Gaurav Rawat
 */


import com.sagar.smartcontactmanager.entities.Contact;
import com.sagar.smartcontactmanager.entities.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ContactRepository extends JpaRepository<Contact, Integer> {
    
    // //pagination
    // @Query("from Contact as c where c.user.id =:userId")
    // public List<Contact> findContactsByUser(@Param("userId") int userId);

    //replacing List with Page, Pageable will have current page & records per page.
    //pagination
    @Query("from Contact as c where c.user.id =:userId")
    public Page<Contact> findContactsByUser(@Param("userId") int userId, Pageable pageable); 

    //create search bar method by name
    public List<Contact> findByNameContainingAndUser(String name, User user);
}
