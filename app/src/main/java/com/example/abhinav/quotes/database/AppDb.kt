package com.example.abhinav.quotes.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [(philosophers::class)],version = 1)
abstract class AppDb : RoomDatabase() {

    abstract fun philosphersDAO(): philosphersDAO
}