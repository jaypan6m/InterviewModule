package com.quest.admin.dto;


public class AppUserDto {
	 
    private String id;
    private String userName;
 
    private String password;
 
    public String getUserName() {
        return userName;
    }
 
    public void setUserName(String username) {
        this.userName = username;
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
 
}
