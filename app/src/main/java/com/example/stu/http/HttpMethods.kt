package com.example.stu.http

import android.app.Application
import android.util.Log
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory


class HttpMethods : Application() {

    private val TAG = HttpMethods::class.java.simpleName

    private lateinit var httpAPI: HttpAPI
    companion object{
        private lateinit var httpMethods: HttpMethods;
        fun getHttpMethods(): HttpMethods {
            return httpMethods;
        }
    }
    init {
        httpMethods = this
    }


    override fun onCreate() {
        super.onCreate()
        Log.i(TAG, "getHttpMethods: $httpMethods")

        val gson = GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create()

        val build = OkHttpClient.Builder().connectTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15,TimeUnit.SECONDS)
            .readTimeout(15,TimeUnit.SECONDS)
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        httpAPI = Retrofit.Builder()
            .client(build.build())
            .baseUrl(Api.WEATHER)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build().create(HttpAPI::class.java)
    }

    fun getHttpAPI(): HttpAPI {
        return httpAPI;
    }
}