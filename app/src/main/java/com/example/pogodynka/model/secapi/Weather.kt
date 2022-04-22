package com.example.pogodynka.model.secapi

data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)