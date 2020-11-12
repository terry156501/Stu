package com.example.stu.mvp.contect

import android.content.Context
import com.example.stu.base.OkHttpCallBack
import com.example.stu.entity.Now
import com.example.stu.entity.WeatherResponse
import com.example.stu.entity.WeatherResponses

interface WeatherContect {

    interface IModel {
        fun getWeather(local:Int, key:String,callBack:OkHttpCallBack<WeatherResponse>);
    }

    interface IView{
        val curContext: Context//获取上下文对象

        fun showProgress() //显示进度条

        fun hideProgress() //隐藏进度条

        fun showData(weather3d: List<WeatherResponse.DailyBean>) //显示数据到View上

        fun showInfo(info: String)
    }

    interface IPresenter{
        fun getWeather3d()
    }
}