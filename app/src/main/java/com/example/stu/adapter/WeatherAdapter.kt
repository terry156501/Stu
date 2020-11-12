package com.example.stu.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.stu.R
import com.example.stu.entity.WeatherResponse

class WeatherAdapter(mContext: Context, private var list: ArrayList<WeatherResponse.DailyBean>?): RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {
    private var context: Context? = mContext
    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        var date: TextView = itemView!!.findViewById(R.id.tvDate)
        var temp:TextView = itemView!!.findViewById(R.id.tvTemp)
        var wea:TextView = itemView!!.findViewById(R.id.tvWea)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.activity_3dweather, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.date.text = list!!.get(position).fxDate
        holder.temp.text = list!!.get(position).tempMin+"-"+ list!!.get(position).tempMax
        holder.wea.text = list!!.get(position).textDay
    }

    override fun getItemCount():  Int = list?.size!!
}