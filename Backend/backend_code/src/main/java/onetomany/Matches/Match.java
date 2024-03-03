package onetomany.Matches;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import onetomany.Users.User;
@Entity
@Table(name="matches")
public class Match {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    private int matchedID;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore

    private User user;

    public Match(){

    }

    public Match(User matchedUser){
        this.matchedID= matchedUser.getId();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getMatchedID(){
        return this.matchedID;
    }

}
