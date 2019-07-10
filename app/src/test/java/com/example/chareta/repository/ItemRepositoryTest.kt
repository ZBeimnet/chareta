package com.example.chareta.repository

import org.junit.After
import org.junit.Before
import org.junit.Test
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.MediumTest
import com.example.chareta.data.local.CharetaDatabase
import com.example.chareta.data.model.Item

import com.example.chareta.data.remote.webservice.ItemService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.*
import org.junit.runner.RunWith
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

@ExperimentalCoroutinesApi
//@RunWith(AndroidJUnit4::class)
@MediumTest
class ItemRepositoryTest {

        private lateinit var repo: ItemRepository
        private lateinit var database: CharetaDatabase
        private lateinit var itemService: ItemService

        // Executes each task synchronously using Architecture Components.
        @get:Rule
        var instantExecutorRule = InstantTaskExecutorRule()

        @Before
        fun setup() {
            // using an in-memory database for testing, since it doesn't survive killing the process
            database = Room.databaseBuilder(
                ApplicationProvider.getApplicationContext(),
                CharetaDatabase::class.java,"charetaDatabase").allowMainThreadQueries().build()

            //repo = ItemRepository(itemService)
        }

        @After
        fun cleanUp() {
            database.close()
        }
        @Test
        fun insertAndRetrieveItemTest() = runBlocking{
            // GIVEN - a new group saved in the database
            val item = Item(1,"Item Name", "Description", 7000,"Post Date", "Expiry Date")

            repo.insertItem(item)

            // WHEN  - Group retrieved by code
            val result = repo.getItemById(item.id)

            MatcherAssert.assertThat<Item>(result as Item, CoreMatchers.notNullValue())

            // THEN - Same group is returned
            Assert.assertThat(result?.id, CoreMatchers.`is`("1"))
            Assert.assertThat(result?.item_name, CoreMatchers.`is`("Item Name"))
            Assert.assertThat(result?.item_description, CoreMatchers.`is`("Description"))

        }

        @Test
        fun deleteItem_nullRetrieved() = runBlocking {

            // Given a new User in the persistent repository and a mocked callback
            val newItem = Item(1,"Item Name", "Description", 7000,"Post Date","Expiry Date")
                repo.insertItem(newItem)


            // When groups are deleted
            repo.deleteItem(newItem.id)

            // Then the retrieved tasks is an empty list
            val result = repo.getItemById(newItem.id)
            Assert.assertThat(result, CoreMatchers.nullValue())

        }

        @Test
        fun getItemsTest() = runBlocking {
            // Given 2 new tasks in the persistent repository

            //val newUser1 =  Item("2","Abdisa","098133123","Addis Ababa","121")
            //val newUser1 =  Item("3","Feyisa","8133123","Addis Ababa","121")

            val newItem1 = Item(1,"Item Name", "Description", 7000,"Post Date","Expiry Date")
            val newItem2 = Item(2,"Item Name Two ", "Description Two", 9000,"Post Date 2","Expiry Date")
            repo.insertItem(newItem1)
            repo.insertItem(newItem2)
            // Then the tasks can be retrieved from the persistent repository
            val latch = CountDownLatch(1)
            val results = repo.getItems()
            var size = 2
//            val tasks = results.
//
//            serveForever{
//                size = it.size
//                latch.countDown()
//            }
//            latch.await(3,TimeUnit.SECONDS)
            Assert.assertThat(size, CoreMatchers.`is`(2))
        }


}