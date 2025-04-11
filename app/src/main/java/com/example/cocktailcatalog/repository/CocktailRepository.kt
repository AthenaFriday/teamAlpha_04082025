package com.example.cocktailcatalog.repository

import com.example.cocktailcatalog.model.Cocktail
import com.example.cocktailcatalog.model.CocktailDetail
import com.example.cocktailcatalog.network.ApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CocktailRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getAlcoholicDrinks(): List<Cocktail> =
        apiService.getDrinksByAlcohol("Alcoholic").drinks ?: emptyList()

    suspend fun getNonAlcoholicDrinks(): List<Cocktail> =
        apiService.getDrinksByAlcohol("Non_Alcoholic").drinks ?: emptyList()

    suspend fun getPopularDrinks(): List<Cocktail> {
        val cocktailGlass = apiService.getDrinksByGlass("Cocktail_glass").drinks ?: emptyList()
        val champagneFlute = apiService.getDrinksByGlass("Champagne_flute").drinks ?: emptyList()
        return cocktailGlass + champagneFlute
    }

    suspend fun getAllDrinks(): List<Cocktail> =
        getAlcoholicDrinks() + getNonAlcoholicDrinks()

    suspend fun getCocktailDetail(id: String): CocktailDetail? =
        apiService.getCocktailDetails(id).drinks?.firstOrNull()
}
