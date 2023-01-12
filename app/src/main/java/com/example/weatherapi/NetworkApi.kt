package com.example.weatherapi

import com.example.weatherapi.model.Day
import com.example.weatherapi.model.weather
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkApi {
    @GET("forecast.json")
    fun getWeather(
        @Query("days")day:String,
        @Query("key")k:String,
        @Query("q")city:String,
        @Query("aqi")aqi:String
    ): Call<weather>
}