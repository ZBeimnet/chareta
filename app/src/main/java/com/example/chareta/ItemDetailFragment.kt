package com.example.chareta

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_item_detail.view.*


class ItemDetailFragment : Fragment() {

    private var listener: OnFragmentInteractionListener? = null

    private lateinit var itemDetail: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_item_detail, container, false)

       itemDetail = view.item_detail
        itemDetail.text = arguments?.getLong("itemID", -2).toString()

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
