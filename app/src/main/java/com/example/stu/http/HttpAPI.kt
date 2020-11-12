package com.example.stu.http

import com.example.stu.entity.WeatherResponse
import com.example.stu.entity.WeatherResponses
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface HttpAPI {
    @GET("/")
    fun getWeather(@Query("location") local:Int, @Query("key") key:String): Observable<WeatherResponse>;

    @GET("")
    fun getWeatherNow(@Query("location") local:Int, @Query("key") key:String): Observable<WeatherResponses>;
}