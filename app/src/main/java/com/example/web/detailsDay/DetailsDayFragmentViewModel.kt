package com.example.web.detailsDay

import android.os.Parcelable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.web.dataObject.Day
import com.example.web.dataObject.Night

class DetailsDayFragmentViewModel: ViewModel()  {

    val dayTime = MutableLiveData<Day>()
    val nightTime = MutableLiveData<Night>()

    fun setData(data: Parcelable?){
        when (data){
            is Day -> dayTime.postValue(data)
            is Night -> nightTime.postValue(data)
        }
    }
}