package com.example.diethelperapp.db2

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diethelperapp.common.JsonUtil
import com.example.diethelperapp.db2.relationDataClasses.DietWithDishes
import com.example.diethelperapp.db2.relationDataClasses.OneToOneListToIngredient
import kotlinx.coroutines.launch

class WorkWithDB : ViewModel() {// Первоначальное заполнение БД.
init {
    val db: AppDatabase? = App.instance?.getDatabase()
    val dietDAO: DietDAO? = db?.getDietDAO()
    DB_work = dietDAO
}

    companion object {
        var DB_work: DietDAO? = null
    }

    fun getCountLines(): Int {
        var count: Int = 0
        viewModelScope.launch {
            try {
                count = DB_work!!.getCountLines()

            } catch (t: Throwable) {
                print(t.message)
            }
        }
        return count
    }

    fun fillTable(context: Context) {
        val tmp = JsonUtil(context)
        viewModelScope.launch {
            try {
                DB_work?.insertDiets(tmp.listDiets)
                DB_work?.insertCrossRefDietWithDishes(tmp.listCrossRefDietOwnDishes)
                DB_work?.insertDishes(tmp.listDishes)
                DB_work?.insertCalendar(tmp.listCalendar)
                DB_work?.insertIngredients(tmp.listIngredients)
                DB_work?.insertListIngredients(tmp.listCrossRefIngredients)

            } catch (t: Throwable) {
                print(t.message)
            }
        }
    }

    fun getLogs() {
       // var testLi: List<DietDAO.Ingredients>?
        var testC: List<DietDAO.CrossRefCalendarOwnDishes>?
       // var tmpTest: List<DietDAO.Ingredients>
        var tmpI: List<DietWithDishes>?


        viewModelScope.launch {
            try {
              //  testLi = DB_work?.getListIngredients()
              //  testC = DB_work?.getCrossRefCalendarWithDishes()
             //   tmpTest = DB_work?.getAllIngredient()!!
                tmpI = DB_work?.getDishesByCertainDiet(1) as List<DietWithDishes>?


            } catch (t: Throwable) {
                print(t.message)
            }
        }

    }
}