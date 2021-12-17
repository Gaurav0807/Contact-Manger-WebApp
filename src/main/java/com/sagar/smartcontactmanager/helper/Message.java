package com.sagar.smartcontactmanager.helper;

public class Message {
    
    private String content;
    private String type;

    //adding parameterized constructor.
    public Message(String content, String type) {
        this.content = content;
        this.type = type;
    }

    //getters and setters
    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }


}
