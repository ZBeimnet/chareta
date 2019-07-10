package com.example.chareta.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.chareta.R
import com.example.chareta.data.model.ItemsWrapper
import com.example.chareta.view.YourPostsFragment
import com.example.chareta.viewmodel.ItemViewModel

class ManageRecyclerAdapter(private var allItems: ItemsWrapper, private var itemViewModel: ItemViewModel) :
    RecyclerView.Adapter<ManageRecyclerAdapter.ManageViewHolder>() {
    lateinit var binding: YourPostsFragmentBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ManageViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val recyclerViewItem = inflater.inflate(R.layout.manage_card_view, parent, false)
        binding = DataBindingUtil.inflate(inflater, R.layout.manage_card_view, parent, false)
        return ManageViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return allItems.embeddedItems.allItems.size
    }

    override fun onBindViewHolder(holder: ManageViewHolder, position: Int) {
        val item = allItems.embeddedItems.allItems[position]
        val itemId = allItems.embeddedItems.allItems[position].id
        holder.itemName.text = item.item_name
        holder.startingPrice.text = item.starting_price.toString()
        holder.expiryDate.text = item.expiry_date

        holder.deleteButton.setOnClickListener {
            itemViewModel.deleteItem(itemId)
        }
    }


    class ManageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemName: TextView = itemView.findViewById(R.id.item_name_manage)
        var startingPrice: TextView = itemView.findViewById(R.id.starting_price_manage)
        var expiryDate: TextView = itemView.findViewById(R.id.expiry_date_manage)

        val deleteButton: Button = itemView.findViewById(R.id.withdraw_button)
    }
}