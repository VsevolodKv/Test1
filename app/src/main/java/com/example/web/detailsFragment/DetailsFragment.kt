package com.example.web.detailsFragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.web.BaseFragment
import com.example.web.R
import com.example.web.dataclasess.Daytime
import com.example.web.dataclasess.Night
import com.example.web.getRusConditions
import com.example.web.getRusWinDir
import kotlinx.android.synthetic.main.fragment_details.*

class DetailsFragment : BaseFragment(){

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

        viewModel.setData(arguments?.getParcelable("data"))

        viewModel.dayTime.observe(this, Observer {
            updateUI(it)
        })

        viewModel.nightTime.observe(this, Observer {
            updateUI(it)
        })
    }

    private fun updateUI(daytime: Daytime){
        humidityInformationTextViewDetails.text = daytime.humidityDaytime
        conditionInformationTextViewDetails.text = getRusConditions(daytime.conditionDaytime)
        tempMaxInformationTextViewDetails.text = daytime.tempMaxDaytime
        windDirInformationTextViewDetails.text = getRusWinDir(daytime.windDirDaytime)
        windSpeedInformationTextViewDetails.text = daytime.windSpeedDaytime
        feelsLikInformationTextViewDetails.text = daytime.feelsLikeDaytime
        tempMinInformationTextViewDetails.text = daytime.tempMinDaytime
        tempAvgInformationTextViewDetails.text = daytime.tempAvgDaytime
        windGustInformationTextViewDetails.text = daytime.windGustDaytime
        pressureMmInformationTextViewDetails.text = daytime.pressureMmDaytime
        pressurePaInformationTextViewDetails.text = daytime.pressurePaDaytime
        precMmInformationTextViewDetails.text = daytime.precMmDaytime
        precPeriodInformationTextViewDetails.text = daytime.precPeriodDaytime
    }

    private fun updateUI(night: Night){
        humidityInformationTextViewDetails.text = night.humidityNight
        conditionInformationTextViewDetails.text = getRusConditions(night.conditionNight)
        tempMaxInformationTextViewDetails.text = night.tempMaxNight
        windDirInformationTextViewDetails.text = getRusWinDir(night.windDirNight)
        windSpeedInformationTextViewDetails.text = night.windSpeedNight
        feelsLikInformationTextViewDetails.text = night.feelsLikeNight
        tempMinInformationTextViewDetails.text = night.tempMinNight
        tempAvgInformationTextViewDetails.text = night.tempAvgNight
        windGustInformationTextViewDetails.text = night.windGustNight
        pressureMmInformationTextViewDetails.text = night.pressureMmNight
        pressurePaInformationTextViewDetails.text = night.pressurePaNight
        precMmInformationTextViewDetails.text = night.precMmNight
        precPeriodInformationTextViewDetails.text = night.precPeriodNight
    }
}
