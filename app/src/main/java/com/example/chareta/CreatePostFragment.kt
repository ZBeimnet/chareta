package com.example.chareta

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.create_post_fragment.view.*

class CreatePostFragment: Fragment() {
    lateinit var itemname:EditText
    lateinit var itemdescription:EditText
    lateinit var startingprice:EditText
    lateinit var posteddate:EditText
    lateinit var expirydate:EditText
lateinit var backbtn:Button
    lateinit var postbtn:Button
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.create_post_fragment, container, false)
        val activity = activity as MainActivity?
        activity?.hideBottomBar(true)
        itemname = view.itemname_editext
        itemdescription = view.itemdescription_edittext
        startingprice = view.startingprice_Edittext
        posteddate = view.post_date_edit_text
        expirydate = view.exipray_date_password_edit_text
        postbtn = view.post_button
        backbtn = view.back_btn
        backbtn.setOnClickListener {
            (activity as NavigationHost).navigateTo(PostedItemFragment(), true)
        }
        postbtn.setOnClickListener {

        }
        return view
    }
}