package com.example.web.detailsFragment

import android.os.Parcelable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.web.dataclasess.*

class DetailsFragmentViewModel: ViewModel()  {

    val dayTime = MutableLiveData<Daytime>()
    val nightTime = MutableLiveData<Night>()

    fun setData(data: Parcelable?){
        when (data){
            is Daytime -> dayTime.postValue(data)
            is Night -> nightTime.postValue(data)
        }
    }
}