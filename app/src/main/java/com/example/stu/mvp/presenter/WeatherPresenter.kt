package com.example.stu.mvp.presenter

import android.util.Log
import com.example.stu.base.OkHttpCallBack
import com.example.stu.entity.WeatherResponse
import com.example.stu.mvp.contect.WeatherContect
import com.example.stu.mvp.model.WeatherModel

class WeatherPresenter(private val iweatherView:WeatherContect.IView) : WeatherContect.IPresenter{
    private val TAG:String = "WeatherPresenter"
    private var weatherModel: WeatherContect.IModel = WeatherModel()
    val local:Int = 101190201
    val key:String = "59078b481b6d4b11a4553eeb815b68a8"
    override fun getWeather3d() {
        iweatherView.showProgress()
        weatherModel.getWeather(local, key, object : OkHttpCallBack<WeatherResponse> {
            override fun onSuccess(t: WeatherResponse) {
                if (t.code.equals(0)) {
                    setWeather(t)
                } else {
                    Log.i(TAG, "onSuccess: ${t.code}")
                    iweatherView.hideProgress()
                }
            }

            override fun onFailure(msg: String) {
                Log.e(TAG, "onFailure: $msg")
                iweatherView.hideProgress()
            }
        })
    }

    private fun setWeather(response: WeatherResponse){
        iweatherView.showData(response.daily)
        iweatherView.hideProgress()
    }
}