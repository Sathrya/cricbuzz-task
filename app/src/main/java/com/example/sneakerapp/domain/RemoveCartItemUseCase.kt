package com.example.sneakerapp.domain

import com.example.sneakerapp.db.CartItem

class RemoceCartItemUseCase(private val repo: SneakerRepo) {

    suspend fun execute(item: CartItem) {
        repo.removeFromCart(item)
    }
}