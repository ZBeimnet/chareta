package com.example.chareta

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chareta.data.Item
import com.example.chareta.data.ItemList
import com.example.chareta.viewmodel.ItemViewModel
import kotlinx.android.synthetic.main.posted_item_fragment.*
import kotlinx.android.synthetic.main.posted_item_fragment.view.*

@Suppress("PLUGIN_WARNING")
class PostedItemFragment: Fragment() {

    private lateinit var itemViewModel: ItemViewModel

    private lateinit var recyclerView: RecyclerView
    private lateinit var allItems: List<Item>
    private lateinit var itemNameTextView: TextView
    private lateinit var startingPriceTextView: TextView
    private lateinit var postedByTextView: TextView
    private lateinit var expiryDateTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        itemViewModel = ViewModelProviders.of(this).get(ItemViewModel::class.java)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.posted_item_fragment, container, false)

        val activity = activity as MainActivity?
        activity?.hideBottomBar(false)

        (activity as AppCompatActivity).setSupportActionBar(view.app_bar)

//        recyclerView = view.recycler_view
//        recyclerView.layoutManager = LinearLayoutManager(requireContext())
//        recyclerView.setHasFixedSize(true)
//
//
//
//        itemViewModel.getAllItems().observe(this, Observer<ItemList> {
//            recyclerView.adapter = ItemRecyclerAdapter(it)
//        })



        ////////

        itemNameTextView = view.item_name_text_view
        startingPriceTextView = view.starting_price_text_view
        postedByTextView = view.posted_by_text_view
        expiryDateTextView = view.expiry_date_text_view

        itemViewModel.getAllItems().observe(this, Observer {
            val item = it?.get(0)
            //Log.d("item_Name", item.item_name)
            itemNameTextView.text = item?.item_name
            startingPriceTextView.text = item?.starting_price.toString()
            expiryDateTextView.text = item?.expiry_date.toString()
        })

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        super.onCreateOptionsMenu(menu, menuInflater)
    }

}