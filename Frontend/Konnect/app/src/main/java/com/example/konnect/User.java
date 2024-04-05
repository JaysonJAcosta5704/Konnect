package com.example.konnect;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * This class represents a User object with various properties and methods.
 *
 * @author Jayson Acosta
 */
public class User {

    /**
     * The name of the user.
     */
    private String name;

    /**
     * The email of the user.
     */
    private String email;

    /**
     * The username of the user.
     */
    private String username;

    /**
     * The gender of the user.
     */
    private String gender;

    /**
     * The bio of the user.
     */
    private String bio;

    /**
     * The password of the user.
     */
    private String password;

    /**
     * The ID of the user.
     */
    private String ID;

    /**
     * The birthday of the user.
     */
    private String birthday;

    /**
     * The age of the user.
     */
    private String age;

    /**
     * The date the user joined the app.
     */
    private String joinDate;

    /**
     * The singleton instance of the User class.
     */
    private static User instance;

    /**
     * The URL of the server.
     */
    private final String SERVER_URL = "http://coms-309-001.class.las.iastate.edu:8080/";
//    private final String SERVER_URL = "https://df952b3b-a205-4a2f-a0e0-a0f471c5f2bb.mock.pstmn.io/";

    /**
     * The URL for user login.
     */
    private String USERLOGIN_URL;

    /**
     * The URL for user information.
     */
    private String USERINFO_URL;

    /**
     * Default constructor for the User class.
     */
    private User(){}

    /* Setter Methods */

    /**
     * Sets the username of the user.
     *
     * @param username The username to be set.
     */
    protected void setUsername(String username) { this.username = username; }

    /**
     * Sets the email of the user.
     *
     * @param email The email to be set.
     */
    protected void setEmail(String email) { this.email = email; }

    /**
     * Sets the ID of the user.
     *
     * @param ID The ID to be set.
     */
    protected void setID(String ID) { this.ID = ID;}

    /**
     * Sets the password of the user.
     *
     * @param password The password to be set.
     */
    protected void setPassword(String password) { this.password = password; }

    /**
     * Sets the name of the user.
     *
     * @param name The name to be set.
     */
    protected void setName(String name) { this.name = name; }

    /**
     * Sets the age of the user.
     *
     * @param age The age to be set.
     */
    protected void setAge(String age) { this.age = age;}

    /**
     * Sets the Join date of the user.
     *
     * @param joinDate The join date to be set.
     */
    protected void setJoinDate(String joinDate){ this.joinDate = joinDate;}

    /**
     * Sets the gender of the user.
     *
     * @param gender The gender to be set.
     */
    protected void setGender(String gender) { this.gender = gender; }

    /**
     * Sets the bio of the user.
     *
     * @param bio The bio to be set.
     */
    protected void setBio(String bio) { this.bio = bio; }

    /**
     * Sets the birthday of the user.
     *
     * @param birthday The birthday to be set.
     */
    protected void setBirthday(String birthday) { this.birthday = birthday; }

    /* Getter Methods */
    /**
     * Gets the username of the user.
     *
     * @return The username of the user.
     */
    protected String getUsername() { return username; }

    /**
     * Gets the email of the user.
     *
     * @return The email of the user.
     */
    protected String getEmail() { return email; }

    /**
     * Gets the ID of the user.
     *
     * @return The ID of the user.
     */
    protected String getID() { return ID; }

    /**
     * Gets the name of the user.
     *
     * @return The name of the user.
     */
    protected String getName() { return name; }

    /**
     * Gets the age of the user.
     *
     * @return The age of the user.
     */
    protected String getAge() { return age; }

    /**
     * Gets the join date of the user.
     *
     * @return The join date of the user.
     */
    protected String getJoinDate() { return joinDate; }

    /**
     * Gets the gender of the user.
     *
     * @return The gender of the user.
     */
    protected String getGender() { return gender; }

    /**
     * Gets the bio of the user.
     *
     * @return The bio of the user.
     */
    protected String getBio() { return bio; }

    /**
     * Gets the birthday of the user.
     *
     * @return The birthday of the user.
     */
    protected String getBirthday() { return birthday; }

    /**
     * Gets the singleton instance of the User class.
     *
     * @return The instance of the User class.
     */
    protected static synchronized User getInstance() {
        if (instance == null){ instance = new User(); }
        return instance;
    }

    /* URLS METHODS */
    /**
     * Sets the URL for user login with the provided username and password.
     */
    protected void setURL_UP(){ USERLOGIN_URL = SERVER_URL + "login/u/" + username + "/" + password; }

    /**
     * Sets the URL for user login with the provided email and password.
     */
    protected void setURL_EP(){ USERLOGIN_URL = SERVER_URL + "login/e/" + email + "/" + password; }

    /**
     * Gets the URL for user login.
     *
     * @return The URL for user login.
     */
    protected String getURL_USERLOGIN(){ return USERLOGIN_URL; }

    /**
     * Sets the URL for user information with the provided ID.
     */
    protected void setURL_USERINFO(){ USERINFO_URL = SERVER_URL + "user/" + ID; }

    /**
     * Gets the URL for user information.
     *
     * @return The URL for user information.
     */
    protected String getURL_USERINFO(){ return USERINFO_URL; }

    /* Other Methods */
    /**
     * Displays a toast message based on the provided error code and logs the error message.
     *
     * @param context   The context in which the toast message will be displayed.
     * @param errorCode The error code indicating the type of error. 0 is a login error and 1 is a server error.
     * @param error     The error message to be logged.
     */

    protected static void toastError(Context context, int errorCode, String error){
        int duration = Toast.LENGTH_LONG;
        String text;
        switch (errorCode){
            case 0:
                text = "There was an error while attempting to login";
                break;
            case 1:
                text = "There is an error with the server";
                break;
            default:
                text = "Something went wrong";
        }
        Toast.makeText(context, text, duration).show();
        Log.e("Error", error);
    }
   }
