package com.Sainadh.mygroceryinventory.database.MyGroceryViewModel

import androidx.lifecycle.ViewModel
import com.Sainadh.mygroceryinventory.database.Entity.MyGroceryInvItems
import com.Sainadh.mygroceryinventory.database.MyGroceryRepo.MyGroceryInvRepo
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MyGroceryViewModel(private val repo : MyGroceryInvRepo): ViewModel() {

    fun insert(items: MyGroceryInvItems) = GlobalScope.launch {
        repo.insert(items)
    }

    fun delete(items: MyGroceryInvItems) = GlobalScope.launch {
        repo.delete(items)
    }

    fun getListOfMyGroceryItem() = repo.getListOfMyGroceryItem()
}