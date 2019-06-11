package com.example.chareta.view

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.chareta.MainActivity
import com.example.chareta.NavigationHost
import com.example.chareta.R
import kotlinx.android.synthetic.main.posted_item_fragment.view.*

class YourBidsFragment: Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.your_bids_fragment, container, false)

        val activity = activity as MainActivity?
        activity?.hideBottomBar(false)

        (activity as AppCompatActivity).setSupportActionBar(view.app_bar)

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        super.onCreateOptionsMenu(menu, menuInflater)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.post_item -> (activity as NavigationHost).navigateTo(CreatePostFragment(), true)
            R.id.logout -> (activity as NavigationHost).navigateTo(LoginFragment(), false)// Navigate to the next Fragment
        }

        return super.onOptionsItemSelected(item)
    }


}
