package com.example.chareta

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.register_fragment.view.*

class RegisterFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.register_fragment, container, false)

        val activity = activity as MainActivity?
        activity?.hideBottomBar(true)

        view.back_button.setOnClickListener {
            (activity as NavigationHost).navigateTo(LoginFragment(), false) // Navigate to the next Fragment
        }

        view.register_button.setOnClickListener {

        }

        return view
    }
}