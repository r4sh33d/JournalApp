package com.r4sh33d.journalapp;

import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.r4sh33d.journalapp.activities.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class TestFabNoteListFragment {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule(MainActivity.class);

    @Test
    public void clickFabButtonForNewNotes() {
        //The fab should move to AddNotsFragment
         onView(withId( R.id.fab)).perform(ViewActions.click());
         // The The Add/Edit Notes screen should be displayed now. Since a Edit text with id: {title_edittext} is
        //present there , try to check if the EditText is displayed
        onView(withId(R.id.title_edittext)).check(matches(isDisplayed())); // if it's displayed, We are in the AddNotesFragment
        //if we reach here, test is successful
    }
}
