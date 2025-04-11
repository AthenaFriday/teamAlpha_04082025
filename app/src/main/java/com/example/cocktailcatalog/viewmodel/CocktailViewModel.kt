package com.example.cocktailcatalog.viewmodel

import androidx.lifecycle.*
import com.example.cocktailcatalog.model.Cocktail
import com.example.cocktailcatalog.model.CocktailDetail
import com.example.cocktailcatalog.repository.CocktailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CocktailViewModel @Inject constructor(
    private val repository: CocktailRepository
) : ViewModel() {

    // LiveData for cocktail list
    private val _cocktails = MutableLiveData<List<Cocktail>>()
    val cocktails: LiveData<List<Cocktail>> get() = _cocktails

    // LiveData for cocktail details
    private val _cocktailDetail = MutableLiveData<CocktailDetail>()
    val cocktailDetail: LiveData<CocktailDetail> get() = _cocktailDetail

    // Cached original list (used for local search filtering)
    private var originalCocktails: List<Cocktail> = emptyList()

    fun loadPopularDrinks() {
        viewModelScope.launch {
            val list = repository.getPopularDrinks()
            originalCocktails = list
            _cocktails.value = list
        }
    }

    fun loadAllDrinks() {
        viewModelScope.launch {
            val list = repository.getAllDrinks()
            originalCocktails = list
            _cocktails.value = list
        }
    }

    fun loadAlcoholicDrinks() {
        viewModelScope.launch {
            val list = repository.getAlcoholicDrinks()
            originalCocktails = list
            _cocktails.value = list
        }
    }

    fun loadNonAlcoholicDrinks() {
        viewModelScope.launch {
            val list = repository.getNonAlcoholicDrinks()
            originalCocktails = list
            _cocktails.value = list
        }
    }

    fun loadCocktailDetail(id: String) {
        viewModelScope.launch {
            repository.getCocktailDetail(id)?.let {
                _cocktailDetail.value = it
            }
        }
    }

    // Filter cocktails locally based on the query text.
    fun searchCocktails(query: String) {
        _cocktails.value = originalCocktails.filter { cocktail ->
            cocktail.name.contains(query, ignoreCase = true)
        }
    }
}
