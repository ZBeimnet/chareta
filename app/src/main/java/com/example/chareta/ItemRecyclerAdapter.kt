package com.example.chareta

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.chareta.data.Item


class ItemRecyclerAdapter(private var allItems: List<Item>, var context: Context) :
    RecyclerView.Adapter<ItemRecyclerAdapter.ItemViewHolder>() {
    private var activity: MainActivity? = MainActivity()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val recyclerViewItem = inflater.inflate(R.layout.item_card_view, parent, false)
        return ItemViewHolder(recyclerViewItem)
    }

    override fun getItemCount(): Int {
        return allItems.size
    }
    fun setData(newItem: List<Item>) {
        this.allItems = newItem
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = allItems[position]
        holder.itemName.text = item.item_name
        holder.startingPrice.text = item.starting_price.toString()

        holder.expiryDate.text = item.expiry_date

        holder.itemView.setOnClickListener {
            View.OnClickListener {
               // navigateToItemDetailFragment(item.id)
                Toast.makeText(activity, item.id.toString(), Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun navigateToItemDetailFragment(itemId: Long) {

    }





    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemName: TextView = itemView.findViewById(R.id.item_name_card_tv)
        var startingPrice: TextView = itemView.findViewById(R.id.starting_price_card_tv)
        var expiryDate: TextView = itemView.findViewById(R.id.expiry_date_card_tv)

    }

}