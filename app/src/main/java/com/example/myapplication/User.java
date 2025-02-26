package com.example.myapplication;

public class User {
    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private Boolean isAdmin;

    public User(String firstName, String lastName, String login, String password, Boolean isAdmin){
        this.firstName = firstName;
        this.lastName = lastName;
        if(login.isBlank()){
            String temp = "";
            temp += lastName.length() < 3 ? lastName : lastName.substring(0, 3);
            temp += firstName.length() < 3 ? firstName : firstName.substring(0, 3);
            this.login = temp;
        }
        else {
            this.login = login;
        }
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public String GetFirstName(){
        return firstName;
    }
    public String GetLastName(){
        return lastName;
    }
    public String GetLogin(){
        return login;
    }
    public String GetPassword(){
        return password;
    }
    public Boolean GetIsAdmin(){
        return isAdmin;
    }
    public void SetFirstName(String firstName){
        this.firstName = firstName.trim();
    }
    public void SetLastName(String lastName){
        this.lastName = lastName.trim();
    }
    public void SetLogin(String login){
        this.login = login.trim();
    }
    public void SetPassword(String password){
        this.password = password;
    }
    public void SetIsAdmin(Boolean isAdmin){
        this.isAdmin = isAdmin;
    }
}