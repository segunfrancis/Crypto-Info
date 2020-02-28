package com.project.segunfrancis.fixaslabchallenge.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.project.segunfrancis.fixaslabchallenge.R
import org.junit.Rule
import org.junit.Test

/**
 * Created by SegunFrancis
 */
class MainActivityUITest {
    @get: Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java) // <- Launch activity

    @Test
    fun test_isActivityInView() {
        onView(withId(R.id.root)).check(matches(isDisplayed()))
    }

    @Test
    fun test_isRecyclerViewVisible() {
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))
    }
}