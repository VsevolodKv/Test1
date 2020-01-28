package com.example.web.choosingCity

import android.os.Bundle
import android.view.View
import com.android_training_room.mbicycle.weather_forecast.R
import com.example.web.BaseFragment
import com.example.web.today.TodayFragment
import kotlinx.android.synthetic.main.fragment_choosing_city.*

class ChoosingCityFragment : BaseFragment() {

    override fun getLayoutID() = R.layout.fragment_choosing_city

    override fun onResume() {
        super.onResume()
        setTolBarTitle(R.string.choosing_city.toString())
        setBackButtonVisibility(false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        chooseGomel.setOnClickListener {
            val bundle = Bundle()
            bundle.putDouble(TodayFragment.ARG_LAT, 52.4345)
            bundle.putDouble(TodayFragment.ARG_LNG, 30.9754)
            navController.navigate(R.id.fragment_today, bundle)
        }

        chooseMinsk.setOnClickListener {
            val bundle = Bundle()
            bundle.putDouble(TodayFragment.ARG_LAT, 53.9)
            bundle.putDouble(TodayFragment.ARG_LNG, 27.5667)
            navController.navigate(R.id.fragment_today, bundle)
        }

        chooseBrest.setOnClickListener {
            val bundle = Bundle()
            bundle.putDouble(TodayFragment.ARG_LAT, 52.0975500)
            bundle.putDouble(TodayFragment.ARG_LNG, 23.6877500)
            navController.navigate(R.id.fragment_today, bundle)
        }

        chooseGrodno.setOnClickListener {
            val bundle = Bundle()
            bundle.putDouble(TodayFragment.ARG_LAT, 39.9075000)
            bundle.putDouble(TodayFragment.ARG_LNG, 116.3972300)
            navController.navigate(R.id.fragment_today, bundle)
        }

        chooseMogilev.setOnClickListener {
            val bundle = Bundle()
            bundle.putDouble(TodayFragment.ARG_LAT, 53.9168000)
            bundle.putDouble(TodayFragment.ARG_LNG, 30.3449000)
            navController.navigate(R.id.fragment_today, bundle)
        }

        chooseVitebsk.setOnClickListener {
            val bundle = Bundle()
            bundle.putDouble(TodayFragment.ARG_LAT, 39.9075000)
            bundle.putDouble(TodayFragment.ARG_LNG, 116.3972300)
            navController.navigate(R.id.fragment_today, bundle)
        }
    }

}