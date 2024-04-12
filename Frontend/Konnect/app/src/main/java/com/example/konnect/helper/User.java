package com.example.konnect.helper;

import android.app.AlertDialog;
import android.content.Context;

/**
 * This class represents a User object with various properties and methods.
 *
 * @author Jayson Acosta
 */
public class User {

    /*---------------------------------------------- USER VARIABLES ----------------------------------------------*/
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
    private String username = "Jhi";

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

    /*---------------------------------------------- USER INSTANCE ----------------------------------------------*/
    /**
     * The singleton instance of the User class.
     */
    private static User instance;

    /*---------------------------------------------- SERVER URLS ----------------------------------------------*/
    /**
     * The URL of the server.
     */
    private static final String SERVER_URL = "http://coms-309-001.class.las.iastate.edu:8080/";
//    private static final String SERVER_URL = "https://df952b3b-a205-4a2f-a0e0-a0f471c5f2bb.mock.pstmn.io/";

    /**
     * The URL of the websocket
     */
    private static final String WEBSOCKET_URL = "ws://coms-309-001.class.las.iastate.edu:8080/";

    /**
     * The URL for user login.
     */
    private String USERLOGIN_URL;

    /**
     * The URL for user information.
     */
    private String USERINFO_URL;

    /**
     * The URL for friend requests.
     */
    private String FRIENDREQUEST_URL;

    /**
     * Default constructor for the User class.
     */
    private User(){}

    /*---------------------------------------------- SETTER METHODS ----------------------------------------------*/

    /**
     * Sets the username of the user.
     *
     * @param username The username to be set.
     */
    public void setUsername(String username) { this.username = username; }

    /**
     * Sets the email of the user.
     *
     * @param email The email to be set.
     */
    public void setEmail(String email) { this.email = email; }

    /**
     * Sets the ID of the user.
     *
     * @param ID The ID to be set.
     */
    public void setID(String ID) { this.ID = ID;}

    /**
     * Sets the password of the user.
     *
     * @param password The password to be set.
     */
    public void setPassword(String password) { this.password = password; }

    /**
     * Sets the name of the user.
     *
     * @param name The name to be set.
     */
    public void setName(String name) { this.name = name; }

    /**
     * Sets the age of the user.
     *
     * @param age The age to be set.
     */
    public void setAge(String age) { this.age = age;}

    /**
     * Sets the Join date of the user.
     *
     * @param joinDate The join date to be set.
     */
    public void setJoinDate(String joinDate){ this.joinDate = joinDate;}

    /**
     * Sets the gender of the user.
     *
     * @param gender The gender to be set.
     */
    public void setGender(String gender) { this.gender = gender; }

    /**
     * Sets the bio of the user.
     *
     * @param bio The bio to be set.
     */
    public void setBio(String bio) { this.bio = bio; }

    /**
     * Sets the birthday of the user.
     *
     * @param birthday The birthday to be set.
     */
    public void setBirthday(String birthday) { this.birthday = birthday; }

    /*---------------------------------------------- GETTER METHODS ----------------------------------------------*/
    /**
     * Gets the username of the user.
     *
     * @return The username of the user.
     */
    public String getUsername() { return username; }

    /**
     * Gets the email of the user.
     *
     * @return The email of the user.
     */
    public String getEmail() { return email; }

    /**
     * Gets the ID of the user.
     *
     * @return The ID of the user.
     */
    public String getID() { return ID; }

    /**
     * Gets the name of the user.
     *
     * @return The name of the user.
     */
    public String getName() { return name; }

    /**
     * Gets the age of the user.
     *
     * @return The age of the user.
     */
    public String getAge() { return age; }

    /**
     * Gets the join date of the user.
     *
     * @return The join date of the user.
     */
    public String getJoinDate() { return joinDate; }

    /**
     * Gets the gender of the user.
     *
     * @return The gender of the user.
     */
    public String getGender() { return gender; }

    /**
     * Gets the bio of the user.
     *
     * @return The bio of the user.
     */
    public String getBio() { return bio; }

    /**
     * Gets the birthday of the user.
     *
     * @return The birthday of the user.
     */
    public String getBirthday() { return birthday; }

    /*------------------------------------------------- USER -------------------------------------------------*/

    /**
     * Gets the singleton instance of the User class.
     *
     * @return The instance of the User class.
     */
    public static synchronized User getInstance() {
        if (instance == null){ instance = new User(); }
        return instance;
    }

    /*---------------------------------------------- SERVER URL ----------------------------------------------*/

    /**
     * Gets the URL for the server.
     *
     * @return The URL for the server.
     */
    public static String getServerUrl(){ return SERVER_URL; }

    /*---------------------------------------------- USER LOGIN ----------------------------------------------*/
    /**
     * Sets the URL for user login with the provided username and password.
     */
    public void setURL_UP(){ USERLOGIN_URL = SERVER_URL + "login/u/" + username + "/" + password + "/"; }

    /**
     * Sets the URL for user login with the provided email and password.
     */
    public void setURL_EP(){ USERLOGIN_URL = SERVER_URL + "login/e/" + email + "/" + password + "/"; }

    /**
     * Sets the URL for friend requests with the provided username.
     */
    public void setURL_FR(){ FRIENDREQUEST_URL = SERVER_URL + "friend-requests/list/" + username; }

    /**
     * Gets the URL for user login.
     *
     * @return The URL for user login.
     */
    public String getURL_USERLOGIN(){ return USERLOGIN_URL; }

    /*-------------------------------------------- USER INFORMATION --------------------------------------------*/
    /**
     * Sets the URL for user information with the provided ID.
     */
    public void setURL_USERINFO(){ USERINFO_URL = SERVER_URL + "users/" + username; }

    /**
     * Gets the URL for user information.
     *
     * @return The URL for user information.
     */
    public String getURL_USERINFO(){ return USERINFO_URL; }


    public String getURL_FR(){ return FRIENDREQUEST_URL; }

    /*---------------------------------------------- WEBSOCKETS ----------------------------------------------*/
    /**
     * Gets the URL for Websockets.
     *
     * @return The URL for websockets
     */
    public static String getWEBSOCKET_URL(){ return WEBSOCKET_URL; }

    /*---------------------------------------------- SCREEN POPUPS ----------------------------------------------*/
//    /**
//     * Displays a toast message based on the provided error code and logs the error message.
//     *
//     * @param context   The context in which the toast message will be displayed.
//     * @param errorCode The error code indicating the type of error. 0 is a login error and 1 is a server error.
//     * @param error     The error message to be logged.
//     */
//
//    public static void toastError(Context context, int errorCode, String error){
//        int duration = Toast.LENGTH_LONG;
//        String text;
//        switch (errorCode){
//            case 0:
//                text = "There was an error while attempting to login";
//                break;
//            case 1:
//                text = "There is an error with the server";
//                break;
//            case 2:
//                text = error;
//                break;
//            default:
//                text = "Something went wrong";
//        }
//        Toast.makeText(context, text, duration).show();
//        Log.e("Error", error);
//    }
    public static void dialogError(Context context, String error){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(error)
                .setTitle("Error:");

        AlertDialog dialog = builder.create();
        dialog.show();
    }
   }
