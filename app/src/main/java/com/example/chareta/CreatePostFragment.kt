package com.example.chareta

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.create_post_fragment.view.*

class CreatePostFragment: Fragment() {
lateinit var backbtn:Button
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.create_post_fragment, container, false)
        val activity = activity as MainActivity?
        activity?.hideBottomBar(true)
        backbtn = view.back_btn
        backbtn.setOnClickListener {
            (activity as NavigationHost).navigateTo(PostedItemFragment(), true)
        }
        return view
    }
}