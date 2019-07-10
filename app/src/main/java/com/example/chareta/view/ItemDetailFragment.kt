package com.example.chareta.view

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.chareta.NavigationHost
import com.example.chareta.R
import com.example.chareta.data.model.Bid
import com.example.chareta.data.model.Item
import com.example.chareta.viewmodel.BidViewModel
import com.example.chareta.viewmodel.ItemViewModel
import com.example.chareta.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.item_detail_fragment.view.*
import java.util.*


class ItemDetailFragment : Fragment() {

    private var listener: OnFragmentInteractionListener? = null

    private lateinit var itemViewModel: ItemViewModel
    private lateinit var bidViewModel: BidViewModel

    private lateinit var itemDetail: TextView
    private lateinit var itemname: TextView
    private lateinit var startingPrice: TextView
    private lateinit var bidEditText: EditText
    private lateinit var bidbtn: Button
    private lateinit var backbtn: Button
    lateinit var binding: com.example.chareta.databinding.ItemDetailFragmentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        itemViewModel = ViewModelProviders.of(this).get(ItemViewModel::class.java)
        bidViewModel = ViewModelProviders.of(this).get(BidViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.item_detail_fragment, container, false)
        val viewModel = ViewModelProviders.of(this).get(BidViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.item_detail_fragment, container, false)
        binding.bidViewModel = viewModel
        binding.executePendingBindings()
        return binding.root
        itemname = view.itemname_textview
        itemDetail = view.item_description
        startingPrice = view.item_starting_price
        bidEditText = view.bid_edittext
        bidbtn = view.Bid_btn
        backbtn = view.cancel_btn

        val itemId: Long? = arguments?.getLong("itemID", -2)

        itemViewModel.getItemById(itemId!!)
        itemViewModel.getResponse.observe(this, Observer { response ->
            val item: Item = response.body()!!
            itemname.text = item.item_name
            startingPrice.text = item.starting_price.toString()
            itemDetail.text = item.item_description
        })

        backbtn.setOnClickListener {
            (activity as NavigationHost).navigateTo(PostedItemFragment(), false)
        }
        bidbtn.setOnClickListener {
            bidViewModel.insertBid(Bid(0, bidEditText.text.toString().toLong(), java.util.Date().toString()))
            Toast.makeText(context, "Bid added", Toast.LENGTH_LONG).show()
            bidEditText.setText("")
        }
        return view
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {

        fun newInstance(itemID: Long): ItemDetailFragment {
            val itemDetailFragment = ItemDetailFragment()
            val args = Bundle()
            args.putLong("itemID", itemID)
            itemDetailFragment.arguments = args
            return itemDetailFragment
        }

    }
}
