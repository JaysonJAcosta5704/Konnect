package com.example.konnect;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.konnect.entry.SignupActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;



import org.junit.Rule;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class ChanhoYangTest {
    @Rule
    public ActivityTestRule<SignupActivity> activityRule = new ActivityTestRule<>(SignupActivity.class);

    @Test
    public void testUserSignUp() {
        // Replace these with test data
        String testUsername = "testUser";
        String testName = "testName";
        String testPassword = "testPassword";
        String testConfirm = "testPassword";
        String testEmail = "testEmail@test.com";
        String testAge = "99";
        String testGender = "Male";
        String testDOB = "01/01/1925";

        onView(withId(R.id.signup_username_edt)).perform(typeText(testUsername));
        onView(withId(R.id.signup_name_edt)).perform(typeText(testName));
        onView(withId(R.id.signup_password_edt)).perform(typeText(testPassword));
        onView(withId(R.id.signup_confirm_edt)).perform(typeText(testConfirm));
        onView(withId(R.id.signup_email_edt)).perform(typeText(testEmail));
        onView(withId(R.id.signup_age_edt)).perform(typeText(testAge));
        onView(withId(R.id.signup_gender_spinner)).perform(click()); // You might need to handle this differently for a spinner
        onView(withId(R.id.signup_birthday_btn)).perform(typeText(testDOB)); // You might need to handle this differently for a date picker
        onView(withId(R.id.signup_signup_btn)).perform(click());


    }
}
