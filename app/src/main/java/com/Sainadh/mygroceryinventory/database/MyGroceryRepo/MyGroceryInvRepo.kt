package com.Sainadh.mygroceryinventory.database.MyGroceryRepo

import com.Sainadh.mygroceryinventory.database.Entity.MyGroceryInvItems
import com.Sainadh.mygroceryinventory.database.RoomDatabase.MyGroceryRoomDatabase

class MyGroceryInvRepo(private val db:MyGroceryRoomDatabase) {

    suspend fun insert(items: MyGroceryInvItems) = db.getMyGroceryDao().insert(items)
    suspend fun delete(items: MyGroceryInvItems) = db.getMyGroceryDao().delete(items)

    fun  getListOfMyGroceryItem() = db.getMyGroceryDao().getListOfMyGroceryItem()
}