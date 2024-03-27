    package com.example.konnect;

    import android.content.Context;
    import android.util.Log;
    import android.widget.Toast;
    import java.util.Date;

    public class User {
        private String name, email, username, gender, bio, password, ID, age;
        private Date birthday;
        private static User instance;
        //    private final String SERVER_URL = "http://coms-309-001.class.las.iastate.edu:8080/";
        private final String SERVER_URL = "https://df952b3b-a205-4a2f-a0e0-a0f471c5f2bb.mock.pstmn.io/";
        private String USERLOGIN_URL, USERINFO_URL;
        /* Constructor */
        public User(){}

        /* Setter Methods */
        public void setUsername(String username) { this.username = username; }
        public void setEmail(String email) { this.email = email; }
        public void setID(String ID) { this.ID = ID;}
        public void setPassword(String password) { this.password = password; }

        public void setName(String name) { this.name = name; }
        public void setAge(String age) { this.age = age;}
        public void setGender(String gender) { this.gender = gender; }

        public void setBio(String bio) { this.bio = bio; }

        public void setBirthday(Date birthday) { this.birthday = birthday; }

        /* Getter Methods */
        public String getUsername() { return username; }
        public String getEmail() { return email; }
        public String getPassword(){return password;}
        public String getID() { return ID; }
        public String getName() { return name; }
        public String getAge() { return age; }
        public String getGender() { return gender; }
        public String getBio() { return bio; }
        public Date getBirthday() { return birthday; }
        public static synchronized User getInstance() {
            if (instance == null){ instance = new User(); }
            return instance;
        }

        /* URLS METHODS */
        public void setURL_UP(){ USERLOGIN_URL = SERVER_URL + "user/" + username + "/" + password; }
        public void setURL_EP(){ USERLOGIN_URL = SERVER_URL + "user/" + email + "/" + password; }
        public String getURL_USERLOGIN(){ return USERLOGIN_URL; }

        public void setURL_USERINFO(){ USERINFO_URL = SERVER_URL + "user/" + ID; }
        public String getURL_USERINFO(){ return USERINFO_URL; }

        /* Other Methods */
        public static void toastError(Context context, int errorCode, String error){
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
