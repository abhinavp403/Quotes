package com.example.abhinav.quotes

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import com.opencsv.CSVReader
import java.io.InputStreamReader
import java.util.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*Observable.fromCallable {
            db = AppDb.getAppDataBase(context = this)
            philosphersDao = db?.philosphersDao()

            for ((person, quote) in map) {
                var x = Philosophers(quote = quote, person = person)
                with(philosphersDao) {
                    this?.insert(x)
                }
            }
            db?.philosphersDao()?.getAllQuotes()
        }.doOnNext { //list ->
            /*var finalString = ""
            list?.map { finalString += it.quote + " - " }
            quote.text = finalString*/

        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()*/


        val cardView1 = findViewById<CardView>(R.id.cardview1)
        val cardView2 = findViewById<CardView>(R.id.cardview2)
        val cardView3 = findViewById<CardView>(R.id.cardview3)
        val cardView4 = findViewById<CardView>(R.id.cardview4)
        val cardView5 = findViewById<CardView>(R.id.cardview5)
        val cardView6 = findViewById<CardView>(R.id.cardview6)
        val cardView7 = findViewById<CardView>(R.id.cardview7)
        val cardView8 = findViewById<CardView>(R.id.cardview8)

        getRoundedCornerBitmap(findViewById(R.id.image1), BitmapFactory.decodeResource(resources, R.drawable.philosophy))
        getRoundedCornerBitmap(findViewById(R.id.image2), BitmapFactory.decodeResource(resources, R.drawable.spiritual))
        getRoundedCornerBitmap(findViewById(R.id.image3), BitmapFactory.decodeResource(resources, R.drawable.scientist))
        getRoundedCornerBitmap(findViewById(R.id.image4), BitmapFactory.decodeResource(resources, R.drawable.politics))
        getRoundedCornerBitmap(findViewById(R.id.image5), BitmapFactory.decodeResource(resources, R.drawable.business))
        getRoundedCornerBitmap(findViewById(R.id.image6), BitmapFactory.decodeResource(resources, R.drawable.music))
        getRoundedCornerBitmap(findViewById(R.id.image7), BitmapFactory.decodeResource(resources, R.drawable.movie))
        getRoundedCornerBitmap(findViewById(R.id.image8), BitmapFactory.decodeResource(resources, R.drawable.author))

        cardView1.setOnClickListener(this)
        cardView2.setOnClickListener(this)
        cardView3.setOnClickListener(this)
        cardView4.setOnClickListener(this)
        cardView5.setOnClickListener(this)
        cardView6.setOnClickListener(this)
        cardView7.setOnClickListener(this)
        cardView8.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.cardview1 -> {
                val reader = CSVReader(InputStreamReader(resources.openRawResource(R.raw.data_philosophers)))
                val reader2 = CSVReader(InputStreamReader(resources.openRawResource(R.raw.data_philosophers)))
                val persons = getPerson(reader)
                val quotes = getQuote(reader2)
                val intent = Intent(this@MainActivity,SliderActivity::class.java)
                intent.putExtra("n", 1)
                intent.putStringArrayListExtra("quotes", quotes as ArrayList<String>?)
                intent.putStringArrayListExtra("persons", persons as ArrayList<String>?)
                startActivity(intent)
            }
            R.id.cardview2 -> {
                val reader = CSVReader(InputStreamReader(resources.openRawResource(R.raw.data_spiritual_leaders)))
                val reader2 = CSVReader(InputStreamReader(resources.openRawResource(R.raw.data_spiritual_leaders)))
                val persons = getPerson(reader)
                val quotes = getQuote(reader2)
                val intent = Intent(this@MainActivity,SliderActivity::class.java)
                intent.putExtra("n", 2)
                intent.putStringArrayListExtra("quotes", quotes as ArrayList<String>?)
                intent.putStringArrayListExtra("persons", persons as ArrayList<String>?)
                startActivity(intent)
            }
            R.id.cardview3 -> {
                val reader = CSVReader(InputStreamReader(resources.openRawResource(R.raw.data_scientists)))
                val reader2 = CSVReader(InputStreamReader(resources.openRawResource(R.raw.data_scientists)))
                val persons = getPerson(reader)
                val quotes = getQuote(reader2)
                val intent = Intent(this@MainActivity,SliderActivity::class.java)
                intent.putExtra("n", 3)
                intent.putStringArrayListExtra("quotes", quotes as ArrayList<String>?)
                intent.putStringArrayListExtra("persons", persons as ArrayList<String>?)
                startActivity(intent)
            }
            R.id.cardview4 -> {
                val reader = CSVReader(InputStreamReader(resources.openRawResource(R.raw.data_politicians)))
                val reader2 = CSVReader(InputStreamReader(resources.openRawResource(R.raw.data_politicians)))
                val persons = getPerson(reader)
                val quotes = getQuote(reader2)
                val intent = Intent(this@MainActivity,SliderActivity::class.java)
                intent.putExtra("n", 4)
                intent.putStringArrayListExtra("quotes", quotes as ArrayList<String>?)
                intent.putStringArrayListExtra("persons", persons as ArrayList<String>?)
                startActivity(intent)
            }
            R.id.cardview5 -> {
                val reader = CSVReader(InputStreamReader(resources.openRawResource(R.raw.data_entrepreneurs)))
                val reader2 = CSVReader(InputStreamReader(resources.openRawResource(R.raw.data_entrepreneurs)))
                val persons = getPerson(reader)
                val quotes = getQuote(reader2)
                val intent = Intent(this@MainActivity,SliderActivity::class.java)
                intent.putExtra("n", 5)
                intent.putStringArrayListExtra("quotes", quotes as ArrayList<String>?)
                intent.putStringArrayListExtra("persons", persons as ArrayList<String>?)
                startActivity(intent)
            }
            R.id.cardview6 -> {
                val reader = CSVReader(InputStreamReader(resources.openRawResource(R.raw.data_musicians)))
                val reader2 = CSVReader(InputStreamReader(resources.openRawResource(R.raw.data_musicians)))
                val persons = getPerson(reader)
                val quotes = getQuote(reader2)
                val intent = Intent(this@MainActivity,SliderActivity::class.java)
                intent.putExtra("n", 6)
                intent.putStringArrayListExtra("quotes", quotes as ArrayList<String>?)
                intent.putStringArrayListExtra("persons", persons as ArrayList<String>?)
                startActivity(intent)
            }
            R.id.cardview7 -> {
                val reader = CSVReader(InputStreamReader(resources.openRawResource(R.raw.data_actors)))
                val reader2 = CSVReader(InputStreamReader(resources.openRawResource(R.raw.data_actors)))
                val persons = getPerson(reader)
                val quotes = getQuote(reader2)
                val intent = Intent(this@MainActivity,SliderActivity::class.java)
                intent.putExtra("n", 7)
                intent.putStringArrayListExtra("quotes", quotes as ArrayList<String>?)
                intent.putStringArrayListExtra("persons", persons as ArrayList<String>?)
                startActivity(intent)
            }
            R.id.cardview8 -> {
                val reader = CSVReader(InputStreamReader(resources.openRawResource(R.raw.data_authors)))
                val reader2 = CSVReader(InputStreamReader(resources.openRawResource(R.raw.data_authors)))
                val persons = getPerson(reader)
                val quotes = getQuote(reader2)
                val intent = Intent(this@MainActivity,SliderActivity::class.java)
                intent.putExtra("n", 8)
                intent.putStringArrayListExtra("quotes", quotes as ArrayList<String>?)
                intent.putStringArrayListExtra("persons", persons as ArrayList<String>?)
                startActivity(intent)
            }
        }
    }

    private fun getPerson(reader: CSVReader): MutableList<String> {
        val persons = mutableListOf<String>()
        try {
            var nextLine: Array<String>
            while (reader.readNext().also { nextLine = it } != null) {
                //Log.d("VariableTag", nextLine[0] + " " + nextLine[1])
                persons.add(nextLine[0])
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.d("VariableTag","The specified file was not found")
        }
        return persons
    }

    private fun getQuote(reader: CSVReader): MutableList<String> {
        val quotes = mutableListOf<String>()
        try {
            var nextLine: Array<String>
            while (reader.readNext().also { nextLine = it } != null) {
                quotes.add(nextLine[1])
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.d("VariableTag","The specified file was not found")
        }
        return quotes
    }

    private fun getRoundedCornerBitmap(image: ImageView, bitmap: Bitmap) {
        val rounded = RoundedBitmapDrawableFactory.create(resources, bitmap)
        rounded.cornerRadius = 75f
        image.setImageDrawable(rounded)
    }
}