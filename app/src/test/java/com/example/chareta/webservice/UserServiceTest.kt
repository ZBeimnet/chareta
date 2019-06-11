package com.example.chareta.webservice



    import android.os.AsyncTask
    import androidx.lifecycle.LiveData
    import androidx.lifecycle.Observer
    import androidx.lifecycle.ViewModelProviders
    import androidx.room.Room
    import androidx.test.core.app.ApplicationProvider
    import androidx.test.ext.junit.runners.AndroidJUnit4
    import androidx.test.filters.SmallTest
    import com.example.chareta.PostedItemFragment
    import com.example.chareta.data.User
    import com.example.chareta.repository.UserRepository
    import com.example.chareta.viewmodel.ItemViewModel
    import com.example.chareta.viewmodel.UserViewModel
    import kotlinx.coroutines.ExperimentalCoroutinesApi
    import kotlinx.coroutines.runBlocking
    import kotlinx.coroutines.test.runBlockingTest
    import org.hamcrest.CoreMatchers
    import org.hamcrest.MatcherAssert
    import org.hamcrest.Matchers.instanceOf
    import org.hamcrest.Matchers.notNullValue
    import org.junit.After
    import org.junit.Before
    import org.junit.Rule
    import org.junit.Test
    import org.junit.runner.RunWith

    @ExperimentalCoroutinesApi
    @RunWith(AndroidJUnit4::class)
    @SmallTest
    class UserServiceTest {

        private lateinit var itemViewModel: ItemViewModel
        private lateinit var userViewModel: UserViewModel
        private lateinit var userRepository: UserRepository

        @Before
        fun initDb() {
            // using an in-memory database because the information stored here disappears when the
            // process is killed
            val userService = ServiceBuilder.buildService(UserService::class.java)
            userRepository = UserRepository(userService)

            userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)


        }



        @Test
        fun insertUserAndGetByCode() {
            // GIVEN - insert a User
            val user = User(1,"abebe",  "12341","Addis Ababa","123")


            userViewModel.registerUser(user)
            // WHEN - Get the group by code from the database

            val loaded = userViewModel.getUserById(user.id)
            // THEN - The loaded data contains the expected values
            MatcherAssert.assertThat<User>(loaded as User, CoreMatchers.notNullValue())
            MatcherAssert.assertThat(loaded.observe(this, Observer { it.id }), CoreMatchers.`is`(user.id))
            MatcherAssert.assertThat(loaded.observe(this, Observer { it.user_name }), CoreMatchers.`is`(user.user_name))
            MatcherAssert.assertThat(loaded.observe(this, Observer { it.phone_number }), CoreMatchers.`is`(user.phone_number))
            MatcherAssert.assertThat(loaded.observe(this, Observer { it.id }), CoreMatchers.`is`(user.id))
        }

        @Test
        fun updateGroupAndGetByCode() {
            // When inserting a tas
            val original = User(23,"beshir","090234234","addisabab","2313")

            userViewModel.registerUser(original)
            // When the task is updated
            val updated = User(23,"beshir","090234234","addisabab","2313")
           userViewModel.registerUser(updated)

            // THEN - The loaded data contains the expected values
            val loaded = userViewModel.getUserById(original.id)
            MatcherAssert.assertThat(updated?.id, CoreMatchers.`is`(updated.id))
            MatcherAssert.assertThat(updated?.user_name, CoreMatchers.`is`("beshir"))
            MatcherAssert.assertThat(updated?.phone_number, CoreMatchers.`is`("090234234"))
            MatcherAssert.assertThat(updated?.password, CoreMatchers.`is`("addisabab"))
        }

        @Test
        fun deleteUserByIdAndGet(){
            // Given a user inserted
            val user = User(23, "beshir","090234234","addisabab","2313" )
            userViewModel.registerUser(user)

            // When deleting a task by id
            userViewModel.deleteUser(user.id)

            // THEN - The object returned is null
            val getUser = userViewModel.getUserById(user.id)
            MatcherAssert.assertThat(getUser, CoreMatchers.nullValue())
        }
        @Test
        fun searchIdAndGet(){
            val user = User(23, "beshir","090234234","addisabab","2313" )
            userViewModel.registerUser(user)

            val result : LiveData<User> = userViewModel.getUserById(user.id)



            MatcherAssert.assertThat(result, notNullValue())


        }
        @Test
        fun getAllUsers() {
            val user = User(23, "beshir","090234234","addisabab","2313" )
            userViewModel.registerUser(user)

            val result = userViewModel.getUserById(user.id)

            MatcherAssert.assertThat(result, CoreMatchers.notNullValue())
        }





    }
