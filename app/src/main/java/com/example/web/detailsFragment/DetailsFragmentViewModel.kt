package com.example.web.detailsFragment

import android.os.Parcelable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.web.R
import com.example.web.dataclasess.*
import com.example.web.model.WebModel
import org.json.JSONObject

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