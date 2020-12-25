package com.colorconverter

import android.content.Context
import com.colorconverter.database.ColorDao
import com.colorconverter.database.ColorDatabase

lateinit var colorDao:ColorDao

var colorCache : ColorCache?= null

object ColorConverter {

    const val DEFAULT_COLOR_CACHE_SIZE = 50

    const val NO_CACHING = 0

    /**
     * @param context: needed to initialize room database
     * @param cacheSize: needed to create ColorCache of cacheSize
     */
    fun configure(context: Context, cacheSize:Int = DEFAULT_COLOR_CACHE_SIZE){
        colorDao = ColorDatabase.getInstance(context).colorDao
        updateCacheSize(cacheSize)
    }

    /**
     * It will update the color cache size, but also all the previously cached colors will be gone, use it wisely.
     */
    fun updateCacheSize(cacheSize:Int = DEFAULT_COLOR_CACHE_SIZE){
        colorCache = if (cacheSize==NO_CACHING)
            null
        else
            ColorCache(cacheSize)
    }

}

fun isColorDaoInitialized() = ::colorDao.isInitialized

