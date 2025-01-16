package com.example.cocktailfullapplication.data.model

data class CocktailResponse(
    val drinks: List<Cocktail>?
)

data class Cocktail(
    val idDrink: String,
    val strDrink: String,
    val strDrinkThumb: String
)


