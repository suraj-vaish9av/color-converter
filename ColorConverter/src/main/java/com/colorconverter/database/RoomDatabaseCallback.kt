package com.colorconverter.database

import android.content.Context
import android.content.res.Resources
import android.util.Log
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.colorconverter.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.BufferedReader
import java.util.*

class RoomDatabaseCallback(private val context: Context) : RoomDatabase.Callback() {

    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)

        Log.d("RoomDatabaseCallback", "onCreate called")

        GlobalScope.launch(Dispatchers.IO) {
            val list = initColorEntryList(context.resources)
            Log.d("RoomDatabaseCallback","list size: ${list.size}")
            ColorDatabase.getInstance(context).colorDao.insertAll(list)
            Log.d("RoomDatabaseCallback","all colors inserted")
        }

    }

    private fun initColorEntryList(resources: Resources): List<Color> {
        val inputStream = resources.openRawResource(R.raw.colors)
        val jsonProductsString = inputStream.bufferedReader().use(BufferedReader::readText)
        val gson = Gson()
        val productListType = object : TypeToken<ArrayList<Color>>() {}.type
        return gson.fromJson(jsonProductsString, productListType)
    }
}