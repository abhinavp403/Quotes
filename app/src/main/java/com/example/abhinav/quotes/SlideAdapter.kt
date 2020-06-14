package com.example.abhinav.quotes

import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class SlideAdapter(var context: Context, q: Array<String>, p: Array<String>, val settings: SharedPreferences, val n:Int) : PagerAdapter() {
    var layoutInflater: LayoutInflater? = null
    private val quotes: Array<String> = q
    private val persons: Array<String> = p
    private val gson = Gson()

    override fun getCount(): Int {
        return quotes.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val json: String? = settings.getString("quotes$n", null)
        val json2: String? = settings.getString("persons$n", null)
        val type: Type = object : TypeToken<Array<String?>?>() {}.type
        val shuffledQuotes:Array<String> = gson.fromJson(json, type)
        val shuffledPersons:Array<String> = gson.fromJson(json2, type)
        layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view: View = layoutInflater!!.inflate(R.layout.slide, container, false)
        val layoutslide = view.findViewById<View>(R.id.slideLinearLayout) as LinearLayout
        val quote = view.findViewById<TextView>(R.id.quote)
        val person = view.findViewById<TextView>(R.id.person)
        //layoutslide.setBackground();
        quote.text = shuffledQuotes[position]
        person.text = shuffledPersons[position]
        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as LinearLayout)
    }
}