package com.example.sneakerapp.data

data class SelectedSneaker(
    var id    : String? = "",
    var name  : String? = "",
    var brand : String? = "",
    var price : Int,
    var year  : Int?
)
