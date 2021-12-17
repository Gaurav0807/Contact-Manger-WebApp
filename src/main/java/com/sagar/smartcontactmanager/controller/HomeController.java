package com.sagar.smartcontactmanager.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.sagar.smartcontactmanager.dao.UserRepository;
import com.sagar.smartcontactmanager.entities.User;
import com.sagar.smartcontactmanager.helper.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    //to save data into db we need object of dao
    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Home - Smart Contact Manager");
        return "home";
    }

    @RequestMapping("/about")
    public String about(Model model) {
        model.addAttribute("title", "About - Smart Contact Manager");
        return "about";
    }

     @RequestMapping("/signup")
     public String signup(Model model) {
         model.addAttribute("title", "Register - Smart Contact Manager");

         model.addAttribute("user", new User()); //sending blank user object for every new registration.
         return "signup";
     }
    
     //handler for registering user
     //we are going to accept data with the @ModelAttribute
     @RequestMapping(value = "/do_register", method = RequestMethod.POST)
     //fields that match with form object and user object, that values will automatically come here and for checkout we have written separately.
     public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result1,
			@RequestParam(value = "agreement", defaultValue = "false") boolean agreement, Model model,
			HttpSession session) {

        try {
            if (!agreement) {
                System.out.println("You have not agree the terms and conditions");
                throw new Exception("You have not agree the terms and conditions");
            }
         
            //checking for errors.
            if (result1.hasErrors()) {
                System.out.println("ERROR " +result1.toString());
                model.addAttribute("user", user);
                return "signup";
            }
        
         user.setRole("ROLE_USER");
         user.setEnabled(true);
         user.setImageUrl("default.png");

         //when user comes for signup, encode it and store in user.
         user.setPassword(passwordEncoder.encode(user.getPassword()));
                  
         System.out.println("Agreement " + agreement);
         System.out.println("USER " +user);

         User result = this.userRepository.save(user); //this will help into db and also it will return its object.
         model.addAttribute("user", new User());

         //if everything goes OK, we pass notification as alert.
         String sessionId = session.getId();  
         System.out.println("[session-id]: " + sessionId);
         
         session.setAttribute("message", new Message("Registration Successful !", "alert-success"));
         return "signup";

        } catch (Exception e) {
             e.printStackTrace();
             //now showing error to user, we require session.
             model.addAttribute("user", user);
             session.setAttribute("message", new Message("Something went wrong!!!" + e.getMessage(), "alert-danger"));
             return "signup";
        }
 
     }

     //handler for custom login page
     @GetMapping("/signin")
     public String customLogin(Model model){
         model.addAttribute("title", "Login page");
         return "login";
     }
}
