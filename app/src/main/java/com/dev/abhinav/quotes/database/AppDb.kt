package com.dev.abhinav.quotes.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [(Philosophers::class)],version = 1)
abstract class AppDb : RoomDatabase() {

    abstract fun philosphersDao(): PhilosphersDAO

    companion object {
        var INSTANCE: AppDb? = null
        fun getAppDataBase(context: Context): AppDb? {
            if (INSTANCE == null) {
                synchronized(AppDb::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDb::class.java,
                        "QuotesDB"
                    ).build()
                }
            }
            return INSTANCE
        }
    }
}