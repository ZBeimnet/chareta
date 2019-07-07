package com.example.chareta.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.chareta.MainActivity
import com.example.chareta.NavigationHost
import com.example.chareta.R
import kotlinx.android.synthetic.main.login_fragment.view.*

class LoginFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.login_fragment, container, false)

        val activity = activity as MainActivity?
        activity?.hideBottomBar(true)

        view.login_button.setOnClickListener {
            (activity as NavigationHost).navigateTo(PostedItemFragment(), false)

        }
        view.click_here.setOnClickListener {
            (activity as NavigationHost).navigateTo(RegisterFragment(), true) // Navigate to the next Fragment
        }
        //val Username = view.username_input_editText

        //navigate to register fragment
        view.findViewById<Button>(R.id.click_here)?.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.registerFragment)


        )

        return view

    }
}