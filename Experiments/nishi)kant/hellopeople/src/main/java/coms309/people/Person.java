//package coms309.people;
//
//public class Person {
//
//    private String firstName;
//    private String lastName;
//    private String address;
//    private String telephone;
//    private String gender; // Added gender field
//    private String eyeColor; // Added eyeColor field
//
//    public Person() {
//        // Default constructor
//    }
//
//    public Person(String firstName, String lastName, String address, String telephone, String gender, String eyeColor) {
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.address = address;
//        this.telephone = telephone;
//        this.gender = gender;
//        this.eyeColor = eyeColor;
//    }
//
//    // Getters and Setters for new fields along with the existing ones
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//
//    public String getAddress() {
//        return address;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }
//
//    public String getTelephone() {
//        return telephone;
//    }
//
//    public void setTelephone(String telephone) {
//        this.telephone = telephone;
//    }
//
//    public String getGender() {
//        return gender;
//    }
//
//    public void setGender(String gender) {
//        this.gender = gender;
//    }
//
//    public String getEyeColor() {
//        return eyeColor;
//    }
//
//    public void setEyeColor(String eyeColor) {
//        this.eyeColor = eyeColor;
//    }
//
//    @Override
//    public String toString() {
//        return "Person{" +
//                "firstName='" + firstName + '\'' +
//                ", lastName='" + lastName + '\'' +
//                ", address='" + address + '\'' +
//                ", telephone='" + telephone + '\'' +
//                ", gender='" + gender + '\'' +
//                ", eyeColor='" + eyeColor + '\'' +
//                '}';
//    }
//}
package coms309.people;

public class Person {

    private String firstName;
    private String lastName;
    private String address;
    private String telephone;
    private String gender;
    private String eyeColor;
    private String hobby; // Added hobby field
    private String profession; // Added profession field

    // Default constructor
    public Person() {
    }

    // Constructor with all fields
    public Person(String firstName, String lastName, String address, String telephone,
                  String gender, String eyeColor, String hobby, String profession) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.telephone = telephone;
        this.gender = gender;
        this.eyeColor = eyeColor;
        this.hobby = hobby;
        this.profession = profession;
    }

    // Getters and setters
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

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
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
                ", hobby='" + hobby + '\'' +
                ", profession='" + profession + '\'' +
                '}';
    }
}
