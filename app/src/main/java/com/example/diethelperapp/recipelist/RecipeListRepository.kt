package com.example.diethelperapp.recipelist

import com.example.diethelperapp.DB2.Models.DishesModel

interface RecipeListRepository {

    suspend fun loadStandartRecipes(): List<DishesModel>

    suspend fun loadUserRecipes(): List<DishesModel>

    suspend fun loadRecipesFromWeb(): List<DishesModel>
}