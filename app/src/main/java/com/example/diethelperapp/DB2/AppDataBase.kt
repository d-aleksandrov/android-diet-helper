package com.example.diethelperapp.DB2

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.diethelperapp.DB.DietDAO


@Database(
    entities = [DietDAO.Diet::class],
    version = 3
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getDietDAO(): DietDAO


}