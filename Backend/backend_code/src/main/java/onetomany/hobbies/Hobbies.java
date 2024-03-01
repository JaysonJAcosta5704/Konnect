//package onetomany.hobbies;
//
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import javax.persistence.EnumType;
//import javax.persistence.Enumerated;
//import onetomany.Users.User;
//
//@Entity
//public class Hobbies {
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Id
//    private int id;
//    private String name; // Assuming each hobby has a name
//
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    @JsonIgnore
//    private User user;
//
//    public Hobbies(String name, User user) {
//        this.name = name;
//        this.user = user;
//
//    }
//
//    //adding type of the hobby as well
//    // Getters and setters
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//}
package onetomany.hobbies;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import com.fasterxml.jackson.annotation.JsonIgnore;
import onetomany.Users.User;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
@Entity
public class Hobbies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @Enumerated(EnumType.STRING)
    private HobbyType hobbyType;

    @ManyToMany(mappedBy = "hobbies")
    private Set<User> users = new HashSet<>();

    public Hobbies() {}

    public Hobbies(String name, HobbyType hobbyType) {
        this.name = name;
        this.hobbyType = hobbyType;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HobbyType getHobbyType() {
        return hobbyType;
    }

    public void setHobbyType(HobbyType hobbyType) {
        this.hobbyType = hobbyType;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}