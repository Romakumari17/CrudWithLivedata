package com.romakumari.crudwithlivedata

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList

class RecyclerClassAdapter(var list: ArrayList<ListEntity>,var listInterface: listInterface):RecyclerView.Adapter<RecyclerClassAdapter.ViewHolder>() {

    class ViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        var etTitle=view.findViewById<EditText>(R.id.etTitle)
        var etDescripation=view.findViewById<EditText>(R.id.etDescription)
        var etTime=view.findViewById<EditText>(R.id.etTime)
        var etid=view.findViewById<EditText>(R.id.etid)
        var  customBtnUpdate =view.findViewById<Button>(R.id.BtnUpdate)
        var Btndelete=view.findViewById<Button>(R.id.Btndelete)
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerClassAdapter.ViewHolder {
        val View=LayoutInflater.from(parent.context)
            .inflate(R.layout.listlayout,parent,false)
             return ViewHolder(View)

    }

    override fun onBindViewHolder(holder: RecyclerClassAdapter.ViewHolder, position: Int) {
      holder.etTitle.setText(list[position].Title)
      holder.etTime.setText(list[position].Time)
      holder.etid.setText(list[position].id)
      holder.etDescripation.setText(list[position].Descripation)
        holder.customBtnUpdate.setOnClickListener {
            listInterface.onUpdateClick(list[position], position)
        }
        holder.Btndelete.setOnClickListener {
            listInterface.onDeleteClick(list [position],position)
        }
    }

    override fun getItemCount(): Int {
       return list.size
    }
    fun setitem(list: ArrayList<ListEntity>){
        this.list=list
        notifyDataSetChanged()
    }
}