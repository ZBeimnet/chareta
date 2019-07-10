package com.example.chareta

import android.R
import android.service.autofill.Validators.not
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.EnumSet.allOf
import java.util.regex.Pattern.matches



@LargeTest

@RunWith(AndroidJUnit4::class)

class LoginFragmentTest {


    @RunWith(AndroidJUnit4::class)
    class LoginActivityTest {



//
//        private val username = "beshir"
//        private val password = "password"
//
//        @Test
//        fun clickLoginButton_opensLoginUi() {
//            onView(withId(R.id)).perform(ViewActions.typeText(username))
//            onView(withId(R.id.password_text_input)).perform(ViewActions.typeText(password))
//
//            onView(withId(R.id.button_login)).perform(ViewActions.scrollTo(), ViewActions.click())
//
//            Espresso.onView(withId(R.id.button_login))
//                .check(matches(withText("Success")))
//        }
//    }
    private lateinit var stringToBetypedInUsernameEditText: String
    private lateinit var stringToBetypedInPasswodEditText: String
    private lateinit var stringToBetyped: String

        @JvmField
    @get:Rule
    var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Before
    fun initValidString() {
        stringToBetyped = "Login Successfully"
        stringToBetypedInUsernameEditText = "Username"
        stringToBetypedInPasswodEditText = "Password"

//        @Test
//        fun testPasswordLengthRulesTriggersErrorMessage(){
//            onView(withId(R.id.password_edit_text)).perform(typeText("john"))
//            onView(withId(R.id.login_button)).perform(click())
//
//            onView(withId(R.id.error_message))
//                .check(matches(isDisplayed()))
//                .check(matches(withText("Password can not be less than 6")))
//        }
//        @Test
//        fun testValidPasswordDoesNotDisplayErrorMessage(){}
//        onView(withId(R.id.error_message))
//            .perform(typeText("Long valid password"))
//        onView(withId(R.id.login_button))
//            .perform(click())
//        onView(withId(R.id.error_message))
//            .check(matches(not(isDisplayed())))
        @Test

        fun loginBtnClicked_homeNavigation() {
            initValidString()
            onView(withId(R.i))
                .perform(typeText(this.stringToBetypedInUsernameEditText), closeSoftKeyboard())
            onView(withId(R.id.password_edit_text))
                .perform(typeText(stringToBetypedInPasswodEditText), closeSoftKeyboard())
            onView(withId(R.id.login_button)).perform(click())

            onView(withId(R.id.textview_for_login_success))
                .check(matches(withText(stringToBetyped)))
        }
        @Test
        fun testLoginFragmenNotRedirectToHomePage(){
            onView(withId(R.id.user_name_edit_text))
                .perform(typeText(stringToBetypedInUsernameEditText), closeSoftKeyboard())
                .check(matches(not("Username")))

            onView(withId(R.id.password_edit_text))
                .perform(typeText(stringToBetypedInPasswodEditText), closeSoftKeyboard())
                .check(matches(not("Password")))
            onView(withId(R.id.login_button))
                .perform(click())
            onView(withId(R.id.home_fragment))
                .check(matches(not(isDisplayed())))

        }
        @Test
        fun testLoginFragmenRedirectToHomePage(){
            onView(withId(R.id.us))
                .perform(typeText(stringToBetypedInUsernameEditText), closeSoftKeyboard())
                .check(matches(withText("Username")))

            onView(withId(R.id.password_edit_text))
                .perform(typeText(stringToBetypedInPasswodEditText), closeSoftKeyboard())
                .check(matches(withText("Password")))
            onView(withId(R.id.login_button))
                .perform(click())
            onView(withId(R.id.home_fragment))
                .check(matches(isDisplayed()))

        }
        @Test
        fun testSuccessMessageDisplayed(){
            onView(withId(R.id.user_name_edit_text))
                .perform(typeText(stringToBetypedInUsernameEditText), closeSoftKeyboard())
                .check(matches(withText("Username")))

            onView(withId(R.id.password_edit_text))
                .perform(typeText(stringToBetypedInPasswodEditText), closeSoftKeyboard())
                .check(matches(withText("Password")))
            onView(withId(R.id.login_button))
                .perform(click())
            onView(withId(R.id.textview_for_login_success))
                .check(matches(isDisplayed()))




        }



    }
