package com.example.cocktailfullapplication.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.cocktailfullapplication.data.api.RetrofitInstance
import com.example.cocktailfullapplication.data.model.Cocktail
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class CocktailViewModel : ViewModel() {
    var cocktails = mutableListOf<Cocktail>()
    var errorMessage: String? = null

    fun searchCocktails(query: String) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.searchCocktails(query)
                println("Response: ${response.drinks}")
                cocktails = response.drinks?.toMutableList() ?: mutableListOf()
            } catch (e: Exception) {
                errorMessage = e.message
                println(errorMessage)
            }
        }
    }

    fun filterCocktails(category: String) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.filterCocktails(category)
                cocktails = response.drinks?.toMutableList() ?: mutableListOf()
            } catch (e: Exception) {
                errorMessage = e.message
            }
        }
    }
}
