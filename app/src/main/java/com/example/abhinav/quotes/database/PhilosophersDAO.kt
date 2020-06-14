package com.example.abhinav.quotes.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PhilosphersDAO
{
    @Insert
    fun insert(q: Philosophers)

    @Query(value = "Select * from philosopher_quotes_table")
    fun getAllQuotes() : LiveData<List<Philosophers>>
}