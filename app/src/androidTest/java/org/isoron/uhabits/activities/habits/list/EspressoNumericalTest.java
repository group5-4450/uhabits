package org.isoron.uhabits.activities.habits.list;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.isoron.uhabits.R;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class EspressoNumericalTest {

    @Rule
    public ActivityTestRule<ListHabitsActivity> mActivityTestRule = new ActivityTestRule<>(ListHabitsActivity.class);

    @Test
    public void espressoNumericalTest() {
        ViewInteraction appCompatImageButton = onView(
                allOf(withId(R.id.next), isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction appCompatImageButton2 = onView(
                allOf(withId(R.id.next), isDisplayed()));
        appCompatImageButton2.perform(click());

        ViewInteraction appCompatImageButton3 = onView(
                allOf(withId(R.id.done), isDisplayed()));
        appCompatImageButton3.perform(click());

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

        ViewInteraction actionMenuItemView2 = onView(
                allOf(withId(R.id.action_edit_habit), withContentDescription("Edit"), isDisplayed()));
        actionMenuItemView2.perform(click());

        ViewInteraction appCompatImageButton4 = onView(
                allOf(withId(R.id.numericPopUpIcon), withContentDescription("Numeric Icon"),
                        withParent(allOf(withId(R.id.numericPanel),
                                withParent(withId(R.id.formPanel)))),
                        isDisplayed()));
        appCompatImageButton4.perform(click());

        ViewInteraction editText = onView(
                allOf(withId(R.id.numericalValue), withText("5"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.formPanel),
                                        0),
                                1),
                        isDisplayed()));
        editText.check(matches(withText("5")));

    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
