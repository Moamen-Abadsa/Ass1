package com.example.myfirestore

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.add_book.*
import kotlinx.android.synthetic.main.edit_delete_book.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class Edit_Delete : AppCompatActivity() {
    lateinit var db : FirebaseFirestore


    var timestamp: String = ""
    //edit_auth
    //edit_year
    //edit_price

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_delete_book)
        var i = intent
        var id = i.getStringExtra("id").toString()
        var year = i.getStringExtra("year").toString()
        var auth = i.getStringExtra("auth").toString()
        var price = i.getStringExtra("price").toString()
        var name = i.getStringExtra("name").toString()
        var rating = i.getFloatExtra("rating",0.0f).toFloat()
        edit_name.setText(name).toString()
        edit_auth.setText(auth).toString()
        edit_year.setText(year).toString()
        edit_price.setText(price).toString()
        edit_rate.rating = rating.toFloat()

        db = Firebase.firestore

        edit_year.setOnClickListener {
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

        edit_button.setOnClickListener {

           var name2 = edit_name.text.toString()
            var auth2 = edit_auth.text.toString()
            convertDate()
            var price2 = edit_price.text.toString()
            var rating = edit_rate.rating
            db.collection("Book").document(id).update("Name" , name2 ,
                "Auth",auth2 , "Year" , timestamp , "Price" , price2 , "rating" , rating)
                .addOnSuccessListener {
                var i = Intent(this , MainActivity::class.java)
                    startActivity(i)
            }
                .addOnFailureListener {
                    Toast.makeText(this,"The book has been added successfully",Toast.LENGTH_LONG).show()

                }
        }
        delete_button.setOnClickListener {

            deleteBookById(id)
        }
    }

    private fun deleteBookById(id : String){
        db.collection("Book").document(id).delete()
            .addOnSuccessListener {
                var i = Intent(this , MainActivity::class.java)
                startActivity(i)
            }
            .addOnFailureListener {
               Toast.makeText(this,"The book have not been deleted",Toast.LENGTH_LONG).show()
            }
    }

    private fun convertDate() {
        val year2 =  edit_year.text.toString()
        val formatter: DateFormat = SimpleDateFormat("yyyy/MM/dd", Locale.ENGLISH)
        val date = formatter.parse(year2) as Date
        timestamp = date.time.toString()
    }

    private fun convertDate(i: Int, i2: Int, i3: Int) {
        val str_date = "$i/${1 + i2}/$i3"
        edit_year.setText(str_date)
        val formatter: DateFormat = SimpleDateFormat("yyyy/MM/dd", Locale.ENGLISH)
        val date = formatter.parse(str_date) as Date
        timestamp = date.time.toString()
    }

}