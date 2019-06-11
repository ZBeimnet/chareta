package com.example.chareta.repository

import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

//class ItemRepositoryTest {
//
//    @Before
//    fun setUp() {
//    }
//
//    @After
//    fun tearDown() {
//    }
//
//    @Test
//    fun getAllItemsAsync() {
//    }
//
//    @Test
//    fun getItemByIdAsync() {
//    }
//
//    @Test
//    fun addItemAsync() {
//    }
//
//    @Test
//    fun deleteItemAsync() {
//    }
    import android.app.PendingIntent.getActivity
import android.content.ClipData
import android.util.Log
    import androidx.arch.core.executor.testing.InstantTaskExecutorRule
    import androidx.lifecycle.Observer
    import androidx.room.Room
    import androidx.test.core.app.ApplicationProvider
    import androidx.test.ext.junit.runners.AndroidJUnit4
    import androidx.test.filters.MediumTest

    import kotlinx.coroutines.Dispatchers
    import kotlinx.coroutines.ExperimentalCoroutinesApi
    import kotlinx.coroutines.runBlocking
    import kotlinx.coroutines.test.runBlockingTest
    import org.hamcrest.CoreMatchers
    import org.junit.*
    import org.junit.runner.RunWith
    import java.util.concurrent.CountDownLatch
    import java.util.concurrent.TimeUnit

    @ExperimentalCoroutinesApi
    @RunWith(AndroidJUnit4::class)
    @MediumTest

    class CareerGroupRepositoryTest {

        private lateinit var repo: ItemRepository
        private lateinit var database: CharetaDatabase

        // Executes each task synchronously using Architecture Components.
        @get:Rule
        var instantExecutorRule = InstantTaskExecutorRule()

        @Before
        fun setup() {
            // using an in-memory database for testing, since it doesn't survive killing the process
            database = Room.databaseBuilder(
                ApplicationProvider.getApplicationContext(),
                CharetaDatabase::class.java,
                "chareta_db").allowMainThreadQueries().build()

            repo = ItemRepository(database.groupDao())
        }

        @After
        fun cleanUp() {
            database.close()
        }
        @Test
        fun insertAndRetrieve()= runBlocking{
            // GIVEN - a new group saved in the database
            val user = Item("1","Abebe","098123123","Addis Ababa","121a")
            repo.registerUser(user)

            // WHEN  - Group retrieved by code
            val result = repo.getUserById(user.groupCode)

            Assert.assertThat(result, CoreMatchers.notNullValue())

            // THEN - Same group is returned
            Assert.assertThat(result.groupName, CoreMatchers.`is`(""))
            Assert.assertThat(result.description, CoreMatchers.`is`("Interest in financial and investment planning and management, and providing banking and insurance services."))
            Assert.assertThat(result.occupations, CoreMatchers.`is`("Accounting Clerk, Appraiser"))

        }

        @Test
        fun deleteGroup_nullRetrievedGroup() = runBlockingTest {

            // Given a new User in the persistent repository and a mocked callback
            val newUser = Item("1","Abebe","098123123","Addis Ababa","121a")
            repo.registerUser(newUser)


            // When groups are deleted
            repo.deleteUser(newUser)

            // Then the retrieved tasks is an empty list
            val result = repo.getUserById(newUser.groupCode)
            Assert.assertThat(result, CoreMatchers.nullValue())

        }

        @Test
        fun getGroups_retrieveGroups() = runBlockingTest {
            // Given 2 new tasks in the persistent repository

            val newUser1 =  Item("2","Abdisa","098133123","Addis Ababa","121")
            val newUser1 =  Item("3","Feyisa","8133123","Addis Ababa","121")

            repo.registerUser(newUser1)
            repo.registerUser(newUser2)
            // Then the tasks can be retrieved from the persistent repository
            val latch = CountDownLatch(1)
            val results = repo.getAllItemsAsync()
            var size = 0
            val tasks = results.ob

            serveForever{
                size = it.size
                latch.countDown()
            }
            latch.await(3,TimeUnit.SECONDS)
            Assert.assertThat(size, CoreMatchers.`is`(2))
        }






    }
}