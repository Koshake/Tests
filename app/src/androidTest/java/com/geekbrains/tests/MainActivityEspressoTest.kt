package com.geekbrains.tests

import android.widget.Button
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.geekbrains.tests.view.search.MainActivity
import junit.framework.TestCase
import org.junit.After
import org.junit.Before
import org.junit.Test

class MainActivityEspressoTest {
    private lateinit var scenario: ActivityScenario<MainActivity>

    @Before
    fun setup() {
        scenario = ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun activity_NotNull() {
        scenario.onActivity {
            TestCase.assertNotNull(it)
        }
    }

    @Test
    fun activity_isResumed() {
        TestCase.assertEquals(Lifecycle.State.RESUMED, scenario.state)
    }

    @Test
    fun activity_ToDetailsButton_NotNull() {
        scenario.onActivity {
            val toDetailsButton = it.findViewById<Button>(R.id.toDetailsActivityButton)
            TestCase.assertNotNull(toDetailsButton)
        }
    }

    @Test
    fun activity_ToDetailsButton_IsDisplayed() {
        onView(withId(R.id.toDetailsActivityButton)).check(matches(isDisplayed()))
    }

    @Test
    fun activity_ToDetailsButton_IsCompletelyDisplayed() {
        onView(withId(R.id.toDetailsActivityButton)).check(matches(isCompletelyDisplayed()))
    }

    @Test
    fun activitySearchEditText_IsDisplayed() {
        onView(withId(R.id.searchEditText)).check(matches(isDisplayed()))
    }

    @Test
    fun activitySearchEditText_IsCompletelyDisplayed() {
        onView(withId(R.id.searchEditText)).check(matches(isCompletelyDisplayed()))
    }

    @Test
    fun activityTotalCountTextView_IsInvisible() {
        onView(withId(R.id.totalCountTextView)).check(
            matches(
                withEffectiveVisibility(
                    Visibility.INVISIBLE
                )
            )
        )
    }

    @Test
    fun activity_searchEditText_textChanged() {
        onView(withId(R.id.searchEditText)).perform(click())
        onView(withId(R.id.searchEditText))
            .perform(ViewActions.typeText("text"), ViewActions.closeSoftKeyboard())
        onView(withId(R.id.searchEditText))
            .perform(ViewActions.pressImeActionButton())

        onView(withId(R.id.searchEditText))
            .check(matches(withText("text")))
    }

    @Test
    fun test_navDetailsActivity() {
        onView(withId(R.id.toDetailsActivityButton)).perform(click())

        onView(withId(R.id.details_layout)).check(matches(isDisplayed()))
    }

    @Test
    fun backPress_toMainActivity() {
        onView(withId(R.id.toDetailsActivityButton)).perform(click())
        onView(withId(R.id.details_layout)).check(matches(isDisplayed()))

        pressBack()

        onView(withId(R.id.main_layout)).check(matches(isDisplayed()))

    }
    @After
    fun close() {
        scenario.close()
    }
}