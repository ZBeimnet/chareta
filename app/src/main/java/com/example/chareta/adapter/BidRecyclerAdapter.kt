package com.example.chareta.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.chareta.R
import com.example.chareta.data.model.Bid
import com.example.chareta.viewmodel.BidViewModel

class BidRecyclerAdapter(private var allBids: List<Bid>, private var bidViewModel: BidViewModel):
    RecyclerView.Adapter<BidRecyclerAdapter.BidViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BidViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val recyclerViewItem = inflater.inflate(R.layout.bids_card_view, parent, false)
        return BidViewHolder(recyclerViewItem)
    }

    override fun getItemCount(): Int {
        return allBids.size
    }

    override fun onBindViewHolder(holder: BidViewHolder, position: Int) {
        val bid = allBids[position]
        val amount = "Bid Amount: " + bid.bid_amount.toString()
        val date = "Bid date: " + bid.bid_date
        holder.bidAmount.text = amount
        holder.bidDate.text = date

        holder.deleteButton.setOnClickListener {
            bidViewModel.deleteItem(bid.id)
        }
    }

    class BidViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var bidAmount: TextView = itemView.findViewById(R.id.bid_amount_bids)
        var bidDate: TextView = itemView.findViewById(R.id.bid_date_bids)
        val deleteButton: Button = itemView.findViewById(R.id.withdraw_button_bids)
    }

}