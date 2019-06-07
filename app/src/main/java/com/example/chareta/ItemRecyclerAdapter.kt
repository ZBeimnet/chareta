package com.example.chareta

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.chareta.data.Item
import com.example.chareta.data.ItemList

class ItemRecyclerAdapter(private var allItems: ItemList):
    RecyclerView.Adapter<ItemRecyclerAdapter.ItemViewHolder>() {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val recyclerViewItem = inflater.inflate(R.layout.item_card_view,parent,false)
        return ItemViewHolder(recyclerViewItem)
    }

    override fun getItemCount(): Int {
       return allItems.itemLists.size
    }

    fun setData(newItem: ItemList){
        this.allItems = newItem
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        val item = allItems.itemLists[position]
        holder.itemName.text = item.item_name
        holder.startingPrice.text = item.starting_price.toString()
        holder.expiryDate.text = item.expiry_date.toString()
    }

    class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var itemName: TextView = itemView.findViewById(R.id.item_name_card_tv)
        var startingPrice: TextView = itemView.findViewById(R.id.starting_price_card_tv)
        var expiryDate: TextView = itemView.findViewById(R.id.expiry_date_card_tv)
    }
}