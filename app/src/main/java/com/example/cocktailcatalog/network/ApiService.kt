package com.example.cocktailcatalog.network

import com.example.cocktailcatalog.model.CocktailDetailResponse
import com.example.cocktailcatalog.model.DrinksResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    // Returns alcoholic or non-alcoholic drinks.
    @GET("filter.php")
    suspend fun getDrinksByAlcohol(@Query("a") alcoholic: String): DrinksResponse

    // Returns popular drinks based on glass type.
    @GET("filter.php")
    suspend fun getDrinksByGlass(@Query("g") glass: String): DrinksResponse

    // Returns detailed information for a cocktail.
    @GET("lookup.php")
    suspend fun getCocktailDetails(@Query("i") id: String): CocktailDetailResponse
}
