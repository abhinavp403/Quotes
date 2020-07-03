package com.dev.abhinav.quotes.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "philosopher_quotes_table")
class Philosophers {
    @PrimaryKey
    var person: String = ""

    @ColumnInfo(name = "quote")
    var quote: String = ""
}

/*@Entity(tableName = "philosopher_quotes_table")
data class Philosophers(
    @PrimaryKey
    val person: String,
    @ColumnInfo(name = "quote")
    val quote: String
)*/