package com.Sainadh.mygroceryinventory

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.Sainadh.mygroceryinventory.Adapter.GroceryRVAdapter
import com.Sainadh.mygroceryinventory.database.Entity.MyGroceryInvItems
import com.Sainadh.mygroceryinventory.database.MyGroceryRepo.MyGroceryInvRepo
import com.Sainadh.mygroceryinventory.database.MyGroceryViewModel.MyGroceryViewModel
import com.Sainadh.mygroceryinventory.database.MyGroceryViewModelFactory.MyGroceryViewModelFactory
import com.Sainadh.mygroceryinventory.database.RoomDatabase.MyGroceryRoomDatabase
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), GroceryRVAdapter.MyGroceryItemClick {
    lateinit var  listRV: RecyclerView
    lateinit var  addFAB: FloatingActionButton
    lateinit var  list: List<MyGroceryInvItems>
    lateinit var  myGroceryRVAdapter : GroceryRVAdapter
    lateinit var  myGroceryViewModel: MyGroceryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listRV = findViewById(R.id.RVItems)
        addFAB = findViewById(R.id.FAB)

        list = ArrayList<MyGroceryInvItems>()

        myGroceryRVAdapter = GroceryRVAdapter(list,this)

        listRV.layoutManager = LinearLayoutManager(this)
        listRV.adapter = myGroceryRVAdapter

        val groceryRepository = MyGroceryInvRepo(MyGroceryRoomDatabase(this))
        val factory = MyGroceryViewModelFactory(groceryRepository)

        myGroceryViewModel = ViewModelProvider(this,factory).get(MyGroceryViewModel::class.java)
        myGroceryViewModel.getListOfMyGroceryItem().observe(this,{
            myGroceryRVAdapter.item = it
            myGroceryRVAdapter.notifyDataSetChanged()
        })

        addFAB.setOnClickListener{
            openDialog()
        }


    }

    fun openDialog(){
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.my_grocery_add_item_dialog)
        val addBtn = dialog.findViewById<Button>(R.id.BtnAdd)
        val cancelBtn = dialog.findViewById<Button>(R.id.BtnCancel)
        val itemEdt =dialog.findViewById<EditText>(R.id.EditGroceryName)
        val itemPriceEdt = dialog.findViewById<EditText>(R.id.EditGroceryPrice)
        val itemQuantityEdt = dialog.findViewById<EditText>(R.id.EditGroceryQuantity)

        cancelBtn.setOnClickListener {
            dialog.dismiss()
        }

        addBtn.setOnClickListener {
            val itemName : String = itemEdt.text.toString()
            val itemPrice : String = itemPriceEdt.text.toString()
            val itemQuantity : String = itemQuantityEdt.text.toString()
            val qty : Int = itemQuantity.toInt()
            val pr : Int = itemPrice.toInt()
            if(itemName.isNotEmpty() && itemPrice.isNotEmpty() && itemQuantity.isNotEmpty()){
                val items =  MyGroceryInvItems(itemName,qty,pr)
                myGroceryViewModel.insert(items)
                Toast.makeText(applicationContext,"Item Successfully Added.",Toast.LENGTH_SHORT).show()
                myGroceryRVAdapter.notifyDataSetChanged()
                dialog.dismiss()
            }
            else{
                Toast.makeText(applicationContext,"Please Enter All The Fields", Toast.LENGTH_SHORT).show()
            }
        }
        dialog.show()
    }

    override fun onItemClick(myGroceryInvItems: MyGroceryInvItems){
      myGroceryViewModel.delete(myGroceryInvItems)
      myGroceryRVAdapter.notifyDataSetChanged()
      Toast.makeText(applicationContext,"Grocery Item Deleted..",Toast.LENGTH_SHORT).show()
    }
}
