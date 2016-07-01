package com.amicly.myapplication;

import android.support.test.rule.ActivityTestRule;
import android.view.View;
import android.view.ViewGroup;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeUp;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by aaronfields on 7/1/16.
 */
public class BookstoreTest {

    public static Matcher<View> nthChildOf(final Matcher<View> parentMatcher, final int childPosition) {
        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("with " + childPosition + " child view of type parentMatcher");
            }

            @Override
            public boolean matchesSafely(View view) {
                if (!(view.getParent() instanceof ViewGroup)) {
                    return parentMatcher.matches(view.getParent());
                }
                ViewGroup group = (ViewGroup) view.getParent();
                return parentMatcher.matches(view.getParent()) && group.getChildAt(childPosition).equals(view);
            }
        };
    }

    @Rule
    public ActivityTestRule<MainActivity> mMainActivityActivityTestRule =
            new ActivityTestRule<MainActivity>(MainActivity.class);

    @Test
    public void canClickGridItem() {

        onView(withId(R.id.gridview)).perform(swipeUp());
        onView(withText("Crime and Punishment")).check(matches(isDisplayed()));

    }

    @Test
    public void addToCart() {
        onView(nthChildOf(withId(R.id.gridview), 0)).perform(click());
        onView(withId(R.id.detail_cartButton)).perform(click());
        onView(withId(R.id.checkout_button)).perform(click());
        onView(nthChildOf(withId(R.id.cart_gridview), 0)).check(matches(hasDescendant(withText("For Whom the Bell Tolls"))));
    }

    @Test
    public void categoryResults(){
        //onView(withId(R.menu.main_menu)).perform(click());
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(withText("Science Fiction & Fantasy")).perform(click());
        //onView(withId(R.id.cat_fantasy)).perform(click());
        onView(nthChildOf(withId(R.id.gridview), 1)).check(matches(hasDescendant(withText("The Lord of the Rings"))));

    }

}
