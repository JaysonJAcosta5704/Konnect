//package coms309.people;
//
//
///**
// * Provides the Definition/Structure for the people row
// *
// * @author Vivek Bengre
// */
//
//public class Person {
//
//    private String firstName;
//
//    private String lastName;
//
//    private String address;
//
//    private String telephone;
//
//
//
//    public Person(){
//
//    }
//
//    public Person(String firstName, String lastName, String address, String telephone){
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.address = address;
//        this.telephone = telephone;
//    }
//
//    public String getFirstName() {
//        return this.firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public String getLastName() {
//        return this.lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//
//    public String getAddress() {
//        return this.address;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }
//
//    public String getTelephone() {
//        return this.telephone;
//    }
//
//    public void setTelephone(String telephone) {
//        this.telephone = telephone;
//    }
//
//    @Override
//    public String toString() {
//        return firstName + " "
//               + lastName + " "
//               + address + " "
//               + telephone;
//
//    }
//}
package coms309.people;

public class Person {

    private String firstName;
    private String lastName;
    private String address;
    private String telephone;
    private String gender; // Added gender field
    private String eyeColor; // Added eyeColor field

    public Person() {
        // Default constructor
    }

    public Person(String firstName, String lastName, String address, String telephone, String gender, String eyeColor) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.telephone = telephone;
        this.gender = gender;
        this.eyeColor = eyeColor;
    }

    // Getters and Setters for new fields along with the existing ones
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", telephone='" + telephone + '\'' +
                ", gender='" + gender + '\'' +
                ", eyeColor='" + eyeColor + '\'' +
                '}';
    }
}
