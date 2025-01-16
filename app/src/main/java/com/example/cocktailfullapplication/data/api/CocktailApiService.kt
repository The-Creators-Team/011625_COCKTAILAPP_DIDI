package com.example.cocktailfullapplication.data.api

import com.example.cocktailfullapplication.data.model.CocktailResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CocktailApiService {
    @GET("search.php")
    suspend fun searchCocktails(@Query("f") query: String): CocktailResponse

    @GET("filter.php")
    suspend fun filterCocktails(@Query("c") category: String): CocktailResponse
}