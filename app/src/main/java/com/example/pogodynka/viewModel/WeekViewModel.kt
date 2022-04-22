package com.example.pogodynka.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pogodynka.model.OneDay
import com.example.pogodynka.model.Repository
import com.example.pogodynka.model.secapi.WholeWeek
import kotlinx.coroutines.launch

class WeekViewModel : ViewModel(){
    private val _all: MutableLiveData<WholeWeek> = MutableLiveData()
    val all: LiveData<WholeWeek>
        get(){
            return _all
        }


    fun postWeek(city:String, keyy:String, metricc:String)
    {
        viewModelScope.launch {

            val weekk= Repository.getWeek(city, keyy, metricc)

            if(weekk!=null)
                _all.value=weekk
        }

    }


}