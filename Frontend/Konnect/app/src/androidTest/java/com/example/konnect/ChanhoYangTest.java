package com.example.konnect;

import static androidx.test.espresso.Espresso.onData;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.instanceOf;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import androidx.test.espresso.contrib.PickerActions;
import android.widget.DatePicker;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;




import android.widget.DatePicker;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.intent.Intents;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import com.example.konnect.entry.SignupActivity;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4ClassRunner.class)
public class ChanhoYangTest {

    private static final int SIMULATED_DELAY_MS = 500;

    @Test
    public void signup() {
        try (ActivityScenario<SignupActivity> activityScenario = ActivityScenario.launch(SignupActivity.class)) {
            String username = "TestUser";
            String password = "TestPassword";
            String confirm = "TestPassword";
            String email = "testuser@example.com";
            String name = "Test User";
            String age = "25";
            String gender = "Male";

            onView(withId(R.id.signup_username_edt)).perform(typeText(username), closeSoftKeyboard());
            onView(withId(R.id.signup_password_edt)).perform(typeText(password), closeSoftKeyboard());
            onView(withId(R.id.signup_confirm_edt)).perform(typeText(confirm), closeSoftKeyboard());
            onView(withId(R.id.signup_email_edt)).perform(typeText(email), closeSoftKeyboard());
            onView(withId(R.id.signup_name_edt)).perform(typeText(name), closeSoftKeyboard());
            onView(withId(R.id.signup_age_edt)).perform(typeText(age), closeSoftKeyboard());

            onView(withId(R.id.signup_gender_spinner)).perform(click());
            onData(allOf(is(instanceOf(String.class)), is(gender))).perform(click());

            onView(withId(R.id.signup_birthday_btn)).perform(click());
            onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(1996, 8, 25));
            onView(withText("OK")).perform(click());


            onView(withId(R.id.signup_signup_btn)).perform(click());

            try { Thread.sleep(SIMULATED_DELAY_MS); } catch (InterruptedException ignored) {}

            // Add assertions to check if the signup was successful
            try (ActivityScenario<ChoosehobbiesActivity> scenario = ActivityScenario.launch(ChoosehobbiesActivity.class)) {
                scenario.onActivity(activity -> {
                    assertTrue(activity.getClass().isAssignableFrom(ChoosehobbiesActivity.class));
                });
            }
        }
    }

    @Before
    public void setUp() {
        Intents.init();
    }

    @After
    public void tearDown() {
        Intents.release();
    }

    @Test
    public void settingsActivityNavigation() {
        try (ActivityScenario<SettingsActivity> activityScenario = ActivityScenario.launch(SettingsActivity.class)) {

            // Click on the button that navigates to ChatActivity
            onView(withId(R.id.ChatButton)).perform(click());
            intended(hasComponent(ChatActivity.class.getName()));

            // Go back to SettingsActivity
            pressBack();

            // Click on the button that navigates to ProfileEditActivity
            onView(withId(R.id.Profile_Edit_Button)).perform(click());
            intended(hasComponent(ProfileEditActivity.class.getName()));

            // Go back to SettingsActivity
            pressBack();

            // Click on the button that navigates to MinigamesActivity
            onView(withId(R.id.Minigame_Button)).perform(click());
            intended(hasComponent(MinigamesActivity.class.getName()));

            // Go back to SettingsActivity
            pressBack();

            // Click on the button that navigates to FindPeopleActivity
            onView(withId(R.id.GroupChatButton)).perform(click());
            intended(hasComponent(FindPeopleActivity.class.getName()));

            //Go back to SettingsActivity
            pressBack();



            // Add more assertions for other buttons in your SettingsActivity
            try (ActivityScenario<SettingsActivity> scenario = ActivityScenario.launch(SettingsActivity.class)) {
                scenario.onActivity(activity -> {
                    assertTrue(activity.getClass().isAssignableFrom(SettingsActivity.class));
                });
            }
        }
    }

}
