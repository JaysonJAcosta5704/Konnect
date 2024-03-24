package com.example.konnect;

import android.content.Context;
import android.widget.Toast;

import java.util.Date;

public class User {
    private String name, email, username, gender, bio, password;
    private int age, ID;
    private Date birthday;
    private static User instance;
    private final String SERVER_URL = "http://coms-309-001.class.las.iastate.edu:8080/";
    private String LOGIN_URL;
    /* Constructor */
    public User(){}

    /* Setter Methods */
    public void setUsername(String username) { this.username = username; }
    public void setEmail(String email) { this.email = email; }
    public void setID(int ID) { this.ID = ID;}
    public void setLOGIN_URL(){
        LOGIN_URL = SERVER_URL + "users/" + username + "/" + password;
    }
    public void setPassword(String password) { this.password = password; }

    public void setName(String name) { this.name = name; }
    public void setAge(int age) { this.age = age;}
    public void setGender(String gender) { this.gender = gender; }

    public void setBio(String bio) { this.bio = bio; }

    public void setBirthday(Date birthday) { this.birthday = birthday; }

    /* Getter Methods */
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public int getID() { return ID; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getGender() { return gender; }
    public String getBio() { return bio; }
    public Date getBirthday() { return birthday; }
    public static synchronized User getInstance() {
        if (instance == null){ instance = new User(); }
        return instance;
    }

    /*GET Requests*/
//    public void tryLogin(String inputPassword, Context context){}

    /* Other Methods */
    public void toastError(Context context, int errorCode){
        int duration = Toast.LENGTH_LONG;
        String text;
        switch (errorCode){
            case 0:
                text = "There was an error while attempting to login";
                break;
            case 1:
                text = "There is no user with your username.";
                break;
            case 2:
                text = "Your password is incorrect.";
                break;
            default:
                text = "Something went wrong";
        }
        Toast.makeText(context, text, duration).show();
    }


}
