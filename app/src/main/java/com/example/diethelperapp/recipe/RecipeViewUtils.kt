package com.example.diethelperapp.recipe

import com.example.diethelperapp.db2.DietDAO
import java.text.DecimalFormat

object RecipeViewUtils {
    @JvmStatic
    fun listOfIngredientsToString(list: List<DietDAO.Ingredients>?): String? {
        list ?: return null
        var resultString = ingredientToString(list.first())
        list.drop(1).forEach(){
            resultString = resultString
                .plus('\n')
                .plus(ingredientToString(it))
        }
        return resultString
    }

    fun ingredientToString(ingredient: DietDAO.Ingredients): String{
        return ingredient.ingredientsName

    }

    @JvmStatic
    fun amountToString(double: Double): String{
        val df = DecimalFormat("0.##")
        /*if (double % 1.0 == 0.0){
            return "%.0f".format(double)
        }
        else return "%.3f".format(double)
         */
        return df.format(double)
    }
}