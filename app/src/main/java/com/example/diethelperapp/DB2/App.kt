package com.example.diethelperapp.db2

import android.app.Application
import androidx.room.Room
import com.example.diethelperapp.common.DaggerRepositoryComponent
import com.example.diethelperapp.common.jsonClasses.DCLinkDietToDishes
import com.example.diethelperapp.common.RepositoryComponent
import com.example.diethelperapp.common.RepositoryModel
import com.example.diethelperapp.common.RetrofitProvider


class App : Application() {
    private var database: AppDatabase? = null
    private var db_work: WorkWithDB? = null


    override fun onCreate() {
        super.onCreate()

        instance = this

        database = Room.databaseBuilder(this, AppDatabase::class.java, "database")
            .fallbackToDestructiveMigration()
            .build()
        val tmpStr: String = listOf<String>("ddd", "ddd").joinToString()
        val qwe = tmpStr.split(",").toList()

        val tmpList = mutableListOf(1, 3,4,5,1,1,2,3)
        val q = tmpList.filter { it == 1 }[0]



        repositories = DaggerRepositoryComponent
            .builder()
            .appDatabase(database)
            .repositoryModel(RepositoryModel())
            .build()
        db_work = WorkWithDB()
        // где то здесь надо бы сделать проверку на наличие данных в базе, чтобы заново таблицы не заполнять
        // причем они должны отличаться от предпологаемой замены
        // причем проверка должна идти во всех таблицах
        // время последненго изменения?
        // время последненей синхронизации
        // Relation
        db_work!!.fillTable(this)
        db_work!!.getLogs()
    }

    fun getDatabase(): AppDatabase? {
        return database
    }

    companion object {
        lateinit var repositories: RepositoryComponent
            private set
        var instance: App? = null
    }


}