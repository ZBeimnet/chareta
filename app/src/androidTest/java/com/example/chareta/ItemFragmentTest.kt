package com.example.chareta

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.testing.launchFragment
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.LargeTest

import androidx.test.runner.AndroidJUnit4
import com.example.chareta.repository.ItemRepository
import com.example.chareta.view.PostedItemFragment
import com.example.chareta.viewmodel.ItemViewModel
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import java.util.regex.Pattern.matches

@RunWith(AndroidJUnit4::class)
        @LargeTest
class ItemFragmentTest {
    private lateinit var itemRepository : ItemRepository
    @Test
    fun clickAddItemButton_navigateToAddFragment() {
        // Given a user in the home screen
        val scenario = launchFragmentInContainer<PostedItemFragment>(Bundle(), R.style.AppTheme)
        val navController = mock(NavController::class.java)
        scenario.onFragment {
            Navigation.setViewNavController(it.view!!, navController)
        }

        // When the FAB is clicked
        onView(withId(R.id.post_item)).perform(click())

        // Then ve""rify that we navigate to the add screen
        verify(navController).navigate(
            PostedItemFragment.actionItemFragmentToPostItemFragment(
                null, getApplicationContext<Context>().getString(R.string.post_btn)))
    }
    @Test
    fun validItem_isSaved()  = runBlocking{
        // GIVEN - On the "Add Task" screen.
        val navController = mock(NavController::class.java)
        launchFragment(navController)

        // WHEN - Valid title and description combination and click save
        onView(withId(R.id.itemname_editext)).perform(replaceText("Item Name"))
        onView(withId(R.id.item_description_edit_text)).perform(replaceText("Item Description"))
        onView(withId(R.id.startingprice_Edittext)).perform(replaceText("starting Price"))
        onView(withId(R.id.expiry_date_textview)).perform(replaceText("Date"))
        onView(withId(R.id.post_button)).perform(click())



        // THEN - Verify that the repository saved the task
        val tasks = (itemRepository.getItems() as Success).data
        assertEquals(tasks.size, 1)
        assertEquals(tasks[0].ItemName, "ItemName")
        assertEquals(tasks[0].Itemdescription, "Item Description")
        assertEquals(tasks[0].StartingPrice, "Starting Price")
        assertEquals(tasks[0].expiryDate, "Date")
    }

}