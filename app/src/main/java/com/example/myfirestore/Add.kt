package com.example.myfirestore

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.squareup.okhttp.internal.http.HttpDate.format
import kotlinx.android.synthetic.main.add_book.*
import kotlinx.android.synthetic.main.add_book.add_auth
import kotlinx.android.synthetic.main.add_book.add_name
import kotlinx.android.synthetic.main.add_book.add_price
import java.lang.String.format
import java.text.DateFormat
import java.text.MessageFormat.format
import java.text.SimpleDateFormat
import java.util.*

class Add : AppCompatActivity() {
        lateinit var db: FirebaseFirestore
        var TAG = "MSA"

    var timestamp: String = ""



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_book)

        db = Firebase.firestore

        add_year.setOnClickListener {
            val currentDate = Calendar.getInstance()
            val day = currentDate.get(Calendar.DAY_OF_MONTH)
            val month = currentDate.get(Calendar.MONTH)
            val year = currentDate.get(Calendar.YEAR)


            val picker = DatePickerDialog(
                this,
                { datePicker, i, i2, i3 -> convertDate(i, i2, i3) },
                year,
                month,
                day
            )
            picker.show()



        }
        add_button.setOnClickListener {
            val name = add_name.text.toString()
            val auther = add_auth.text.toString()

            //Here ===================

            val price = add_price.text.toString()
            val rating = add_rate.rating
            var book = hashMapOf("Name" to name , "Auth" to auther ,
                "Year" to timestamp , "Price" to "${price.trim()}$" , "Rating" to rating)

            db.collection("Book")
                .add(book)
                .addOnSuccessListener {
                    Log.e(TAG, "${it.id}")
                    Toast.makeText(this,"The book has been added successfully",Toast.LENGTH_LONG).show()
                    var i = Intent(this,MainActivity::class.java)
                    startActivity(i)

                }


                .addOnFailureListener {
                    Log.e(TAG,"The add process is failed -> ${it}")
                    Toast.makeText(this,"Adding the book failed",Toast.LENGTH_LONG).show()
                }
        }
    }

    private fun convertDate(i: Int, i2: Int, i3: Int) {
        val str_date = "$i/${1 + i2}/$i3"
        add_year.setText(str_date)

        // write to firebase timestamp
        val formatter: DateFormat = SimpleDateFormat("yyyy/MM/dd", Locale.ENGLISH)
        val date = formatter.parse(str_date) as Date

        timestamp = date.time.toString()

    }



//    private fun addBookToDB(Name : String , Auth : String ,
//                            Year : String , Price : String) {
//
//        var book = hashMapOf("Name" to Name , "Auth" to Auth ,
//            "Year" to Year , "Price" to Price)
//
//
//        db.collection("Book")
//            .add(book)
//            .addOnSuccessListener {
//                Log.e(TAG, "${it.id}")
//                Toast.makeText(this,"The book has been added successfully",Toast.LENGTH_LONG).show()
//                var i = Intent(this,MainActivity::class.java)
//                startActivity(i)
//
//            }
//
//
//            .addOnFailureListener {
//                Log.e(TAG,"The add process is failed -> ${it}")
//                Toast.makeText(this,"Adding the book failed",Toast.LENGTH_LONG).show()
////                var i = Intent(this,MainActivity::class.java)
////                startActivity(i)
//            }
//
//    }

}