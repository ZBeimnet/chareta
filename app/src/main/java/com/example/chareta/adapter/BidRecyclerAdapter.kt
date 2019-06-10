package com.example.chareta.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chareta.R
import com.example.chareta.data.remote.model.Bid

class BidRecyclerAdapter(private var allBids: List<Bid>, private var fm: FragmentManager):
    RecyclerView.Adapter<BidRecyclerAdapter.BidViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BidRecyclerAdapter.BidViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val recyclerViewItem = inflater.inflate(R.layout.bids_card_view, parent, false)
        return BidViewHolder(recyclerViewItem)
    }

    override fun getItemCount(): Int {
        return allBids.size
    }

    override fun onBindViewHolder(holder: BidRecyclerAdapter.BidViewHolder, position: Int) {
        val bid = allBids[position]
        holder.itemName.text = bid.bid_amount.toString()
        holder.startingPrice.text = bid.bid_date.toString()
    }

    class BidViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemName: TextView = itemView.findViewById(R.id.item_name_card_tv)
        var startingPrice: TextView = itemView.findViewById(R.id.starting_price_card_tv)
    }

}