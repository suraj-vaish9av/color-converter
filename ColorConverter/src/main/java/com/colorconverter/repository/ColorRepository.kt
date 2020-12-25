package com.colorconverter.repository

import androidx.lifecycle.LiveData
import com.colorconverter.*
import com.colorconverter.database.Color
import com.colorconverter.database.ColorDao


/**
 * accepts color name and returns hex-code
 */
suspend fun String.toHex():String?{
    // search from the cache first
    colorCache?.run {
        val hexCode = findHexCodeFromColorName(this@toHex)
        if (hexCode!=null) return hexCode
    }

    val color = getColorDao().getHexFromColorName(this)
    colorCache?.addColor(color)
    return color?.hexCode
}


/**
 * accepts hex-code and returns color name
 */
suspend fun String.toColorName():String?{
    val validColorCode = this.toValidColorCode()

    // search from the cache first
    colorCache?.run {
        val colorName = findColorNameFromHexCode(this@toColorName)
        if (colorName!=null) return colorName
    }

    val color = getColorDao().getColorNameFromHex(validColorCode)
    colorCache?.addColor(color)
    return color?.colorName
}


/**
 * returns only one random color
 */
suspend fun getRandomColor(): Color? {
    return getColorDao().getRandomColor()
}


/**
 * returns random colors of the given size
 */
fun getRandomColors(size:Int): LiveData<List<Color>>
{
    return getColorDao().getRandomColors(size)
}


/**
 * Just returns a valid ColorDao instance
 */
fun getColorDao() : ColorDao{
    if (isColorDaoInitialized())
        return colorDao
    else
        throw IllegalStateException("ColorConverter is not initialized, have you called ColorConverter.configure(applicationContext[,cacheSize])?")
}