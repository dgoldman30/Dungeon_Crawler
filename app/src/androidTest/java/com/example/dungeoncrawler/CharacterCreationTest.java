package com.example.dungeoncrawler;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Test;

public class CharacterCreationTest {

    @org.junit.Rule
    public ActivityScenarioRule<ControllerActivity> activityRule
            = new ActivityScenarioRule<>(ControllerActivity.class);
    @Test
    public void attributeIncreased() {
        ViewInteraction strButton = Espresso.onView(ViewMatchers.withId(R.id.STRbutton));
        ViewInteraction attributesLeft = Espresso.onView(ViewMatchers.withId(R.id.attPointsLeft));

        strButton.perform(ViewActions.click());

        attributesLeft.check(ViewAssertions.matches(ViewMatchers.withSubstring("2")));
    }

    @Test
    public void createCharacter() {
        ViewInteraction strButton = Espresso.onView(ViewMatchers.withId(R.id.STRbutton));

        strButton.perform(ViewActions.click());
        strButton.perform(ViewActions.click());
        strButton.perform(ViewActions.click());

        ViewInteraction create = Espresso.onView(ViewMatchers.withId(R.id.enterChoice));

        create.perform(ViewActions.click());

        ViewInteraction nameField = Espresso.onView(ViewMatchers.withId(R.id.nameField));
        String name = "HUMAN GLADIATOR";

        nameField.check(ViewAssertions.matches(ViewMatchers.withSubstring(name)));

    }
}
