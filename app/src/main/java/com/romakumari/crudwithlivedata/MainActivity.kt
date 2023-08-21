package com.romakumari.crudwithlivedata

import android.app.Dialog
import android.app.TimePickerDialog
import android.icu.text.Transliterator.Position
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.romakumari.crudwithlivedata.databinding.ActivityMainBinding
import com.romakumari.crudwithlivedata.databinding.CustomlayoutBinding
import com.romakumari.crudwithlivedata.databinding.ListlayoutBinding
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.ArrayList
import java.util.Calendar

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
        binding.recyclerview.layoutManager = GridLayoutManager(this, 1)
        binding.recyclerview.adapter = adapter
        listViewModel = ViewModelProvider(this)[ListViewModel::class.java]
        listViewModel.itemlist.observe(this) {
            list.clear()
            list.addAll(it)
            adapter.notifyDataSetChanged()
            for (listEntity in it) {
                System.out.println("in list entity${listEntity.id}")
                System.out.println("in list entity${listEntity.Title}")
                System.out.println("in list entity${listEntity.Descripation}")
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
                if (dialogBinding.Title.text.toString().isNullOrEmpty()) {
                    dialogBinding.Title.error = ("Enter Your Title")
                } else if
                               (dialogBinding.Description.text.toString().isNullOrEmpty()) {
                    dialogBinding.Description.error = "enter Your description "

                } else {
                        listViewModel.insertitem(ListEntity(
                            Descripation = dialogBinding.Description.text.toString(),
                            Time = SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().time),
                            Title = dialogBinding.Title.text.toString()))
                    dialog.dismiss()
                }

            }
            dialog.show()
        }


        }

        override fun onDeleteClick(student: ListEntity ,position: Int) {
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
                if (dialogBinding.Title.text.toString().isNullOrEmpty()) {
                    dialogBinding.Title.error = ("Enter Your Title")
                } else if (dialogBinding.Description.text.toString().isNullOrEmpty()) {
                    dialogBinding.Description.error = "enter Your description "

                } else {
                    listViewModel.updateitem(ListEntity(
                        id = student.id,
                        Descripation = dialogBinding.Description.text.toString(),
                        Title = dialogBinding.Title.text.toString()))
                    dialog.dismiss()
                }


            }
            dialog.show()

        }

}