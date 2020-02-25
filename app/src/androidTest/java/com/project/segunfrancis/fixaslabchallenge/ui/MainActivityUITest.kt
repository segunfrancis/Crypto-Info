package com.project.segunfrancis.fixaslabchallenge.ui

import android.content.Context
import android.net.ConnectivityManager
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.rule.ActivityTestRule
import com.project.segunfrancis.fixaslabchallenge.R
import org.junit.Rule
import org.junit.Test

/**
 * Created by SegunFrancis
 */
class MainActivityUITest {
    @get: Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java) // <- Launch activity
    val activityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun test_isActivityInView() {
        onView(withId(R.id.root)).check(matches(isDisplayed()))
    }

    @Test
    fun test_checkSnackbarIsDisplayedWithCorrectMessage(message: String) {
        Espresso.onView(withText(message))
            .check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }

    @Test
    fun test_snackbarVisible() {
        onView(withId(com.google.android.material.R.id.snackbar_text)).check(matches(withText(R.string.snackbar_error_message)))
    }

    @Test
    fun test_isRecyclerViewVisible() {
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))
    }

    @Test
    fun test_snackbarDisplayedWithMessage() {
        if (isThereNetworkConnection(activityTestRule.activity)) {

        }
    }

    private fun isThereNetworkConnection(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }
}