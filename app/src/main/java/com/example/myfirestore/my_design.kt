package com.example.myfirestore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.my_design.*

class my_design : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.my_design)

        button2.setOnClickListener {
            var i = Intent(this,Edit_Delete::class.java)
            startActivity(i)
            i.putExtra("open",true)
        }
    }
}