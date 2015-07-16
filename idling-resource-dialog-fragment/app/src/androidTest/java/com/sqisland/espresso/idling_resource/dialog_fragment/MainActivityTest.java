package com.sqisland.espresso.idling_resource.dialog_fragment;

import android.app.Instrumentation;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingPolicies;
import android.support.test.espresso.IdlingResource;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.text.format.DateUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeUnit;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    private IdlingResource idlingResource;

    @Before
    public void doBefore() {
        long waitingTime = DateUtils.SECOND_IN_MILLIS * 3;

        IdlingPolicies.setMasterPolicyTimeout(
                waitingTime * 2, TimeUnit.MILLISECONDS);
        IdlingPolicies.setIdlingResourceTimeout(
                waitingTime * 2, TimeUnit.MILLISECONDS);

        idlingResource = new HandlerIdlingResource(waitingTime);
        Espresso.registerIdlingResources(idlingResource);
    }


    @After
    public void unregisterIdlingResource() {
        Espresso.unregisterIdlingResources(idlingResource);
    }


    @Test
    public void done() {
        // TODO: Add IdlingResource so this test passes
        onView(withId(R.id.text))
                .check(matches(withText(R.string.done)));
    }
}