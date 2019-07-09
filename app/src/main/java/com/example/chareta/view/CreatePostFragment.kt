package com.example.chareta.view

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.chareta.MainActivity
import com.example.chareta.NavigationHost
import com.example.chareta.R
import com.example.chareta.data.model.Item
import com.example.chareta.viewmodel.ItemViewModel
import kotlinx.android.synthetic.main.create_post_fragment.view.*


class CreatePostFragment : Fragment() {
    lateinit var itemname: EditText
    lateinit var itemdescription: EditText
    lateinit var startingprice: EditText
    lateinit var backbtn: Button
    lateinit var postbtn: Button
    lateinit var expirydate:TextView
    lateinit var postdate_btn:Button
    @RequiresApi(Build.VERSION_CODES.O)
fun clearFields(){
        itemname.setText("")
        itemdescription.setText("")
        startingprice.setText("")
    }
    lateinit var itemViewModel:ItemViewModel
    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.create_post_fragment, container, false)
        val activity = activity as MainActivity?
        activity?.hideBottomBar(true)
        itemViewModel = ViewModelProviders.of(this).get(ItemViewModel::class.java)
        val isConnected = activity?.connected()
        itemname = view.itemname_editext
        itemdescription = view.item_description_edit_text
        startingprice = view.startingprice_Edittext
        expirydate = view.expiry_date_textview
        postbtn = view.post_button
        postdate_btn = view.pickdate_btn
        backbtn = view.back_btn
        val c = java.util.Calendar.getInstance()
        val year = c.get(java.util.Calendar.YEAR)
        val month = c.get(java.util.Calendar.MONTH)
        val day = c.get(java.util.Calendar.DAY_OF_MONTH)



        backbtn.setOnClickListener {
            (activity as NavigationHost).navigateTo(PostedItemFragment(), true)
        }

        postbtn.setOnClickListener {
            if (isConnected!!) {
                itemViewModel.insertItem(readFields())
                clearFields()
                Toast.makeText(context, "Post added", Toast.LENGTH_LONG).show()
            }
        }

        postdate_btn.setOnClickListener {

            val dpd =
                DatePickerDialog(requireContext(), DatePickerDialog.OnDateSetListener { view, myear, mmonth, mday ->
                    expirydate.setText(" " + mday + "/" + mmonth + "/" + myear)

                }, year, month, day)
            dpd.show()

        }
        return view
    }

        fun readFields()= Item(0, itemname.text.toString(),
            itemdescription.text.toString(),
            startingprice.text.toString().toLong(),
            java.util.Date().toString(),
            expirydate.text.toString())



    }

