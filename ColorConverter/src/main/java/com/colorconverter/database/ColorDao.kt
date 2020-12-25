package com.colorconverter.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ColorDao {

    @Query("select * from color where name=:colorName collate nocase")
    suspend fun getHexFromColorName(colorName:String):Color?

    @Query("select * from color where hex=:hex collate nocase")
    suspend fun getColorNameFromHex(hex:String):Color?

    @Query("select * from color order by random() limit 1")
    suspend fun getRandomColor():Color?

    @Query("select * from color order by random() limit :size")
    fun getRandomColors(size:Int):LiveData<List<Color>>

    @Insert
    suspend fun insertAll(list:List<Color>)
}