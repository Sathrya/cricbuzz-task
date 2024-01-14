package com.example.sneakerapp.domain

import com.example.sneakerapp.data.Sneaker
import com.example.sneakerapp.db.CartItem

interface SneakerRepo {

    suspend fun getAllSneakers() : Sneaker
    suspend fun addToCart(item: CartItem)
    suspend fun getAllCartItems() : MutableList<CartItem>
    suspend fun removeFromCart(item: CartItem)
}