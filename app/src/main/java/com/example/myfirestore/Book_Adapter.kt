package com.example.myfirestore

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.my_design.view.*

class Book_Adapter(var Activity: Activity, var data: ArrayList<Book>) :
RecyclerView.Adapter<Book_Adapter.BookViewHolder>() {
    inner class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var num = itemView.textView6
        var name = itemView.textView
        var auth = itemView.textView2
        var date = itemView.textView3
        var price = itemView.textView5
        var edit_button = itemView.button2
        var id = itemView.transparent
        var tv_rate = itemView.tv_rate
        var rate = itemView.rate



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        var root = LayoutInflater.from(Activity).inflate(R.layout.my_design, parent, false)
        return BookViewHolder(root)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.num.setText(data[position].num)
        holder.name.setText(data[position].name)
        holder.auth.setText(data[position].auth)

        //Here ===================
        holder.date.setText(data[position].year.toString())
        //Here ===================

        holder.price.setText(data[position].price)
        holder.id.setText(data[position].id)
        holder.rate.rating = data[position].rate
        holder.tv_rate.setText(data[position].rate.toString())

        holder.edit_button.setOnClickListener {


            var i = Intent(Activity,Edit_Delete::class.java)
            i.putExtra("id",data[position].id)
            i.putExtra("name",data[position].name)
            i.putExtra("auth",data[position].auth)
            i.putExtra("year",data[position].year)
            i.putExtra("price",data[position].price)
            i.putExtra("rating",data[position].rate)

            Activity.startActivity(i)

        }



    }

    override fun getItemCount(): Int {
        return data.size
    }
}



