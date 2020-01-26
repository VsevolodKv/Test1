package com.example.web.detailsFragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.android_training_room.mbicycle.weather_forecast.R
import com.example.web.BaseFragment
import com.example.web.dataObject.Day
import com.example.web.dataObject.Night
import com.example.web.getRusConditions
import com.example.web.getRusWinDir
import kotlinx.android.synthetic.main.fragment_details.*

class DetailsFragment : BaseFragment(){

    companion object{
        const val KEY = "data"
    }

    private lateinit var viewModel: DetailsFragmentViewModel

    override fun getLayoutID() = R.layout.fragment_details

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        backward.setOnClickListener {
            navController.popBackStack(R.id.week_fragment, false)
        }

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(DetailsFragmentViewModel::class.java)

        viewModel.setData(arguments?.getParcelable(KEY))

        viewModel.dayTime.observe(this, Observer {
            updateUI(it)
        })

        viewModel.nightTime.observe(this, Observer {
            updateUI(it)
        })
    }

    private fun updateUI(daytime: Day){
        humidityInformationTextViewDetails.text = daytime.humidity.toString()
        conditionInformationTextViewDetails.text = getRusConditions(daytime.condition)
        tempMaxInformationTextViewDetails.text = daytime.tempMax.toString()
        windDirInformationTextViewDetails.text = getRusWinDir(daytime.windDir)
        windSpeedInformationTextViewDetails.text = daytime.windSpeed.toString()
        feelsLikInformationTextViewDetails.text = daytime.feelsLike.toString()
        tempMinInformationTextViewDetails.text = daytime.tempMin.toString()
        tempAvgInformationTextViewDetails.text = daytime.tempAvg.toString()
        windGustInformationTextViewDetails.text = daytime.windGust.toString()
        pressureMmInformationTextViewDetails.text = daytime.pressureMm.toString()
        pressurePaInformationTextViewDetails.text = daytime.pressurePa.toString()
        precMmInformationTextViewDetails.text = daytime.precMm.toString()
        precPeriodInformationTextViewDetails.text = daytime.precPeriod.toString()
    }

    private fun updateUI(night: Night){
        humidityInformationTextViewDetails.text = night.humidity.toString()
        conditionInformationTextViewDetails.text = getRusConditions(night.condition)
        tempMaxInformationTextViewDetails.text = night.tempMax.toString()
        windDirInformationTextViewDetails.text = getRusWinDir(night.windDir)
        windSpeedInformationTextViewDetails.text = night.windSpeed.toString()
        feelsLikInformationTextViewDetails.text = night.feelsLike.toString()
        tempMinInformationTextViewDetails.text = night.tempMin.toString()
        tempAvgInformationTextViewDetails.text = night.tempAvg.toString()
        windGustInformationTextViewDetails.text = night.windGust.toString()
        pressureMmInformationTextViewDetails.text = night.pressureMm.toString()
        pressurePaInformationTextViewDetails.text = night.pressurePa.toString()
        precMmInformationTextViewDetails.text = night.precMm.toString()
        precPeriodInformationTextViewDetails.text = night.precPeriod.toString()
    }
}
