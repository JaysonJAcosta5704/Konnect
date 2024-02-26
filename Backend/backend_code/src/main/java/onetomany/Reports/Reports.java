package onetomany.Reports;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import onetomany.Users.User;

@Entity
public class Reports {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;



    private String report;


    @ManyToOne
    @JsonIgnore
    @JoinColumn
    private User user;

    public int getId(){
        return this.id;
    }
    public void setId(int newId){
        this.id=newId;
    }

    public String getReport(){
        return this.report;
    }

    public User getUser(){
        return this.user;
    }

    public Reports(){
    }
    public Reports(String report){
        this.report= report;
    }

    public void setUser(User user){
        this.user= user;
    }




}
