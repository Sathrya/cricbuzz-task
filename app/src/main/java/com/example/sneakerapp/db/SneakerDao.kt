package com.example.sneakerapp.db

import androidx.room.*

@Dao
interface SneakerDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addToCart(item: CartItem)

    @Delete
    suspend fun deleteItem(item: CartItem)

    @Query("select * from sneaker_table")
    fun getAllCartItems() : MutableList<CartItem>
}