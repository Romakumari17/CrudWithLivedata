package com.romakumari.crudwithlivedata

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.romakumari.crudwithlivedata.databinding.ActivityMainBinding
import com.romakumari.crudwithlivedata.databinding.CustomlayoutBinding
import com.romakumari.crudwithlivedata.databinding.ListlayoutBinding
import java.util.ArrayList

class MainActivity : AppCompatActivity(),listInterface {
    lateinit var binding: ActivityMainBinding
    lateinit var listViewModel: ListViewModel
    lateinit var adapter: RecyclerClassAdapter
    var list = arrayListOf<ListEntity>()
    lateinit var layoutManager: GridLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = RecyclerClassAdapter(list, this)
        binding.recyclerview.layoutManager = GridLayoutManager(this, 2)
        binding.recyclerview.adapter = adapter
        listViewModel = ViewModelProvider(this)[ListViewModel::class.java]
        listViewModel.itemlist.observe(this) {
            for (listEntity in it) {
                System.out.println("in list entity${listEntity.id}")
            }
        }
        binding.btnfab.setOnClickListener {
            var dialog = Dialog(this)
            var dialogBinding = CustomlayoutBinding.inflate(layoutInflater)
            dialog.setContentView(dialogBinding.root)
            dialog.getWindow()?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            dialogBinding.customBtnUpdate.setOnClickListener {
                listViewModel.insertitem(ListEntity(id = "id".toInt()))
                listViewModel.insertitem(ListEntity(Descripation = "Descripation"))
                listViewModel.insertitem(ListEntity(Time = "Time".toInt()))
                listViewModel.insertitem(ListEntity(Title = "Title"))
                dialog.dismiss()
            }
            dialog.show()
        }


    }

    override fun onDeleteClick(student: ListEntity, position: Int) {
        listViewModel.deleteitem(list[position])
    }

    override fun onUpdateClick(student: ListEntity, position: Int) {
        var dialog = Dialog(this)
        var dialogBinding = CustomlayoutBinding.inflate(layoutInflater)
        dialog.setContentView(dialogBinding.root)
        dialog.getWindow()?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialogBinding.customBtnUpdate.setOnClickListener {
            listViewModel.updateitem(ListEntity(id = "id".toInt()))
            listViewModel.updateitem(ListEntity(Descripation = "Descripation"))
            listViewModel.updateitem(ListEntity(Time = "Time".toInt()))
            listViewModel.updateitem(ListEntity(Title = "Title"))
            dialog.dismiss()
        }
        dialog.show()
    }
}