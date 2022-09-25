package com.Sainadh.mygroceryinventory.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.Sainadh.mygroceryinventory.R
import com.Sainadh.mygroceryinventory.database.Entity.MyGroceryInvItems

class GroceryRVAdapter (var item: List<MyGroceryInvItems>,
                        val myGroceryItemClick: MyGroceryItemClick) : RecyclerView.Adapter<GroceryRVAdapter.MyGroceryViewHolder>(){

    inner class MyGroceryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val myGroceryName = itemView.findViewById<TextView>(R.id.TVGroceryName)
        val myGroceryQuantity = itemView.findViewById<TextView>(R.id.TVGroceryQuantity)
        val myGroceryRate = itemView.findViewById<TextView>(R.id.TVGroceryRate)
        val myGroceryAmount = itemView.findViewById<TextView>(R.id.TVTotalAmt)
        val deleteGrocery = itemView.findViewById<ImageView>(R.id.DeleteGrocery)
    }


    interface MyGroceryItemClick{
        fun onItemClick(groceryInvItems: MyGroceryInvItems)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyGroceryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.my_grocery_item_rv,parent,false)
        return MyGroceryViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyGroceryViewHolder, position: Int) {
        holder.myGroceryName.text = item.get(position).groceryItem
        holder.myGroceryQuantity.text = item.get(position).groceryQuantity.toString()
        holder.myGroceryRate.text = "RS. "+item.get(position).groceryPrice.toString()
        val itemTotal : Int = item.get(position).groceryPrice* item.get(position).groceryQuantity
        holder.myGroceryAmount.text = "RS. "+itemTotal.toString()
        holder.deleteGrocery.setOnClickListener{
            myGroceryItemClick.onItemClick(item.get(position))
        }

    }

    override fun getItemCount(): Int {
       return item.size
    }
}