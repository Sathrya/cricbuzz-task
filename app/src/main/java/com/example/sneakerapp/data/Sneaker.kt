package com.example.sneakerapp.data


import com.google.gson.annotations.SerializedName

data class Sneaker(
    @SerializedName("sneakers")
    val sneakers: List<Sneakeritem>
)