package com.colorconverter

import android.util.Log
import com.colorconverter.database.Color


/**
 * ColorCache can store last accessed Color objects of given size,
 * let's say ColorCache's size is 50, it means ColorCache can store maximum 50 elements of Color type
 * when more items need to be added the it will remove the oldest item
 * so we can say if the size is 50, then ColorCache can store latest 50 Color objects.
 */
class ColorCache(private val size:Int) {

    private val arrayOfColors= ArrayList<Color>(size)

    fun addColor(color: Color?){
        if (color==null)
            return
        if (arrayOfColors.size==size)
            arrayOfColors.removeAt(0)
        arrayOfColors.add(color)
    }

    fun findColorNameFromHexCode(hexCode:String): String? {
        val color = arrayOfColors.singleOrNull { it.hexCode.equals(hexCode, ignoreCase = true) }
        Log.d("colorName", "findColorNameFromHexCode: ${color?.colorName}")
        return color?.colorName
    }

    fun findHexCodeFromColorName(colorName:String): String? {
        val color = arrayOfColors.singleOrNull { it.colorName.equals(colorName, ignoreCase = true) }
        Log.d("colorName", "findColorNameFromHexCode: ${color?.hexCode}")
        return color?.hexCode
    }

}