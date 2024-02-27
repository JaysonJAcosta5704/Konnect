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


    @OneToOne
    @JsonIgnore
    private User user;

    public Reports(){
    }
    public Reports(String report){
        this.report= report;
    }


}
