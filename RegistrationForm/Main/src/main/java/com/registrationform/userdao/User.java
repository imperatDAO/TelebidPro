package com.registrationform.userdao;

public class User {
    private String email;
    private String firstName;
    private String middleName;
    private String lastName;
    private String userName;
    private boolean verified;

    public User(String email,String firstName,String middleName,String lastName,String userName,boolean verified){
        this.email = email;
        this.firstName=firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.userName = userName;
        this.verified = verified;
    }

    public String getEmail() {
        return email;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getMiddleName() {
        return middleName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getUserName() {
        return userName;
    }
    public boolean getVerified() {
        return verified;
    }
}
