package com.romakumari.crudwithlivedata

interface listInterface {
    fun onDeleteClick(student: ListEntity, position :Int)
    fun onUpdateClick(student: ListEntity, position :Int)
}