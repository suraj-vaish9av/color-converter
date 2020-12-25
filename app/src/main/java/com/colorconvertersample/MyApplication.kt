package com.colorconvertersample

import android.app.Application
import com.colorconverter.ColorConverter

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        ColorConverter.configure(applicationContext, ColorConverter.NO_CACHING)
    }
}