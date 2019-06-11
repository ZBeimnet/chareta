package com.example.chareta

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.LargeTest
import androidx.test.runner.AndroidJUnit4
import org.junit.Before
import org.junit.runner.RunWith
import org.junit.Rule
import org.junit.Test
import androidx.test.rule.ActivityTestRule

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {
    @Rule
    val activityActivityTestRule = ActivityTestRule<MainActivity>(MainActivity::class.java)

    @Before
    fun init(){
        activityActivityTestRule.getActivity()
            .getSupportFragmentManager().beginTransaction();
    }

    @Test
    fun TestAutoComplete() {
    }
    onView(withId(R.id.bottom_posted))
}