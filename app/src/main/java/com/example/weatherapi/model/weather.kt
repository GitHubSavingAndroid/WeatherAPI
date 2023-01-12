package com.example.weatherapi.model

data class weather(
    val current: Current,
    val forecast: Forecast,
    val location: Location
)