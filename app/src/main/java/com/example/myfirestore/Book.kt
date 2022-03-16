package com.example.myfirestore

import com.google.firebase.firestore.ServerTimestamp
import java.security.Timestamp
import java.util.*

data class Book(val id : String, val num : String,
                val name : String, val auth : String,
                val year : String,
                val price : String, val rate : Float
                )
