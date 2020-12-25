package com.colorconvertersample

import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.colorconverter.database.Color
import com.colorconvertersample.databinding.ItemColorBinding
import kotlin.random.Random

class ColorAdapter(private val listOfColor:List<Color>
                   , val displayMetrics:DisplayMetrics
            , val colorClickListener:ColorClickListener) : RecyclerView.Adapter<ColorAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemColorBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(listOfColor[position])
    }

    override fun getItemCount(): Int {
        return listOfColor.size
    }


    inner class ViewHolder(private val binding: ItemColorBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.colorClickListener = colorClickListener
        }

        fun bindData(color: Color){
            binding.color= color
            binding.executePendingBindings()

            binding.viewColor.layoutParams.height= displayMetrics.heightPixels/ Random.nextInt(2,4)
        }
    }
}

class ColorClickListener(val onColorClicked:(color:Color)->Unit){
    fun onItemClicked(color: Color) = onColorClicked(color)
}