package com.example.cocktailcatalog.model

import com.google.gson.annotations.SerializedName

data class Cocktail(
    @SerializedName("idDrink")
    val id: String,
    @SerializedName("strDrink")
    val name: String,
    @SerializedName("strDrinkThumb")
    val thumbnail: String
)
