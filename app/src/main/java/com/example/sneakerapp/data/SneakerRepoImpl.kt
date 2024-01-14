package com.example.sneakerapp.data

import android.content.Context
import android.util.Log
import com.example.sneakerapp.db.CartItem
import com.example.sneakerapp.db.MyDatabase
import com.example.sneakerapp.db.SneakerDao
import com.example.sneakerapp.domain.SneakerRepo
import com.google.gson.Gson

class SneakerRepoImpl(
    private val context: Context,
    private val dao: SneakerDao
) : SneakerRepo {

//    val dao: SneakerDao = MyDatabase.getDatabase(context).sneakerDao()
    override suspend fun getAllSneakers(): Sneaker {
        val inputStream = context.assets.open("mockSneakerList.json")
        val strMockResponse = inputStream.bufferedReader().use { it.readText() }
        val mockResponse = Gson().fromJson(strMockResponse, Sneaker::class.java)
        inputStream.close()
        return mockResponse
    }

    override suspend fun addToCart(item: CartItem) {
        dao.addToCart(item)
    }

    override suspend fun getAllCartItems(): MutableList<CartItem> {
        return dao.getAllCartItems()
    }

    override suspend fun removeFromCart(item: CartItem) {
        dao.deleteItem(item)
    }

}