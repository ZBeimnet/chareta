package com.example.chareta.webservice


    import androidx.lifecycle.LiveData
    import androidx.lifecycle.Observer
    import androidx.lifecycle.ViewModelProviders
    import androidx.test.filters.SmallTest


    import com.example.chareta.data.model.User
    import com.example.chareta.data.remote.ServiceBuilder

    import com.example.chareta.data.remote.webservice.UserService
    import com.example.chareta.repository.UserRepository
    import com.example.chareta.viewmodel.ItemViewModel
    import com.example.chareta.viewmodel.UserViewModel
    import junit.framework.Assert.assertEquals
    import kotlinx.coroutines.ExperimentalCoroutinesApi

    import org.hamcrest.CoreMatchers
    import org.hamcrest.MatcherAssert
    import org.hamcrest.Matchers.notNullValue
    import org.junit.Before
    import org.junit.Test
    import org.junit.runner.RunWith

    @ExperimentalCoroutinesApi
    //@RunWith(AndroidJUnit4::class)
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
//            userRepository = UserRepository(userService)




        }



        @Test
        fun insertUserAndGetByCode() {
            // GIVEN - insert a User
            val user = User(1,"abebe",  "12341","Addis Ababa","123")


            userViewModel.insertUser(user)
            // WHEN - Get the group by code from the database

            val loaded = userViewModel.getUserById(user.id)
            // THEN - The loaded data contains the expected values
            MatcherAssert.assertThat<User>(loaded as User, CoreMatchers.notNullValue())
            assertEquals(loaded.id,CoreMatchers.`is`(user.user_name))
            assertEquals(loaded.user_name, CoreMatchers.`is`(user.user_name))
            assertEquals(loaded.phone_number, CoreMatchers.`is`(user.phone_number))
            MatcherAssert.assertThat(loaded.address, CoreMatchers.`is`(user.address))
        }

        @Test
        fun updatUserAndGetByCode() {
            // When inserting a tas
            val original = User(23,"beshir","090234234","addisabab","2313")

            userViewModel.insertUser(original)
            // When the task is updated
            val updated = User(23,"beshir","090234234","addisabab","2313")
           userViewModel.updateUser(original.id,updated)

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
            userViewModel.insertUser(user)

            // When deleting a task by id
            userViewModel.deleteUser(user.id)

            // THEN - The object returned is null
            val getUser = userViewModel.getUserById(user.id)
            MatcherAssert.assertThat(getUser, CoreMatchers.nullValue())
        }
        @Test
        fun GetUserTest(){
            val user = User(23, "beshir","090234234","addisabab","2313" )
            userViewModel.getUserById(user.id)

            val result = userViewModel.getUserById(user.id)



            MatcherAssert.assertThat(result, notNullValue())


        }
        @Test
        fun getAllUsers() {
            val user = User(23, "beshir","090234234","addisabab","2313" )
            userViewModel.getUsers()

            val result = userViewModel.getUserById(user.id)

            MatcherAssert.assertThat(result, CoreMatchers.notNullValue())
        }





    }
