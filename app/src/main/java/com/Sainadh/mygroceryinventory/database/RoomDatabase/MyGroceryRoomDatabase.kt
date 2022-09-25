package com.Sainadh.mygroceryinventory.database.RoomDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.Sainadh.mygroceryinventory.database.DAO.MyGroceryDao
import com.Sainadh.mygroceryinventory.database.Entity.MyGroceryInvItems

@Database(entities = [MyGroceryInvItems::class], version = 1 )
abstract class MyGroceryRoomDatabase : RoomDatabase(){
    abstract fun getMyGroceryDao() : MyGroceryDao

    //Creating Object

    companion object{
        @Volatile
        private var inst : MyGroceryRoomDatabase?= null
        private val LOCK = Any()

        operator fun invoke(context : Context) = inst ?: synchronized(LOCK){
            inst ?: createMyGroceryDatabase(context).also{
                inst = it
            }
        }


    private fun createMyGroceryDatabase(context: Context) = Room.databaseBuilder(
                                                            context.applicationContext,MyGroceryRoomDatabase::class.java, "MyGrocery.db")
                                                            .build()
    }
}