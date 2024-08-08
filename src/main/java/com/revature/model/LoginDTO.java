package com.revature.model;

public class LoginDTO {

    private String username;
    private String password;

    public LoginDTO () {

    }
    public LoginDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getPassword() { return this.password ;}
    public String getUsername() { return this.username ;}
}
