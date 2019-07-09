package com.example.chareta.view

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chareta.MainActivity
import com.example.chareta.NavigationHost
import com.example.chareta.R
import com.example.chareta.adapter.BidRecyclerAdapter
import com.example.chareta.viewmodel.BidViewModel
import com.example.chareta.viewmodel.ItemViewModel
import kotlinx.android.synthetic.main.posted_item_fragment.view.*
import kotlinx.android.synthetic.main.posted_item_fragment.view.app_bar
import kotlinx.android.synthetic.main.your_bids_fragment.view.*

class YourBidsFragment: Fragment() {
    private lateinit var recyclerView: RecyclerView

    private lateinit var bidViewModel: BidViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        bidViewModel = ViewModelProviders.of(this).get(BidViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.your_bids_fragment, container, false)

        val activity = activity as MainActivity?
        activity?.hideBottomBar(false)

        val isConnected = activity?.connected()
        (activity as AppCompatActivity).setSupportActionBar(view.app_bar)

        recyclerView = view.recycler_view_bids
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)

        if(isConnected!!) {
            bidViewModel.getBids()
            bidViewModel.getResponses.observe(this, Observer {
                recyclerView.adapter =
                    BidRecyclerAdapter(it.body()!!.embeddedBids.allBids, bidViewModel)
            })
        }

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
