package com.example.chareta.adapter


import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chareta.R
import com.example.chareta.data.model.ItemsWrapper
import com.example.chareta.view.ItemDetailFragment


class ItemRecyclerAdapter(private var allItems: ItemsWrapper, private var fm: FragmentManager) :
    RecyclerView.Adapter<ItemRecyclerAdapter.ItemViewHolder>() {
lateinit var binding: com.example.chareta.databinding.PostedItemFragmentBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val recyclerViewItem = inflater.inflate(R.layout.item_card_view, parent, false)
        binding = DataBindingUtil.inflate(inflater,R.layout.item_card_view,parent,false)
        return ItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        Log.d("Recycler View Adapter", allItems.embeddedItems.allItems.size.toString())
        return allItems.embeddedItems.allItems.size
    }
    fun setData(newItem: ItemsWrapper) {
        this.allItems = newItem
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = allItems.embeddedItems.allItems[position]
        holder.itemName.text = item.item_name
        holder.startingPrice.text = item.starting_price.toString()
        holder.expiryDate.text = item.expiry_date

        holder.itemView.setOnClickListener {
              fm.beginTransaction()
                .replace(R.id.container, ItemDetailFragment.newInstance(allItems.embeddedItems.allItems[position].id))
                .addToBackStack(null)
                .commit()
        }
    }


    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemName: TextView = itemView.findViewById(R.id.item_name_card_tv)
        var startingPrice: TextView = itemView.findViewById(R.id.starting_price_card_tv)
        var expiryDate: TextView = itemView.findViewById(R.id.expiry_date_card_tv)
    }

}
