package com.sagar.smartcontactmanager.dao;

import com.sagar.smartcontactmanager.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Integer> {

    //building system, login with username and password.

    @Query("select u from User u where u.email = :email") //jpa query: select u from User, u where username.email = email(parameterized, dynamic email)
    public User getUserByUserName(@Param("email") String email); //dynamic value of email, param email and :email must match, String email is input by users 
}
