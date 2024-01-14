package com.example.sneakerapp.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sneaker_table")
data class CartItem(
    @PrimaryKey(autoGenerate = true)
    var sid : Int,
    var id : String?,
    var name: String?,
    var price: Int
)
