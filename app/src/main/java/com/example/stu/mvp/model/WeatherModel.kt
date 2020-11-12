package com.example.stu.mvp.model

import android.util.Log
import com.example.stu.base.OkHttpCallBack
import com.example.stu.entity.WeatherResponse
import com.example.stu.http.Api
import com.example.stu.http.HttpAPI
import com.example.stu.mvp.contect.WeatherContect
import com.example.stu.http.RetrofitUtils
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import retrofit2.Retrofit
import java.net.ConnectException
import java.net.SocketTimeoutException

class WeatherModel : WeatherContect.IModel{
    private val TAG:String = "WeatherModel"
    override fun getWeather(
        local: Int,
        key: String,
        callBack: OkHttpCallBack<WeatherResponse>
    ) {
        val weather:Retrofit = RetrofitUtils.newInstence(Api.WEATHER)
        weather.create(HttpAPI::class.java)
            .getWeather(local, key)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object : Observer<WeatherResponse> {
                override fun onNext(t: WeatherResponse) {
                    callBack.onSuccess(t)
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                    if (e is HttpException) {
                        val httpException = e
                        val code = httpException.code()
                        if (code == 500 || code == 404) {
                            callBack.onFailure("服务器出错")
                        }
                    } else if (e is ConnectException) {
                        callBack.onFailure("网络断开,请打开网络!")
                    } else if (e is SocketTimeoutException) {
                        callBack.onFailure("网络连接超时!!")
                    } else {
                        callBack.onFailure("发生未知错误" + e.message)
                        Log.e("Tag", e.message.toString())
                    }
                }

                override fun onComplete() {
                }

                override fun onSubscribe(d: Disposable) {
                }

            })
    }

}