package com.example.pogodynka.model.secapi

data class WholeWeek(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<Listt>,
    val message: Int
)