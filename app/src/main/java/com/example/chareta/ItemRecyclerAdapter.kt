package com.example.chareta


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

import androidx.recyclerview.widget.RecyclerView
import com.example.chareta.data.Item
import java.text.SimpleDateFormat


class ItemRecyclerAdapter(private var allItems: List<Item>) :
    RecyclerView.Adapter<ItemRecyclerAdapter.ItemViewHolder>() {
    private lateinit var activity: MainActivity
    private lateinit var listener: OnItemClickListener


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
        holder.expiryDate.text = item.expiry_date.toString()
        holder.bind(with(allItems) { get(position) }, listener)




        holder.itemView.setOnClickListener {
            (activity as NavigationHost).navigateTo(
                ItemDetailFragment.newInstance(item.id),
                true
            ) // Navigate to the next Fragment
        }
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemName: TextView = itemView.findViewById(R.id.item_name_card_tv)
        var startingPrice: TextView = itemView.findViewById(R.id.starting_price_card_tv)
        var expiryDate: TextView = itemView.findViewById(R.id.expiry_date_card_tv)

        fun bind(item: Item, listener: OnItemClickListener) {

            itemView.setOnClickListener {
                listener.onItemClick(item)
                var fragmentManager: FragmentManager? = null

                fragmentManager?.beginTransaction()
                    ?.replace(R.id.container, ItemDetailFragment())
                    ?.commit()


            }
        }

    }

    interface OnItemClickListener {
        fun onItemClick(item: Item)
    }


}