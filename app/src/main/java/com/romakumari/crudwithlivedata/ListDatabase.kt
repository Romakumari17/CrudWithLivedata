package com.romakumari.crudwithlivedata


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(version = 1, entities = [ListEntity::class])
 abstract class ListDatabase:RoomDatabase() {
     abstract fun listDaoInterface():ListDaoInterface
     companion object{
         private var listDatabase:ListDatabase?=null
         fun getDatabase(context: Context):ListDatabase{
             if (listDatabase==null){
                 listDatabase= Room.databaseBuilder(context,ListDatabase::class.java,
                     context.resources.getString(R.string.app_name)).build()
             }
             return listDatabase!!
         }
     }
}