package com.romakumari.crudwithlivedata

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


class ListViewModel(application: Application):AndroidViewModel( application)
{
    var itemlist:LiveData<List<ListEntity>>
    var listDaoInterface:ListDaoInterface
    init {
        listDaoInterface=ListDatabase.getDatabase(application).listDaoInterface()
        itemlist=listDaoInterface.getList()
    }

         fun insertitem(listEntity: ListEntity){
            viewModelScope.launch {
            listDaoInterface.insertitem(listEntity)
        }

        fun deleteitem(listEntity: ListEntity){
            viewModelScope.launch {
                listDaoInterface.deleteitem(listEntity)

        }
            fun updateitem(listEntity: ListEntity){
                viewModelScope.launch {
                    listDaoInterface.updateitem(listEntity)

                }
    }

       }

    }