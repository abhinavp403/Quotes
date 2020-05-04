package com.example.abhinav.quotes.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface philosphersDAO
{
    @Insert
    fun saveQuotes(q: philosophers)

    @Query(value = "Select * from philosopher_quotes_table")
    fun getAllQuotes() : List<philosophers>
}