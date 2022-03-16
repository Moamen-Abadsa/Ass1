package com.example.myfirestore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager

import com.google.firebase.Timestamp
import com.google.firebase.firestore.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList
import java.text.SimpleDateFormat
import com.google.firebase.firestore.FieldValue
import java.lang.Exception


class MainActivity : AppCompatActivity() {

    lateinit var db: FirebaseFirestore
    var TAG = "MSA"
    var myCollection = "Book"
    var data = ArrayList<Book>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        floatingActionButton2.setOnClickListener {
            var i = Intent(this, Add::class.java)
            startActivity(i)
        }
        db = Firebase.firestore

        //------------------------------------------------------------------------------------------
        recyclerView.layoutManager = LinearLayoutManager(this)

        var num = 1
        db.collection(myCollection).orderBy("Name", Query.Direction.ASCENDING)
            .get()
            .addOnSuccessListener {
                //  var s = it.size()
                // var i = 0
                for (document in it) {
                    val id = document.id
                    var name = document.get("Name").toString()
                    var auth = document.get("Auth").toString()
                    //Here ===================
                    var date = document.get("Year").toString()

                    //Here ===================
                    var price = document.get("Price").toString()
                    var rating = document.get("Rating").toString().toFloat()
                    if (rating == null) {
                        rating = 4.5f
                    }

                    var book = Book(id, "$num", name, auth, getDate(date), price, rating)
                    data.add(book)
                    var adapter = Book_Adapter(this, data)
                    recyclerView.adapter = adapter
                    //data[i] = book
                    num++
//                    if (i <= s){
//                        i++
//                    }

                }

            }
            .addOnFailureListener {

            }
        //------------------------------------------------------------------------------------------

//        var i = 0;
//        for (book in data){
//            data.add(Book(data[i].id,"${data[i].num}",data[i].BName,
//                data[i].BAuth,data[i].Date,data[i].Price))
//            i++
//
//        }


//    recyclerView.layoutManager = LinearLayoutManager(this)
//
//    var adapter = Book_Adapter(this,data)
//    recyclerView.adapter = adapter

    }

    fun getDate(timestamp: String): String {
        val tt = timestamp.toLong()
        val sfd = SimpleDateFormat("yyyy/MM/dd", Locale.ENGLISH)
        return sfd.format(Date(tt))
    }

//    fun GetAllBooks() : ArrayList<Book> {
//       // var data = ArrayList<Book>()
//        var num = 1
//        db.collection(myCollection)
//            .get()
//            .addOnSuccessListener {
//              //  var s = it.size()
//               // var i = 0
//                for (document in it){
//                    val id = document.id
//                    var name = document.get("BName").toString()
//                    var auth = document.get("BAuth").toString()
//                    var date = document.get("Date").toString()
//                    var price = document.get("Price").toString()
//                    var book = Book(id,"$num",name,auth,date,price)
//                    data.add(book)
//                    //data[i] = book
//                    num++
////                    if (i <= s){
////                        i++
////                    }
//
//                }
//
//            }
//            .addOnFailureListener {
//
//            }
//        return data
//
//
//    }


    //    private fun addUserToDB(name: String, email: String) {
//        var user = hashMapOf("name" to name, "email" to email)
//        db.collection("users")
//            .add(user)
//            .addOnSuccessListener {
//                Log.e(TAG, "${it.id}")
//
//            }
//            .addOnFailureListener {
//                Log.e(TAG,"The add process is failed -> ${it}")
//            }
//
//    }
}



