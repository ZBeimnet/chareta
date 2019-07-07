package com.example.chareta.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chareta.R
import com.example.chareta.data.remote.model.Item
import com.example.chareta.data.remote.model.ItemsWrapper

class ManageRecyclerAdapter(private var allItems: ItemsWrapper, private var fm: FragmentManager):
    RecyclerView.Adapter<ManageRecyclerAdapter.ManageViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ManageViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val recyclerViewItem = inflater.inflate(R.layout.manage_card_view, parent, false)
        return ManageViewHolder(recyclerViewItem)
    }

    override fun getItemCount(): Int {
        return allItems.embeddedItems.allItems.size
    }

    override fun onBindViewHolder(holder: ManageViewHolder, position: Int) {
        val item = allItems.embeddedItems.allItems[position]
        holder.itemName.text = item.item_name
        holder.startingPrice.text = item.starting_price.toString()
        holder.expiryDate.text = item.expiry_date
    }


    class ManageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemName: TextView = itemView.findViewById(R.id.item_name_manage)
        var startingPrice: TextView = itemView.findViewById(R.id.starting_price_manage)
        var expiryDate: TextView = itemView.findViewById(R.id.expiry_date_manage)
    }
}