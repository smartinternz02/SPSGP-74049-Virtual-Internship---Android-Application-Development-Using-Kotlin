package com.Sainadh.mygroceryinventory.database.DAO

import androidx.lifecycle.LiveData
import androidx.room.*
import com.Sainadh.mygroceryinventory.database.Entity.MyGroceryInvItems


@Dao
interface MyGroceryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insert(item : MyGroceryInvItems)

    @Delete
     fun delete(item: MyGroceryInvItems)

    @Query("SELECT * FROM my_grocery_list")
    fun getListOfMyGroceryItem() : LiveData<List<MyGroceryInvItems>>
}