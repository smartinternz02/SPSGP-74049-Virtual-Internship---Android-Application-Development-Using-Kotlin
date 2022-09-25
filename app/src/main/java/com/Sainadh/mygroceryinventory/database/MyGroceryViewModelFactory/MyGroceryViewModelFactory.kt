package com.Sainadh.mygroceryinventory.database.MyGroceryViewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.Sainadh.mygroceryinventory.database.MyGroceryRepo.MyGroceryInvRepo
import com.Sainadh.mygroceryinventory.database.MyGroceryViewModel.MyGroceryViewModel

class MyGroceryViewModelFactory(private val repository: MyGroceryInvRepo): ViewModelProvider.NewInstanceFactory() {
    override fun <T: ViewModel> create(modelClass: Class<T>):T{
        return MyGroceryViewModel(repository) as T
    }
}