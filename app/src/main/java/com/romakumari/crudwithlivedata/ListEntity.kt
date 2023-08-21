package com.romakumari.crudwithlivedata

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ListEntity(

    @PrimaryKey(autoGenerate = true)
    var  id:Int=0,
    @ColumnInfo
    var Descripation:String?=null,
    @ColumnInfo
    var Time:String?=null,
    @ColumnInfo
    var Title: String?=null

)
