package com.colorconverter.database

import androidx.room.ColumnInfo
import androidx.room.ColumnInfo.NOCASE
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = COLOR_TABLE_NAME)
class Color {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ID)
    var id:Long = 0

    @ColumnInfo(name = NAME, index = true, collate = NOCASE)
    @SerializedName("name")
    var colorName:String=""

    @ColumnInfo(name = HEX, index = true, collate = NOCASE)
    @SerializedName("hex")
    var hexCode:String=""
}