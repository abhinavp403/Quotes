package com.dev.abhinav.quotes

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.gson.Gson
import java.util.*

class SliderActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager
    private lateinit var madapter: SlideAdapter
    private val gson = Gson()
    private var json: Array<String?> = Array(8) {gson.toJson(ArrayList<String>())}
    private var json2: Array<String?> = Array(8) {gson.toJson(ArrayList<String>())}
    private val cal: Calendar = Calendar.getInstance()
    private var currentDay = cal.get(Calendar.DAY_OF_MONTH)
    private lateinit var settings: SharedPreferences
    private lateinit var pref: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_slider)

        val q: MutableList<String>? = intent.getStringArrayListExtra("quotes")
        val p: MutableList<String>? = intent.getStringArrayListExtra("persons")
        val n: Int = intent.getIntExtra("n", 0)

        settings = getSharedPreferences("PREFS", Context.MODE_PRIVATE)
        pref = applicationContext.getSharedPreferences("COLOR", Context.MODE_PRIVATE)
        editor = settings.edit()

        val lastday: Array<Int> = arrayOf(settings.getInt("day1", 0), settings.getInt("day2", 0), settings.getInt("day3", 0),
            settings.getInt("day4", 0), settings.getInt("day5", 0), settings.getInt("day6", 0), settings.getInt("day7", 0),
            settings.getInt("day8", 0))

        //settings.edit().remove("PREFS").apply()
        val quotes: Array<String> = q!!.toTypedArray()
        val persons: Array<String> = p!!.toTypedArray()
        val (shuffledQuotes,shuffledPersons) = shuffle(quotes, persons, n, lastday)

        viewPager = findViewById(R.id.viewpager)
        madapter = SlideAdapter(this, shuffledQuotes, shuffledPersons, settings, pref, editor, n)
        viewPager.adapter = madapter
    }

    private fun <String:Comparable<String>>shuffle(items: Array<String>, items2: Array<String>, n: Int, lastday: Array<Int>): Pair<Array<String>, Array<String>>{
        //Log.d("ggg", lastday.contentToString())
        //Log.d("ggg", "day$n")
        if(lastday[n-1] != currentDay) {
            editor.putInt("day$n", currentDay)
            val rg = Random()
            for (i in 0..items.size - 1) {
                val randomPosition = rg.nextInt(items.size)
                val tmp: String = items[i]
                val tmp2: String = items2[i]
                items[i] = items[randomPosition]
                items2[i] = items2[randomPosition]
                items[randomPosition] = tmp
                items2[randomPosition] = tmp2
            }
            json[n-1] = gson.toJson(items)
            json2[n-1] = gson.toJson(items2)
            Log.d("ggg", json2[n-1])
            editor.putString("quotes$n", json[n-1])
            editor.putString("persons$n", json2[n-1])
            editor.apply()
        }
        //editor.clear().commit()
        return Pair(items, items2)
    }
}