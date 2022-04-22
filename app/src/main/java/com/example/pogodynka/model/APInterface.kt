package com.example.pogodynka.model

import com.example.pogodynka.model.secapi.WholeWeek
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface APInterface {

    @GET("weather?")
    fun getDay(@Query("q") city:String,@Query("appid") key:String,@Query("units") units:String,@Query("lang") lang:String): Call<OneDay>

    @GET("forecast?")
    fun getWeek(@Query("q") city:String,@Query("appid") key:String,@Query("units") units:String): Call<WholeWeek>
}