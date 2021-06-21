import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import com.geekbrains.tests.R
import com.geekbrains.tests.TEST_EDITTEXT_ALGOL
import com.geekbrains.tests.TEST_NUMBER_OF_RESULTS_ALGOL
import com.geekbrains.tests.view.search.MainActivity
import org.junit.Before
import org.junit.Test

class ActivitySearchEspressoTest {

    private lateinit var scenario: ActivityScenario<MainActivity>

    @Before
    fun setup() {
        scenario = ActivityScenario.launch(MainActivity::class.java)
    }


    @Test
    fun activitySearch_IsWorking() {
        Espresso.onView(ViewMatchers.withId(R.id.searchEditText)).perform(ViewActions.click())

        Espresso.onView(ViewMatchers.withId(R.id.searchEditText))
            .perform(ViewActions.replaceText(TEST_EDITTEXT_ALGOL), ViewActions.closeSoftKeyboard())

        Espresso.onView(ViewMatchers.withId(R.id.searchEditText))
            .perform(ViewActions.pressImeActionButton())

        Espresso.onView(ViewMatchers.withId(R.id.totalCountTextView))
            .check(ViewAssertions.matches(ViewMatchers.withText(TEST_NUMBER_OF_RESULTS_ALGOL)))
    }
}