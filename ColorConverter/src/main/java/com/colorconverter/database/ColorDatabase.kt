package com.colorconverter.database

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Database(entities = [Color::class],version = 2, exportSchema = false)
abstract class ColorDatabase : RoomDatabase() {

    abstract val colorDao: ColorDao

    companion object{

        @Volatile
        private var INSTANCE: ColorDatabase?= null

        fun getInstance(context: Context): ColorDatabase {

            synchronized(this){

                var instance = INSTANCE
                if (instance==null){
                    instance = Room.databaseBuilder(context,ColorDatabase::class.java,"color_database")
                        //.createFromAsset("color_database.db")
                        .addCallback(RoomDatabaseCallback(context))
                        .fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance

                    // RoomDatabaseCallback not called until an operation is performed on database
                    // just to trigger the RoomDatabaseCallback we calling getRandomColor
                    GlobalScope.launch (Dispatchers.IO){
                        val colorName = instance.colorDao.getRandomColor()
                        Log.d("getInstance",colorName?.colorName?:"colorName not found")
                    }
                }
                return instance
            }

        }
    }
}