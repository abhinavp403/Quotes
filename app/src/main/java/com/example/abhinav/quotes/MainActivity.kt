package com.example.abhinav.quotes

import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.room.Room
import com.example.abhinav.quotes.database.AppDb
import com.example.abhinav.quotes.database.philosophers
import com.example.abhinav.quotes.quotes.quotes_philosophers
import com.opencsv.CSVReader
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.io.IOException
import java.io.InputStreamReader


class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val image_view = findViewById<CardView>(R.id.image1)
        image_view.setOnClickListener {
            Toast.makeText(this@MainActivity, "You clicked on ImageView.", Toast.LENGTH_SHORT).show()
            //doit().execute()
        }

        var q = quotes_philosophers()
        val reader = CSVReader(InputStreamReader(resources.openRawResource(R.raw.data)))
        var person = q.getPerson(reader)
        var quote = q.getQuote(reader)

        var db= Room.databaseBuilder(applicationContext, AppDb::class.java,"QuotesDB").build()
        val thread = Thread {
            var bookEntity = philosophers()
            bookEntity.person = "1"
            bookEntity.quote = "Kotlin for Android Developer"

            db.philosphersDAO().saveQuotes(bookEntity)

            //fetch Records
            db.philosphersDAO().getAllQuotes().forEach()
            {
                Log.i("Fetch Records", "Id:  : ${it.person}")
                Log.i("Fetch Records", "Name:  : ${it.quote}")
            }
        }
        thread.start()
    }

    inner class doit : AsyncTask<Void?, Void?, Void?>() {
        var words: String? = null
        override fun doInBackground(vararg p0: Void?): Void? {
            val url = "https://www.greeka.com/greece-history/greek-people-quotes/"
            var document: Document? = null
            try {
                document = Jsoup.connect(url).get()
            } catch (e: IOException) {
                e.printStackTrace()
            }
            words = document!!.text()
            Log.d("TAG", words)
            return null
        }

        override fun onPostExecute(result: Void?) {
            super.onPostExecute(result)
        }
    }
}
