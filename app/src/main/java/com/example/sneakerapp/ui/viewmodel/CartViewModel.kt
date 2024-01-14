package com.example.sneakerapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sneakerapp.db.CartItem
import com.example.sneakerapp.domain.GetCartItemsUseCase
import com.example.sneakerapp.domain.RemoceCartItemUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CartViewModel(
    private val getCartItemsUseCase: GetCartItemsUseCase,
    private val remoceCartItemUseCase: RemoceCartItemUseCase
    ) : ViewModel(){

    var cartItems = MutableLiveData<MutableList<CartItem>>()

    fun getCartItems() {
        viewModelScope.launch(Dispatchers.IO) {
            val res = getCartItemsUseCase.execute()
            cartItems.postValue(res)
        }
    }

    fun removeItemFromCart(cartItemToDelete: CartItem) {
        viewModelScope.launch(Dispatchers.IO) {
            remoceCartItemUseCase.execute(cartItemToDelete)
        }
    }

    fun calculateCartItemsCost() : Int{
        var sum =0
        cartItems.value?.forEach {
            sum = sum + it.price?.toInt()!!
        }
        return sum
    }

    fun totalPriceWithTax() : Int {
        return calculateCartItemsCost() + 40
    }
}