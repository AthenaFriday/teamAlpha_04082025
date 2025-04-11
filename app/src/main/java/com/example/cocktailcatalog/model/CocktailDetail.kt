package com.example.cocktailcatalog.model

import com.google.gson.annotations.SerializedName

data class CocktailDetail(
    @SerializedName("idDrink")
    val id: String,
    @SerializedName("strDrink")
    val name: String,
    @SerializedName("strCategory")
    val category: String?,
    @SerializedName("strAlcoholic")
    val alcoholic: String?,
    @SerializedName("strInstructions")
    val instructions: String?,
    @SerializedName("strDrinkThumb")
    val thumbnail: String?,
    @SerializedName("strIngredient1")
    val ingredient1: String?,
    @SerializedName("strIngredient2")
    val ingredient2: String?,
    @SerializedName("strIngredient3")
    val ingredient3: String?,
    @SerializedName("strIngredient4")
    val ingredient4: String?,
    @SerializedName("strIngredient5")
    val ingredient5: String?
)
