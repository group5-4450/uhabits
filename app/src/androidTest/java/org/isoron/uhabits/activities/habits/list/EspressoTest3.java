package org.isoron.uhabits.activities.habits.list;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.isoron.uhabits.R;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.longClick;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class EspressoTest3 {

    @Rule
    public ActivityTestRule<ListHabitsActivity> mActivityTestRule = new ActivityTestRule<>(ListHabitsActivity.class);

    @Test
    public void espressoTest3() {

        ViewInteraction actionMenuItemView = onView(
                allOf(withId(R.id.actionAdd), withContentDescription("Add habit"), isDisplayed()));
        actionMenuItemView.perform(click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.tvName),
                        withParent(allOf(withId(R.id.namePanel),
                                withParent(withId(R.id.formPanel)))),
                        isDisplayed()));
        appCompatEditText.perform(replaceText("EspressoTest"), closeSoftKeyboard());

        ViewInteraction switch_ = onView(
                allOf(withId(R.id.numericSwitch),
                        withParent(allOf(withId(R.id.numericPanel),
                                withParent(withId(R.id.formPanel)))),
                        isDisplayed()));
        switch_.perform(click());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.numericalValue), isDisplayed()));
        appCompatEditText2.perform(replaceText("5"), closeSoftKeyboard());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.saveButton), withText("Save"), isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.buttonSave), withText("Save"), isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction checkmarkButtonView = onView(
                allOf(withText("\uF00D"),
                        withParent(allOf(withId(R.id.checkmarkPanel),
                                withParent(withId(R.id.innerFrame)))),
                        isDisplayed()));
        checkmarkButtonView.perform(longClick());

        ViewInteraction checkmarkButtonView2 = onView(
                allOf(withText("\uF00D"),
                        withParent(allOf(withId(R.id.checkmarkPanel),
                                withParent(withId(R.id.innerFrame)))),
                        isDisplayed()));
        checkmarkButtonView2.perform(longClick());

        ViewInteraction checkmarkButtonView3 = onView(
                allOf(withText("\uF00D"),
                        withParent(allOf(withId(R.id.checkmarkPanel),
                                withParent(withId(R.id.innerFrame)))),
                        isDisplayed()));
        checkmarkButtonView3.perform(longClick());

        ViewInteraction checkmarkButtonView4 = onView(
                allOf(withText("\uF00D"),
                        withParent(allOf(withId(R.id.checkmarkPanel),
                                withParent(withId(R.id.innerFrame)))),
                        isDisplayed()));
        checkmarkButtonView4.perform(longClick());

        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());

        ViewInteraction appCompatTextView = onView(
                allOf(withId(R.id.title), withText("Settings"), isDisplayed()));
        appCompatTextView.perform(click());

        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.list),
                        withParent(allOf(withId(R.id.list_container),
                                withParent(withId(R.id.fragment)))),
                        isDisplayed()));
        recyclerView.perform(actionOnItemAtPosition(1, click()));

        ViewInteraction imageButton = onView(
                allOf(withContentDescription("Navigate up"),
                        withParent(allOf(withId(R.id.toolbar),
                                withParent(withId(R.id.container)))),
                        isDisplayed()));
        imageButton.perform(click());

        ViewInteraction checkmarkButtonView5 = onView(
                allOf(withText("\uF00D"),
                        withParent(allOf(withId(R.id.checkmarkPanel),
                                withParent(withId(R.id.innerFrame)))),
                        isDisplayed()));
        checkmarkButtonView5.perform(click());

    }

}
