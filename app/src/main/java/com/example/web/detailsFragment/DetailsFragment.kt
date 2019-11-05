package com.example.web.detailsFragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.web.BaseFragment
import com.example.web.R
import com.example.web.dataclasess.Daytime
import com.example.web.dataclasess.Night
import kotlinx.android.synthetic.main.fragment_details.*
import kotlinx.android.synthetic.main.fragment_today.progressBar
import kotlinx.android.synthetic.main.fragment_week.*

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
        humidity.text = daytime.humidityDaytime
    }

    private fun updateUI(night: Night){
        humidity.text = night.humidityNight
    }
}
