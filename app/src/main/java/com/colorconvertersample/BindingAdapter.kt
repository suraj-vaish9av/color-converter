package com.colorconvertersample

import android.graphics.Color.parseColor
import android.util.Log
import android.view.View
import androidx.databinding.BindingAdapter
import com.colorconverter.database.Color


@BindingAdapter("colorize")
fun View.bindColor(color: Color){
    Log.d("bindColor","${color.colorName}, ${color.hexCode}")
    setBackgroundColor(parseColor(color.hexCode))
}