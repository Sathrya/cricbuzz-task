package com.example.sneakerapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sneakerapp.data.SelectedSneaker
import com.example.sneakerapp.db.CartItem
import com.example.sneakerapp.domain.AddToCartUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

class SharedViewModel(private val cartUseCase: AddToCartUseCase) :  ViewModel(), KoinComponent {

    var selectedSneaker : SelectedSneaker? = null

    fun setSneaker(selectedItem: SelectedSneaker) {
        selectedSneaker = SelectedSneaker(
            brand = selectedItem.brand,
            name = selectedItem.name ,
            price = selectedItem.price,
            year = selectedItem.year
        )
    }

    fun addToCart(item: CartItem) {
        viewModelScope.launch(Dispatchers.IO) {
            cartUseCase.execute(item)
        }
    }
}