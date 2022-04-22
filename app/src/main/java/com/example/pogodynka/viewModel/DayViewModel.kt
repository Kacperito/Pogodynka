package com.example.pogodynka.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pogodynka.model.OneDay
import com.example.pogodynka.model.Repository
import kotlinx.coroutines.launch

class DayViewModel : ViewModel(){
    private val _all: MutableLiveData<OneDay> = MutableLiveData()
    val all: LiveData<OneDay>
        get(){
            return _all
        }


    fun postDay(city:String, keyy:String, metricc:String, langg:String)
    {
        viewModelScope.launch {

            val dayy= Repository.getDay(city, keyy, metricc,langg)

            if(dayy!=null)
                _all.value=dayy
        }

    }


}