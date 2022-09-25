package com.Sainadh.mygroceryinventory.database.Entity

import androidx.room.*


@Entity(tableName = "my_grocery_list")
data class MyGroceryInvItems(
    @ColumnInfo(name = "groceryItem")
    var groceryItem : String,

    @ColumnInfo(name = "groceryQuantity")
    var groceryQuantity: Int,

    @ColumnInfo(name = "groceryPrice")
    var groceryPrice: Int,
){
    @PrimaryKey(autoGenerate = true)
    var id:Int? = null
}
