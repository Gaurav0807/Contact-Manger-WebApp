package com.sagar.smartcontactmanager.entities;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "CONTACT")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int cId;
    private String name;
    private String nickName;
    private String work;
    @Column(unique = true)
    private String email;
    private String phone;
    private String image; //image name will be save here.
    @Column(length = 5000)
    private String description;

    //now, 'many contact belongs to one user', as this is bidirectional mapping, here it will be 'many-to-one' i.e reverse.
    @ManyToOne
    @JsonIgnore //not to serialize the user.
    private User user; //now one more column is created, treated as foreign key.

    //generate getter and setter for User.
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    //getters and setters
    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    

    
    
}
