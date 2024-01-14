package com.example.sneakerapp.domain

import com.example.sneakerapp.db.CartItem

class GetCartItemsUseCase (private val repo: SneakerRepo) {

    suspend fun execute() : MutableList<CartItem> {
        return repo.getAllCartItems()
    }
}