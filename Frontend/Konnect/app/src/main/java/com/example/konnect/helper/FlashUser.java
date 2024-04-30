package com.example.konnect.helper;

import java.util.Date;
import java.util.List;

public class FlashUser {
    private String name;
    private Date dob;
    private String gender;
    private List<String> hobbies;


    public FlashUser(String name, Date dob, String gender, List<String> hobbies) {
        this.name = name;

        this.dob = dob;
        this.gender = gender;
        this.hobbies = hobbies;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfBirth() {
        return dob;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dob = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<String> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<String> hobbies) {
        this.hobbies = hobbies;
    }
}
