package com.colorconvertersample

import android.app.Activity
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.colorconverter.repository.getRandomColors
import com.colorconverter.repository.toColorName
import com.colorconverter.repository.toHex
import com.colorconvertersample.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        val rvColors = binding.rvColors
        rvColors.run {
            layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        }

        val liveListOfRandomColors = getRandomColors(50)
        liveListOfRandomColors.observe(this@MainActivity, { listOfColor ->
            Log.d("liveListOfRandomColors","size: ${listOfColor.size}")
            rvColors.adapter = ColorAdapter(listOfColor, getDisplayMetrics(this@MainActivity), ColorClickListener{color->
                Snackbar.make(binding.constraintLayout,color.colorName, Snackbar.LENGTH_SHORT).show()
            })
        })


        // converting colors
        lifecycleScope.launch (Dispatchers.IO){
            val hexCode = "Alabaster".toHex()   // will return #f3e7db

            val colorName = hexCode?.toColorName() // is actually "#f3e7db".toColorName, should return Alabaster

            Log.d("colorConversion","colorName: $colorName, hexCode: $hexCode") // will print-> colorConversion: colorName: Alabaster, hexCode: #f3e7db
        }
    }

    private fun getDisplayMetrics(activity: Activity): DisplayMetrics {
        val outMetrics = DisplayMetrics()

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
            val display = activity.display
            display?.getRealMetrics(outMetrics)
        } else {
            @Suppress("DEPRECATION")
            val display = activity.windowManager.defaultDisplay
            @Suppress("DEPRECATION")
            display.getMetrics(outMetrics)
        }
        return outMetrics
    }
}