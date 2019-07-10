package com.example.chareta


import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.runner.AndroidJUnit4
import com.example.chareta.data.model.Item
import com.example.chareta.repository.ItemRepository
import kotlinx.coroutines.runBlocking
import org.hamcrest.core.IsNot
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4)
class ItemEndToEndTest {
    private lateinit var repository : ItemRepository
    @Test
    fun PostItem() = runBlocking {
        repository.insertItem(Item(1, "Item Name","Item Description",700,"PostDate","ExpiryDate"))


        val activityScenario = ActivityScenario.launch(MainActivity::class.java)


        Espresso.onView(ViewMatchers.withId(R.id.post_item)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.itemname_editext)).perform(ViewActions.replaceText("Item Name"))
        Espresso.onView(ViewMatchers.withId(R.id.item_description_edit_text))
            .perform(ViewActions.replaceText("Item Description"))

        Espresso.onView(ViewMatchers.withId(R.id.startingprice_Edittext))
            .perform(ViewActions.replaceText(700.toString()))

        Espresso.onView(ViewMatchers.withId(R.id.expiry_date_textview))
            .perform(ViewActions.replaceText("Expiry Date"))

        Espresso.onView(ViewMatchers.withId(R.id.post_button)).perform(ViewActions.click())

        // Verify task is displayed on screen in the task list.
        Espresso.onView(ViewMatchers.withText("NEW TITLE")).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        // Verify previous task is not displayed
        Espresso.onView(ViewMatchers.withText("TITLE1")).check(ViewAssertions.doesNotExist())
    }

    @Test
    fun EditItem() = runBlocking {
        repository.insertItem(Item(1, "Item Name","Item Description",700,"PostDate","ExpiryDate"))


        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        Espresso.onView(ViewMatchers.withId(R.id.post_item)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.itemname_textview))
            .check(ViewAssertions.matches(ViewMatchers.withText("Item Name")))
        Espresso.onView(ViewMatchers.withId(R.id.item_description))
            .check(ViewAssertions.matches(ViewMatchers.withText("Item Description")))


        // Click on the edit button, edit, and save
        Espresso.onView(ViewMatchers.withId(R.id.post_item)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.itemname_editext)).perform(ViewActions.replaceText("Item Name"))
        Espresso.onView(ViewMatchers.withId(R.id.item_description_edit_text))
            .perform(ViewActions.replaceText("Item Description"))

        Espresso.onView(ViewMatchers.withId(R.id.startingprice_Edittext))
            .perform(ViewActions.replaceText(700.toString()))

        Espresso.onView(ViewMatchers.withId(R.id.expiry_date_textview))
            .perform(ViewActions.replaceText("Expiry Date"))

        Espresso.onView(ViewMatchers.withId(R.id.post_button)).perform(ViewActions.click())

        // Verify task is displayed on screen in the task list.
        Espresso.onView(ViewMatchers.withText("Item Name")).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        // Verify previous task is not displayed
        Espresso.onView(ViewMatchers.withText("Item Description")).check(ViewAssertions.doesNotExist())
    }


}