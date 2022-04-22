package com.example.pogodynka.model

import com.example.pogodynka.model.secapi.WholeWeek
import retrofit2.awaitResponse

class Repository {

    companion object {

        suspend fun getDay(city:String, keyy:String, metricc:String, langg:String): OneDay? {
            return RetrofitInstance.api.getDay(city, keyy, metricc,langg).awaitResponse().body()
        }
        suspend fun getWeek(city:String, keyy:String, metricc:String): WholeWeek? {
            return RetrofitInstance.api.getWeek(city, keyy, metricc).awaitResponse().body()
        }
    }
}