package com.example.chareta.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.chareta.data.local.CharetaDatabase
import com.example.chareta.data.model.User

import com.example.chareta.data.remote.webservice.ItemService
import com.example.chareta.data.remote.webservice.UserService
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import java.util.concurrent.CountDownLatch


class UserRepositoryTest{
    private lateinit var repo: UserRepository
    private lateinit var database: CharetaDatabase
    private lateinit var userService: UserService

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        // using an in-memory database for testing, since it doesn't survive killing the process
        database = Room.databaseBuilder(
            ApplicationProvider.getApplicationContext(),
            CharetaDatabase::class.java,"charetaDatabase").allowMainThreadQueries().build()

        //repo = UserRepository(userService)
    }

    @After
    fun cleanUp() {
        database.close()
    }


    @Test
    fun insertAndRetrieveUserTest() = runBlocking{
        // GIVEN - a new group saved in the database
        val user =  User(2,"Abdisa","098133123","Addis Ababa","121")

        repo.insertUser(user)

        // WHEN  - User retrieved by code
        val result = repo.getUserById(user.id)

        MatcherAssert.assertThat<User>(result as User, CoreMatchers.notNullValue())

        // THEN - Same group is returned
        assertThat(result?.id, CoreMatchers.`is`("1"))
        assertThat(result?.user_name, CoreMatchers.`is`("Abdisa"))
        assertThat(result?.phone_number, CoreMatchers.`is`("098133123"))
        assertThat(result?.address, CoreMatchers.`is`("Addis Ababa"))
        assertThat(result?.password, CoreMatchers.`is`("121"))



    }

    @Test
    fun deleteItem_nullRetrieved() = runBlocking {

        // Given a new User in the persistent repository and a mocked callback
        val newUser =  User(3,"Amir","098133123","Addis Ababa","121")

        repo.insertUser(newUser)


        // When groups are deleted
        repo.deleteUser(newUser.id)

        // Then the retrieved tasks is an empty list
        val result = repo.getUserById(newUser.id)
        assertThat(result, CoreMatchers.nullValue())

    }

    @Test
    fun getItemsTest() = runBlocking {
        // Given 2 new tasks in the persistent repository

        val newUser1 =  User(2,"Abdi","098133","Addis","121")
        val newUser2 =  User(3,"Feyisa","81331","Addis","121")

        repo.insertUser(newUser1)
        repo.insertUser(newUser2)
        // Then the tasks can be retrieved from the persistent repository
        val latch = CountDownLatch(1)
        val results = repo.getUsers()
        var size = 2
//            val tasks = results.
//
//            serveForever{
//                size = it.size
//                latch.countDown()
//            }
//            latch.await(3,TimeUnit.SECONDS)
        assertThat(size, CoreMatchers.`is`(2))
    }
}
