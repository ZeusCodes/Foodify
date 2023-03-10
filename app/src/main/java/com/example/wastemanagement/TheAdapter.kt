package com.example.wastemanagement

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.log
import kotlin.random.Random

class TheAdapter(private var data: List<Post>,
                 private val listener: (Post) -> Unit) : RecyclerView.Adapter<TheAdapter.TheHolder>()  {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TheAdapter.TheHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater
            .inflate(R.layout.layout_row, parent, false) as View
        return TheHolder(view)
    }

    override fun getItemCount() = data.size

    fun setData(newData: List<Post>){
        this.data = newData
        notifyDataSetChanged()
        Log.d("Database", "Notifying Change $newData")
    }

    override fun onBindViewHolder(holder: TheAdapter.TheHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    inner class TheHolder(private val v: View) : RecyclerView.ViewHolder(v) {
        private val postHeader: TextView = v.findViewById(R.id.postHeader)
//        private val desc: TextView = v.findViewById(R.id.description)
        private val date: TextView = v.findViewById(R.id.date)
        private val location: TextView = v.findViewById(R.id.location)
        private val picture: ImageView = v.findViewById(R.id.profile_image)
        private val img :Int = when (Random.nextInt(0, 3)) {
            1 -> R.drawable.dumplings
            2 -> R.drawable.hotdog
            3 -> R.drawable.pizza
            else -> R.drawable.drinks
        }
        fun bind(item: Post) {
            postHeader.text = item.postHeader
            picture.setImageResource(img)
//            desc.text = item.meal
            date.text = item.date
            location.text = item.location
            v.setOnClickListener { listener(item) }
        }

    }

}