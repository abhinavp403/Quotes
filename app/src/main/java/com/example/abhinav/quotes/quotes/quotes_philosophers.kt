package com.example.abhinav.quotes.quotes

import android.util.Log
import com.opencsv.CSVReader

class quotes_philosophers {

    fun getPerson(reader: CSVReader): HashMap<Int, String> {
        val persons = HashMap<Int, String>()
        try {
            var nextLine: Array<String>
            var i = 0
            while (reader.readNext().also { nextLine = it } != null) {
                Log.d("VariableTag", nextLine[0])
                persons[i] = nextLine[0]
                i += 1
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.d("VariableTag","The specified file was not found")
        }
        return persons
    }

    fun getQuote(reader: CSVReader): HashMap<Int, String> {
        val quote = HashMap<Int, String>()
        try {
            var nextLine: Array<String>
            var i = 0
            while (reader.readNext().also { nextLine = it } != null) {
                quote[i] = nextLine[1]
                i += 1
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.d("VariableTag","The specified file was not found")
        }
        return quote
    }
}