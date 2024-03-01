//package onetomany.Users;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//import javax.persistence.*;
//
//
//import onetomany.Matches.Match;
//
//
//import onetomany.Reports.Reports;
//
//@Entity
//@Table(name="Users")
//public class User {
//
//    /*
//     * The annotation @ID marks the field below as the primary key for the table created by springboot
//     * The @GeneratedValue generates a value if not already present, The strategy in this case is to start from 1 and increment for each table
//     */
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//    private String name;
//    private String emailId;
//    private Date joiningDate;
//    private boolean ifActive;
//
//    private String UserPassword;
//    private Date lastLoggin;
//
//    private String UserBio;
//    private int viewCount;
//    private int acceptanceCount;
//    private String UserImagePath;
//    @ElementCollection
//    private List<Integer> UserHobbiesLists;
//
//
//
//
//
//
//
//
//    /*
//     * @OneToOne creates a relation between the current entity/table(Laptop) with the entity/table defined below it(User), the cascade option tells springboot
//     * to create the child entity if not present already (in this case it is laptop)
//     * @JoinColumn specifies the ownership of the key i.e. The User table will contain a foreign key from the laptop table and the column name will be laptop_id
//     */
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "match_id")
//    private Match match;
//
//    /*
//     * @OneToMany tells springboot that one instance of User can map to multiple instances of Phone OR one user row can map to multiple rows of the phone table
//     */
//    @OneToMany  (mappedBy = "user")  ///Initialize Hobbies  here  like  private List<Phone> phones;
//            List<Reports> userReports;
//
//
//    @OneToMany
//    List<Match> UserMatches;
//
//
//    // =============================== Constructors ================================== //
//
//
//    public User(String name, String emailId, Date joiningDate, String userPassword) {
//        this.name = name;
//        this.emailId = emailId;
//        this.joiningDate = joiningDate;
//        this.ifActive = true;
//        userReports = new ArrayList<>();
//        this.UserPassword= userPassword;
//        this.UserMatches= new ArrayList<>();
//
//    }
//
//
//    public User() {
//        userReports = new ArrayList<>();
//    }
//
//
//    // =============================== Getters and Setters for each field ================================== //
//
//
//    public int getId(){
//        return id;
//    }
//    public String getUserPassword(){
//        return this.UserPassword;
//    }
//    public void setLastLoggin(){
//        lastLoggin= new Date();
//    }
//
//
//    public void setId(int id){
//        this.id = id;
//    }
//
//    public String getName(){
//        return name;
//    }
//
//    public void setName(String name){
//        this.name = name;
//    }
//
//    public String getEmailId(){
//        return emailId;
//    }
//
//    public void setEmailId(String emailId){
//        this.emailId = emailId;
//    }
//
//    public Date getJoiningDate(){
//        return joiningDate;
//    }
//
//    public void setJoiningDate(Date joiningDate){
//        this.joiningDate = joiningDate;
//    }
//
//
//
//    public void setIfActive(boolean ifActive){
//        this.ifActive = ifActive;
//    }
//
//    public void setUserReports(Reports report){
//        userReports.add(report);
//    }
//
//    public boolean isIfActive() {
//        return ifActive;
//    }
//
//    public List<Reports> getReports() {
//        return userReports;
//    }
//
//
//    public void setReports(List<Reports> reports) {
//        this.userReports = reports;
//    }
//
//    public void addReport(Reports report){
//        this.userReports.add(report);
//    }
//    public void addUserMatch(Match match){
//        this.UserMatches.add(match);
//    }
//    public List<Match> getUserMatches(){
//        return this.UserMatches;
//    }
//    public Date getLastLoggin(){
//        return this.lastLoggin;
//    }
//
//}
package onetomany.Users;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;


import onetomany.Matches.Match;


import onetomany.Reports.Reports;
import onetomany.hobbies.Hobbies;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name="Users")
public class User {

    /*
     * The annotation @ID marks the field below as the primary key for the table created by springboot
     * The @GeneratedValue generates a value if not already present, The strategy in this case is to start from 1 and increment for each table
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String emailId;
    private Date joiningDate;
    private boolean ifActive;

    private String UserPassword;
    private Date lastLoggin;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_hobbies",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "hobby_id")
    )
//    private Set<Hobbies> hobbies;
    private Set<Hobbies> hobbies = new HashSet<>();
    private String UserBio;
    private int viewCount;
    private int acceptanceCount;
    private String UserImagePath;
    @ElementCollection
    private List<Integer> UserHobbiesLists;








    /*
     * @OneToOne creates a relation between the current entity/table(Laptop) with the entity/table defined below it(User), the cascade option tells springboot
     * to create the child entity if not present already (in this case it is laptop)
     * @JoinColumn specifies the ownership of the key i.e. The User table will contain a foreign key from the laptop table and the column name will be laptop_id
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "match_id")
    private Match match;

    /*
     * @OneToMany tells springboot that one instance of User can map to multiple instances of Phone OR one user row can map to multiple rows of the phone table
     */
    @OneToMany  (mappedBy = "user")  ///Initialize Hobbies  here  like  private List<Phone> phones;
            List<Reports> userReports;


    @OneToMany
    List<Match> UserMatches;


    // =============================== Constructors ================================== //


    public User(String name, String emailId, Date joiningDate, String userPassword) {
        this.name = name;
        this.emailId = emailId;
        this.joiningDate = joiningDate;
        this.ifActive = true;
        userReports = new ArrayList<>();
        this.UserPassword= userPassword;
        this.UserMatches= new ArrayList<>();

    }


    public User() {
        userReports = new ArrayList<>();
    }


    // =============================== Getters and Setters for each field ================================== //

    public Set<Hobbies> getHobbies() {
        return hobbies;
    }

    public void setHobbies(Set<Hobbies> hobbies) {
        this.hobbies = hobbies;
    }
    public int getId(){
        return id;
    }
    public String getUserPassword(){
        return this.UserPassword;
    }
    public void setLastLoggin(){
        lastLoggin= new Date();
    }


    public void setId(int id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getEmailId(){
        return emailId;
    }

    public void setEmailId(String emailId){
        this.emailId = emailId;
    }

    public Date getJoiningDate(){
        return joiningDate;
    }

    public void setJoiningDate(Date joiningDate){
        this.joiningDate = joiningDate;
    }



    public void setIfActive(boolean ifActive){
        this.ifActive = ifActive;
    }

    public void setUserReports(Reports report){
        userReports.add(report);
    }

    public boolean isIfActive() {
        return ifActive;
    }

    public List<Reports> getReports() {
        return userReports;
    }


    public void setReports(List<Reports> reports) {
        this.userReports = reports;
    }

    public void addReport(Reports report){
        this.userReports.add(report);
    }
    public void addUserMatch(Match match){
        this.UserMatches.add(match);
    }
    public List<Match> getUserMatches(){
        return this.UserMatches;
    }
    public Date getLastLoggin(){
        return this.lastLoggin;
    }

}
