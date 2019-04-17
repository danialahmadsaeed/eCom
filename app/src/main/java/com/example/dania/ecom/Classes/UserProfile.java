package com.example.dania.ecom.Classes;

public class UserProfile {

    public String userType;
    public String userName;
    public String userSureName;
    public String userEmail;
    public String userPhone;
    public String id;

    public UserProfile(){

    }
    public UserProfile(String userType, String userName, String userSureName, String userEmail, String userPhone, String id) {
       this.userType = userType;
        this.userName = userName;
        this.userSureName = userSureName;
        this.userEmail = userEmail;
        this.userPhone = userPhone;
        this.id = id;
    }
    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSureName() {
        return userSureName;
    }

    public void setUserSureName(String userSureName) {
        this.userSureName = userSureName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getId() {return id;}

    public void setId(String id) {this.id = id;}

}
