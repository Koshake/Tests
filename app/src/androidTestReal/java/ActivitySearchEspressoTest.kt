import android.view.View
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import com.geekbrains.tests.*
import com.geekbrains.tests.TEST_DELAY_MS
import com.geekbrains.tests.TEST_EDITTEXT_ALGOL
import com.geekbrains.tests.TEST_NUMBER_OF_RESULTS_ALGOL
import com.geekbrains.tests.view.search.MainActivity
import org.hamcrest.Matcher
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

        Espresso.onView(ViewMatchers.isRoot()).perform(delay())
        Espresso.onView(ViewMatchers.withId(R.id.totalCountTextView))
            .check(ViewAssertions.matches(ViewMatchers.withText(TEST_NUMBER_OF_RESULTS_ALGOL_REAL)))
    }

    private fun delay(): ViewAction? {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> = ViewMatchers.isRoot()
            override fun getDescription(): String = "wait for $10 seconds"
            override fun perform(uiController: UiController, v: View?) {
                uiController.loopMainThreadForAtLeast(TEST_DELAY_MS)
            }
        }
    }
}