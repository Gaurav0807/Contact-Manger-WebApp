package com.sagar.smartcontactmanager.entities;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="USER")
public class User  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotBlank(message = "Username cannot be blank !")
    @Size(min = 3, max = 20, message = "minimum 3 and maximum 20 are allowed !")
    private String name;
    
    @NotBlank(message = "Email is mandatory")
    @Email(message="Invalid E-mail id") 
    @Column(unique = true)
    private String email;

    @NotBlank(message = "Password cannot be blank !")
    @Size( message = "minimum 3 and maximum 20 is required !")
    private String password;

    private String role;
    private boolean enabled;
    private String imageUrl;
    @Column(length = 500)
    private String about;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true) //cascade, auto save, delete etc. i.e If user is save, then contact will auto saved.
    private List<Contact> contacts = new ArrayList<>();

    //generate getter and setter for ArrayList
    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    //making default constructor.
    public User() {
        super();
    }

    //parameterized constructors.
    public User(int id, String name, String email, String password, String role, boolean enabled, String imageUrl, String about, List<Contact> contacts) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.enabled = enabled;
        this.imageUrl = imageUrl;
        this.about = about;
        this.contacts = contacts;
    }


    //getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    //generate toString() method.
    @Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", role=" + role
				+ ", enabled=" + enabled + ", imageUrl=" + imageUrl + ", about=" + about + ", contacts=" + contacts
				+ "]";
	}
    
    
}
