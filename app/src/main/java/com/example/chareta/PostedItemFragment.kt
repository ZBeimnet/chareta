package com.example.chareta

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chareta.data.Item
import com.example.chareta.repository.ScalarRepository
import com.example.chareta.viewmodel.ItemViewModel
import com.example.chareta.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.posted_item_fragment.view.*
import java.net.URI

@Suppress("PLUGIN_WARNING")
class PostedItemFragment: Fragment() {

    private lateinit var itemViewModel: ItemViewModel
    private lateinit var userViewModel: UserViewModel

    private lateinit var recyclerView: RecyclerView
    private lateinit var allItems: List<Item>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        itemViewModel = ViewModelProviders.of(this).get(ItemViewModel::class.java)
        userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.posted_item_fragment, container, false)
        val activity = activity as MainActivity?
        activity?.hideBottomBar(false)

        val isConnected = activity?.connected()

        (activity as AppCompatActivity).setSupportActionBar(view.app_bar)

        recyclerView = view.recycler_view
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)
//        val adapter = ItemRecyclerAdapter()
//        recyclerView.adapter = adapter


        val scalarRepository = ScalarRepository()

        if(isConnected!!) {
//            scalarRepository.addBelongingToItem( "Http://localhost:8080/users/2" , 5)
            itemViewModel.getAllItems().observe(this, Observer {
//                adapter.setData(it)
                recyclerView.adapter = ItemRecyclerAdapter(it, activity!!.supportFragmentManager)
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
            R.id.post_item -> (activity as NavigationHost).navigateTo(CreatePostFragment(), true) // Navigate to the next Fragment
            R.id.logout -> (activity as NavigationHost).navigateTo(com.example.chareta.LoginFragment(), true)
        }

        return super.onOptionsItemSelected(item)
    }

}