package com.example.stu.entity

data class WeatherResponses(
    val code: String,
    val fxLink: String,
    val now: Now,
    val refer: Refer,
    val updateTime: String
)

data class Now(
    val cloud: String,
    val dew: String,
    val feelsLike: String,
    val humidity: String,
    val icon: String,
    val obsTime: String,
    val precip: String,
    val pressure: String,
    val temp: String,
    val text: String,
    val vis: String,
    val wind360: String,
    val windDir: String,
    val windScale: String,
    val windSpeed: String
)

data class Refer(
    val license: List<String>,
    val sources: List<String>
)