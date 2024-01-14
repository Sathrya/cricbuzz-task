package com.example.sneakerapp.domain

import android.util.Log
import com.example.sneakerapp.db.CartItem

class AddToCartUseCase (private val repo: SneakerRepo) {

    suspend fun execute(item: CartItem) {
        return repo.addToCart(item)
    }
}