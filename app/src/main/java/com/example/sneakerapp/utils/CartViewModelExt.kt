package com.example.sneakerapp.utils

import com.example.sneakerapp.ui.fragments.CartFragment
import com.example.sneakerapp.ui.viewmodel.CartViewModel

fun CartFragment.concatenateCurrency(price : Int) : String {
    return "$$price"
}