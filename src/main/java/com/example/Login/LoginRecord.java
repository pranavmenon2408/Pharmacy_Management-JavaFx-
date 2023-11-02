package com.example.Login;

public class LoginRecord {
    private String username;
    private String password;
    public LoginRecord(){

    }
    public LoginRecord(String username,String password){
        this.username=username;
        this.password=password;
    }
    public String getUsername(){
        return username;
    }
    public void setUsername(String name){
        username=name;
    }
    public String getPassword(){
        return password;
    }
    public void setPassword(String pwd){
        password=pwd;
    }
    @Override
    public String toString(){
        return username +" "+password;
    }
}
