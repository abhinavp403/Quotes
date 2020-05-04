package com.example.abhinav.quotes.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "philosopher_quotes_table")
class philosophers {
    @PrimaryKey
    var person: String = ""

    @ColumnInfo(name = "quote")
    var quote: String = ""
}