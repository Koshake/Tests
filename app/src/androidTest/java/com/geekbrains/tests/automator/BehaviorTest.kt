package com.geekbrains.tests.automator

import android.content.Context
import android.content.Intent
import android.view.KeyEvent
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SdkSuppress
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.*
import com.geekbrains.tests.*
import com.geekbrains.tests.TEST_NUMBER_OF_RESULTS_ALGOL
import com.geekbrains.tests.TEST_NUMBER_OF_RESULTS_PLUS_1
import com.geekbrains.tests.TEST_NUMBER_OF_RESULTS_ZERO
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SdkSuppress(minSdkVersion = 18)
class BehaviorTest {

    private val uiDevice: UiDevice =
        UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

    private val context = ApplicationProvider.getApplicationContext<Context>()

    private val packageName = context.packageName

    @Before
    fun setup() {

        uiDevice.pressHome()

        val intent = context.packageManager.getLaunchIntentForPackage(packageName)

        intent!!.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        context.startActivity(intent)

        uiDevice.wait(Until.hasObject(By.pkg(packageName).depth(0)), TIMEOUT)
    }

    @Test
    fun test_MainActivityIsStarted() {

        val editText = uiDevice.findObject(By.res(packageName, "searchEditText"))

        Assert.assertNotNull(editText)
    }

    @Test
    fun test_TotalCountTextViewIsEmpty() {

        val totalCountTextView = uiDevice.findObject(By.res(packageName, "totalCountTextView"))

        Assert.assertNull(totalCountTextView)

    }

    @Test
    fun test_SearchIsPositive() {
        val editText = uiDevice.findObject(By.res(packageName, "searchEditText"))

        editText.text = TEST_EDITTEXT_ALGOL

        val search = uiDevice.findObject(By.res(packageName, "search_button"))
        search.click()

        val changedText = uiDevice.wait(
            Until.findObject(By.res(packageName, "totalCountTextView")),
            TIMEOUT
        )

        Assert.assertEquals(changedText.text.toString(), TEST_NUMBER_OF_RESULTS_ALGOL)

    }

    @Test
    fun test_OpenDetailsScreen() {

        val toDetails: UiObject2 = uiDevice.findObject(
            By.res(
                packageName,
                "toDetailsActivityButton"
            )
        )

        toDetails.click()


        val changedText =
            uiDevice.wait(
                Until.findObject(By.res(packageName, "totalCountTextView")),
                TIMEOUT
            )

        Assert.assertEquals(changedText.text, TEST_NUMBER_OF_RESULTS_ZERO)
    }

    @Test
    fun test_DetailsShowsCounter() {
        val editText = uiDevice.findObject(By.res(packageName, "searchEditText"))

        editText.text = TEST_EDITTEXT_ALGOL

        val search = uiDevice.findObject(By.res(packageName, "search_button"))
        search.click()


        uiDevice.wait(
            Until.findObject(By.res(packageName, "totalCountTextView")),
            TIMEOUT
        )

        val toDetails: UiObject2 = uiDevice.findObject(
            By.res(
                packageName,
                "toDetailsActivityButton"
            )
        )

        toDetails.click()


        val changedText =
            uiDevice.wait(
                Until.findObject(By.res(packageName, "totalCountTextView")),
                TIMEOUT
            )

        Assert.assertEquals(changedText.text, TEST_NUMBER_OF_RESULTS_ALGOL)
    }

    @Test
    fun test_DetailsIncrement() {
        val toDetails: UiObject2 = uiDevice.findObject(
            By.res(
                packageName,
                "toDetailsActivityButton"
            )
        )

        toDetails.click()

        val incrementButton = uiDevice.wait(
            Until.findObject(By.res(packageName, "incrementButton")),
            TIMEOUT
        )

        incrementButton.click()

        val changedText =
            uiDevice.wait(
                Until.findObject(By.res(packageName, "totalCountTextView")),
                TIMEOUT
            )
        Assert.assertEquals(changedText.text, TEST_NUMBER_OF_RESULTS_PLUS_1)
    }

    @Test
    fun test_DetailsDecrement() {
        val toDetails: UiObject2 = uiDevice.findObject(
            By.res(
                packageName,
                "toDetailsActivityButton"
            )
        )

        toDetails.click()

        val decrementButton = uiDevice.wait(
            Until.findObject(By.res(packageName, "decrementButton")),
            TIMEOUT
        )

        decrementButton.click()

        val changedText =
            uiDevice.wait(
                Until.findObject(By.res(packageName, "totalCountTextView")),
                TIMEOUT
            )
        Assert.assertNotEquals(changedText.text, TEST_NUMBER_OF_RESULTS_ZERO)
    }

    @Test
    fun test_DetailsBackPress() {
        val editText = uiDevice.findObject(By.res(packageName, "searchEditText"))

        editText.text = TEST_EDITTEXT_ALGOL

        val search = uiDevice.findObject(By.res(packageName, "search_button"))
        search.click()


        val totalCountText = uiDevice.wait(
            Until.findObject(By.res(packageName, "totalCountTextView")),
            TIMEOUT
        )

        val toDetails: UiObject2 = uiDevice.findObject(
            By.res(
                packageName,
                "toDetailsActivityButton"
            )
        )

        toDetails.click()


        val changedText =
            uiDevice.wait(
                Until.findObject(By.res(packageName, "totalCountTextView")),
                TIMEOUT
            )

        Assert.assertEquals(changedText.text, TEST_NUMBER_OF_RESULTS_ALGOL)

        uiDevice.pressBack()

        Assert.assertEquals(totalCountText.text, TEST_NUMBER_OF_RESULTS_ALGOL)

        Assert.assertEquals(editText.text, TEST_EDITTEXT_ALGOL)

    }

    companion object {
        private const val TIMEOUT = 5000L
    }
}