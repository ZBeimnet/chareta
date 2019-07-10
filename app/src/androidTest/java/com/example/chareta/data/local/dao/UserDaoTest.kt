package com.example.chareta.data.local.dao

import android.content.Context
import androidx.room.Room
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import com.example.chareta.data.local.CharetaDatabase
import com.example.chareta.data.model.User

import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
class UserDaoTest {
    private lateinit var database: CharetaDatabase

    // Set the main coroutines dispatcher for unit testing.
    //@ExperimentalCoroutinesApi
    //@get:Rule
    //var mainCoroutineRule = MainCoroutineRule()

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun initDb() {
        // using an in-memory database because the information stored here disappears when the
        // process is killed
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            CharetaDatabase::class.java
        ).allowMainThreadQueries().build()
    }

    @After
    fun closeDb() = database.close()

    @Test
    fun insertUserTest(){

        val user = User(1, "beshir", "0913865598" ,"Addis Ababa","beshir")
        database.userDao().insertUser(user)

        // WHEN - Get the task by id from the database
        val loaded = database.userDao().getUserById(user.id)

        // THEN - The loaded data contains the expected values
        MatcherAssert.assertThat<User>(loaded as User, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(loaded.id, CoreMatchers.`is`(user.id))
        MatcherAssert.assertThat(loaded.user_name, CoreMatchers.`is`(user.user_name))
        MatcherAssert.assertThat(loaded.phone_number, CoreMatchers.`is`(user.user_name))
        MatcherAssert.assertThat(loaded.address, CoreMatchers.`is`(user.password))

    }

    @Test
    fun insertUuserReplacesOnConflict() {
        // Given that a task is inserted
        val user = User(1, "beshir", "0913865598" ,"Addis Ababa","beshir")
        database.userDao().insertUser(user)

        // When a task with the same id is inserted
        val newUser = User(2, "bereket", "0936865598" ,"Addis Ababa","bereket")
        database.userDao().insertUser(newUser)

        // THEN - The loaded data contains the expected values
        val loaded = database.userDao().getUserById(user.id)
        MatcherAssert.assertThat<User>(loaded as User, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(loaded?.id, CoreMatchers.`is`(user.id))
        MatcherAssert.assertThat(loaded?.user_name, CoreMatchers.`is`("bereket"))
        MatcherAssert.assertThat(loaded?.phone_number, CoreMatchers.`is`("0936865598"))
        MatcherAssert.assertThat(loaded?.password, CoreMatchers.`is`("bereket"))
        MatcherAssert.assertThat(loaded?.address, CoreMatchers.`is`("AddisAbaba"))
    }



    @Test
    fun updateBidAndGetById(){
        // When inserting a task
        val originalUser = User(1, "beshir", "0913865598" ,"Addis Ababa","beshir")
        database.userDao().insertUser(originalUser)

        // When the task is updated
        val updatedUser = User(1, "bemnet", "0913865598" ,"Addis Ababa","beshir")
        database.userDao().insertUser(updatedUser)

        // THEN - The loaded data contains the expected values
        val loaded = database.userDao().getUserById(originalUser.id)
        MatcherAssert.assertThat<User>(loaded as User, CoreMatchers.notNullValue())

        MatcherAssert.assertThat(loaded?.id, CoreMatchers.`is`(originalUser.id))
        MatcherAssert.assertThat(loaded?.user_name, CoreMatchers.`is`("bemnet"))
        MatcherAssert.assertThat(loaded?.phone_number, CoreMatchers.`is`("0913865598"))
        MatcherAssert.assertThat(loaded?.password, CoreMatchers.`is`("beshir"))
        MatcherAssert.assertThat(loaded?.address, CoreMatchers.`is`("AddisAbaba"))
    }



    @Test
    fun deleteBidById(){
        // Given a task inserted
        val user =  User(4, "bemnet", "0913865598" ,"Addis Ababa","beshir")
        database.userDao().insertUser(user)

        // When deleting a task by id
        database.userDao().deleteUserById(user.id)

        // THEN - The list is empty
        val users = database.userDao().getUserById(user.id)
        //MatcherAssert.assertThat(users.isEmpty(), CoreMatchers.`is`(true))
        assertNull(users)
    }

}
