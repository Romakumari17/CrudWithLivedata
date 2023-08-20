package com.romakumari.crudwithlivedata

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ListDaoInterface {
    @Insert
    suspend fun insertitem(listEntity: ListEntity)
    @Delete
    suspend fun deleteitem(listEntity: ListEntity)
    @Update
    suspend fun updateitem(listEntity: ListEntity)

    @Query ("SELECT*FROM ListEntity")
    fun getList():LiveData<List<ListEntity>>
}