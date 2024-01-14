package com.example.sneakerapp.data


import com.google.gson.annotations.SerializedName

data class Sneakeritem(
    @SerializedName("brand")
    val brand: String?,
    @SerializedName("colorway")
    val colorway: String?,
    @SerializedName("gender")
    val gender: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("media")
    val media: Media,
    @SerializedName("name")
    val name: String?,
    @SerializedName("releaseDate")
    val releaseDate: String?,
    @SerializedName("retailPrice")
    val retailPrice: String?,
    @SerializedName("shoe")
    val shoe: String?,
    @SerializedName("styleId")
    val styleId: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("year")
    val year: Int?
)