package com.example.chareta

import android.view.Gravity
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.DrawerActions
import androidx.test.espresso.contrib.DrawerMatchers
import androidx.test.espresso.contrib.NavigationViewActions
import androidx.test.espresso.matcher.ViewMatchers
import com.example.chareta.ServiceLocater.ServiceLocator
import com.example.chareta.repository.ItemRepository
import org.junit.After
import org.junit.Before
import org.junit.Test
class AppNavigationTest {
    private lateinit var itemsRepository: ItemRepository

// An Idling Resource that waits for Data Binding to have no pending bindings
//private val dataBindingIdlingResource = DataBindingIdlingResource()

    @Before
    fun init() {
        //ItemRepository = ServiceLocator.provideItemsRepository(getApplicationContext())
    }

    @After
    fun reset() {
        ServiceLocator.resetRepository()
    }

    @Test
    fun navigateFromPostedItemToStatus() {
        // start up Tasks screen
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        //dataBindingIdlingResource.monitorActivity(activityScenario)

        Espresso.onView(ViewMatchers.withId(R.id.bottom_bids))
            .check(ViewAssertions.matches(DrawerMatchers.isClosed(Gravity.START))) // Left Drawer should be closed.
            .perform(DrawerActions.open()) // Open Drawer

        Espresso.onView(ViewMatchers.withId(R.id.bottom_manage))
            .perform(NavigationViewActions.navigateTo(R.layout.posted_item_fragment))


        Espresso.onView(ViewMatchers.withId(R.layout.register_fragment))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(ViewMatchers.withId(R.layout.status_fragment))
            .check(ViewAssertions.matches(DrawerMatchers.isClosed(Gravity.START))) // Left Drawer should be closed.
            .perform(DrawerActions.open()) // Open Drawer


        Espresso.onView(ViewMatchers.withId(R.id.post_item))
            .perform(NavigationViewActions.navigateTo(R.layout.posted_item_fragment))

        // Check that tasks screen was opened.
        Espresso.onView(ViewMatchers.withId(R.layout.status_fragment))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun postedItemScreen_clickOnAddFavButton_OpensAddItemScreen() {
        // start up Tasks screen
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        //dataBindingIdlingResource.monitorActivity(activityScenario)


        Espresso.onView(ViewMatchers.withId(R.layout.posted_item_fragment))
            .check(ViewAssertions.matches(DrawerMatchers.isClosed(Gravity.START))) // Left Drawer should be closed.

        // Open Drawer
        Espresso.onView(
            ViewMatchers.withId(R.layout.create_post_fragment)

        ).perform(ViewActions.click())

        // Check if  is open
        Espresso.onView(ViewMatchers.withId(R.layout.create_post_fragment))
            .check(ViewAssertions.matches(DrawerMatchers.isOpen(Gravity.START))) // Left drawer is open open.
    }

    @Test
    fun itemsDetailScreen_UIBackButton() {

//
//        // start up Tasks screen
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
//        //dataBindingIdlingResource.monitorActivity(activityScenario)
//
//        // Click on the task on the list
        Espresso.onView(ViewMatchers.withText("back")).perform(ViewActions.click())
//
       Espresso.onView(ViewMatchers.withId(R.id.)).perform(ViewActions.click())
//
//        // Confirm that if we click "<-" once, we end up back at the task details page
       Espresso.onView(
            ViewMatchers.withContentDescription(
                activityScenario.
            )
        ).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.layout.posted_item_fragment))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
//
//        // Confirm that if we click "<-" a second time, we end up back at the home screen
        Espresso.onView(
            ViewMatchers.withContentDescription(
                activityScenario
                    .getToolbarNavigationContentDescription()
            )
        ).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.layout.posted_item_fragment))
           .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

