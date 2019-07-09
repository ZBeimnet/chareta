package com.example.chareta.view

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chareta.adapter.ItemRecyclerAdapter
import com.example.chareta.MainActivity
import com.example.chareta.NavigationHost
import com.example.chareta.R
import com.example.chareta.data.model.Item
import com.example.chareta.data.model.ItemsWrapper
import com.example.chareta.repository.ScalarRepository
import com.example.chareta.viewmodel.BidViewModel
import com.example.chareta.viewmodel.ItemViewModel
import com.example.chareta.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.posted_item_fragment.view.*

@Suppress("PLUGIN_WARNING")
class PostedItemFragment: Fragment() {
lateinit var binding:com.example.chareta.databinding.PostedItemFragmentBinding
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
        val viewModel = ViewModelProviders.of(this).get(ItemViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater,R.layout.posted_item_fragment,container,false)
        binding.PostedItem=viewModel
        binding.executePendingBindings()
        return binding.root

        val activity = activity as MainActivity?
        activity?.hideBottomBar(false)

        val isConnected = activity?.connected()

        (activity as AppCompatActivity).setSupportActionBar(view.app_bar)

        recyclerView = view.recycler_view
        recyclerView.layoutManager = LinearLayoutManager(requireContext()) as RecyclerView.LayoutManager?
        recyclerView.setHasFixedSize(true)
//        val adapter = ItemRecyclerAdapter()
//        recyclerView.adapter = adapter
        val scalarRepository = ScalarRepository()

        if(isConnected!!) {
//            scalarRepository.addBelongingToItem( "Http://localhost:8080/users/2" , 5)
            itemViewModel.getItems()
            itemViewModel.getResponses.observe(this, Observer {
//                adapter.setData(it)
                recyclerView.adapter =
                    ItemRecyclerAdapter(it.body()!!, activity.supportFragmentManager)
            })
        }

//        else {
//            itemViewModel.getItemsFromLocal().observe(this, Observer {
//                val items = ItemsWrapper(ItemsWrapper.ItemList(it))
//                recyclerView.adapter =
//                    ItemRecyclerAdapter(items, activity.supportFragmentManager)
//            })
//        }

      return view
    }

    override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        super.onCreateOptionsMenu(menu, menuInflater)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.post_item -> (activity as NavigationHost).navigateTo(CreatePostFragment(), true) // Navigate to the next Fragment
            R.id.logout -> (activity as NavigationHost).navigateTo(LoginFragment(), false)
        }

        return super.onOptionsItemSelected(item)
    }

}