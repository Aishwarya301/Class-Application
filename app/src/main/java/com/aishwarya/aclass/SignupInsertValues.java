package com.aishwarya.aclass;

public class SignupInsertValues {
    String email,username,password,id,choice;

    public SignupInsertValues() {
    }

    public SignupInsertValues(String email, String username, String password, String id, String choice) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.id = id;
        this.choice = choice;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }
}
