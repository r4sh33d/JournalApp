package com.r4sh33d.journalapp;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.r4sh33d.journalapp.activities.SignInActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class TestSignInActivity {

    @Rule
    // third parameter is set to false which means the activity is not started automatically
    public ActivityTestRule<SignInActivity> mActivityRule =
            new ActivityTestRule<>(SignInActivity.class, false, true);


    // This method tests the sign in page UI when users are opening the app for the first time.
    @Test
    public void testLoginPage() {
        //Check if the views are  displayed correctly

        //Google sign in button
        onView(withText("Sign in with Google"))
                .check(matches(isDisplayed()));
        //Email sign in
        onView(withText("Sign in with email;"))
                .check(matches(isDisplayed()));
        //If we reach without exception, Test is successful.
        //The sign in screen is set up correctly.
    }
}
