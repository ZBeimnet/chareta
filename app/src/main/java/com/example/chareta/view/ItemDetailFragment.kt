package com.example.chareta.view

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.chareta.MainActivity
import com.example.chareta.NavigationHost
import com.example.chareta.R
import kotlinx.android.synthetic.main.fragment_item_detail.view.*



class ItemDetailFragment : Fragment() {

    private var listener: OnFragmentInteractionListener? = null

    private lateinit var itemDetail: TextView
    lateinit var itemname:TextView
    lateinit var bidbtn: Button
    lateinit var backbtn:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_item_detail, container, false)
       itemname = view.itemname_textview

       itemDetail = view.item_description
        bidbtn = view.Bid_btn
        backbtn = view.cancel_btn
        itemDetail.text = arguments?.getLong("itemID", -2).toString()
         backbtn.setOnClickListener {
             (activity as NavigationHost).navigateTo(PostedItemFragment(), false)
         }
        bidbtn.setOnClickListener {

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
            args.putLong("itemID",itemID)
            itemDetailFragment.arguments = args
            return itemDetailFragment
        }

    }
}
