package com.example.chareta.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.chareta.MainActivity
import com.example.chareta.NavigationHost
import com.example.chareta.R
import com.example.chareta.data.remote.model.User
import com.example.chareta.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.register_fragment.view.*

class RegisterFragment: Fragment() {

    private lateinit var userViewModel: UserViewModel

    private lateinit var userNameEditText: EditText
    private lateinit var phoneEditText: EditText
    private lateinit var addressEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var confirmEditText: EditText
    private lateinit var registrationConfirmation: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)

    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.register_fragment, container, false)

        val activity = activity as MainActivity?
        activity?.hideBottomBar(true)

        val isConnected = activity?.connected()

        /////
        userNameEditText = view.register_username_edit_text
        phoneEditText = view.register_phone_edit_text
        addressEditText = view.register_address_edit_text
        passwordEditText = view.register_password_edit_text
        confirmEditText = view.confirm_password_edit_text
        registrationConfirmation = view.register_confirmation_text_view
        /////
        registrationConfirmation.text = ""

        view.back_button.setOnClickListener {
            (activity as NavigationHost).navigateTo(LoginFragment(), false) // Navigate to the next Fragment
        }

        view.register_button.setOnClickListener {

//            userViewModel.registerUser(readFields())
//            clearFields()
//            registrationConfirmation.text = "Successfully Registered!"

            if(isConnected!!) {
                userViewModel.registerUser(readFields())
                clearFields()
                registrationConfirmation.text = "Successfully Registered!"
            }
            else {
                registrationConfirmation.text = "Not connected to a network!"
            }

        }


        return view
    }//


    private fun readFields() = User(
        0,
        userNameEditText.text.toString(),
        phoneEditText.text.toString(),
        addressEditText.text.toString(),
        passwordEditText.text.toString()
    )

    private fun clearFields() {
        userNameEditText.setText("")
        phoneEditText.setText("")
        addressEditText.setText("")
        passwordEditText.setText("")
        confirmEditText.setText("")
    }
}