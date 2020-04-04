package com.example.diethelperapp.recipecreate

import com.example.diethelperapp.db2.DietDAO

class RecipeCreateRepositoryImplementation(private val dao: DietDAO) : RecipeCreateRepository {
    override suspend fun addRecipeToDatabase(): DietDAO.Dishes {
        TODO("Not yet implemented")
    }

    override suspend fun loadIngredients(): List<DietDAO.Ingredients> {
        return dao.getAllIngredient()
    }

    override suspend fun getIngredientByName(name: String): DietDAO.Ingredients {
        return dao.getCertainIngredientById(name)
    }
}