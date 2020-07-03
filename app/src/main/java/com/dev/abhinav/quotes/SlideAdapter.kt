package com.dev.abhinav.quotes

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.viewpager.widget.PagerAdapter
import com.flask.colorpicker.ColorPickerView
import com.flask.colorpicker.OnColorChangedListener
import com.flask.colorpicker.OnColorSelectedListener
import com.flask.colorpicker.builder.ColorPickerClickListener
import com.flask.colorpicker.builder.ColorPickerDialogBuilder
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.ByteArrayOutputStream
import java.lang.reflect.Type

class SlideAdapter(var context: Context, q: Array<String>, p: Array<String>, val settings: SharedPreferences, val pref: SharedPreferences, var editor: SharedPreferences.Editor, val n:Int) : PagerAdapter() {
    var layoutInflater: LayoutInflater? = null
    private val quotes: Array<String> = q
    private val persons: Array<String> = p
    private val gson = Gson()
    private var currentBackgroundColor: Int = R.color.black
    private lateinit var quote: TextView
    private lateinit var person: TextView
    private lateinit var share: ImageView
    private lateinit var layout: View

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
        quote = view.findViewById(R.id.quote)
        person = view.findViewById(R.id.person)
        Colorpicker(view.findViewById(R.id.colorpicker))
        share = view.findViewById(R.id.share)
        share.setOnClickListener {
            ShareScreen(view)
        }
        quote.text = shuffledQuotes[position]
        person.text = shuffledPersons[position]
        container.addView(view)
        return view
    }

    private fun ShareScreen(view: View) {
        layout = view.findViewById(R.id.sharelayout)
        val bitmap = getScreenShot(layout)
        try {
            val intent = Intent(Intent.ACTION_SEND)
            intent.putExtra(Intent.EXTRA_STREAM, getImageUri(context, bitmap))
            intent.type = "image/png"
            startActivity(context, Intent.createChooser(intent, "Share image via"), null)
        } catch (e: Exception) {
            Log.d("error", e.printStackTrace().toString())
        }
    }

    private fun Colorpicker(colorpicker: ImageView) {
        editor = pref.edit()
        editor.putInt("color", currentBackgroundColor)
        changeBackgroundColor(pref.getInt("color", -1))
        Log.d("ColorPicker", Integer.toHexString(currentBackgroundColor))
        colorpicker.setOnClickListener {
            ColorPickerDialogBuilder
                .with(context)
                .setTitle("Choose color")
                .initialColor(currentBackgroundColor)
                .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                .density(12)
                .setOnColorChangedListener(OnColorChangedListener { selectedColor -> // Handle on color change
                })
                .setOnColorSelectedListener(OnColorSelectedListener { selectedColor ->
                    Log.d("onColorSelected: 0x", Integer.toHexString(selectedColor))
                })
                .setPositiveButton("ok",
                    ColorPickerClickListener { dialog, selectedColor, allColors ->
                        changeBackgroundColor(selectedColor)
                        notifyDataSetChanged()
                        Log.d("ColorPicker2", Integer.toHexString(selectedColor))
                        editor.putInt("color", selectedColor)
                        editor.apply()
                    })
                .setNegativeButton("cancel",
                    DialogInterface.OnClickListener { dialog, which -> })
                .showColorEdit(true)
                .setColorEditTextColor(
                    ContextCompat.getColor(context, R.color.black)
                )
                .build()
                .show()
        }
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as LinearLayout)
    }

    private fun changeBackgroundColor(selectedColor: Int) {
        currentBackgroundColor = selectedColor
        Log.d("ColorPicker2", Integer.toHexString(currentBackgroundColor))
        //root.setBackgroundColor(selectedColor.toInt())
        quote.setTextColor(selectedColor)
        person.setTextColor(selectedColor)
    }

    private fun getScreenShot(view: View): Bitmap {
        val returnedBitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(returnedBitmap)
        val bgDrawable: Drawable? = view.background
        if (bgDrawable != null) bgDrawable.draw(canvas)
        else canvas.drawColor(Color.WHITE)
        view.draw(canvas)
        return returnedBitmap
    }

    fun getImageUri(inContext: Context, inImage: Bitmap): Uri? {
        val bytes = ByteArrayOutputStream()
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val path: String = MediaStore.Images.Media.insertImage(inContext.contentResolver, inImage, "Title", null)
        return Uri.parse(path)
    }
}