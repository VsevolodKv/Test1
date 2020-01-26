package com.example.web.weekFragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android_training_room.mbicycle.weather_forecast.R
import com.example.web.BaseFragment
import com.example.web.dataObject.WeatherRespons
import com.example.web.detailsFragment.DetailsFragment
import com.example.web.toast
import com.example.web.today.TodayFragment
import kotlinx.android.synthetic.main.fragment_week.*

class WeekFragment : BaseFragment() {
    companion object {
        const val ARG_LAT = "ARG_LAT"
        const val ARG_LNG = "ARG_LNG"
    }

    private lateinit var viewModel: WeekFragmentViewModel

    override fun getLayoutID() = R.layout.fragment_week

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        back_button.setOnClickListener {
            navController.popBackStack(R.id.fragment_today, false)
        }

        viewModel = ViewModelProviders.of(this).get(WeekFragmentViewModel::class.java)
        viewModel.isLoading.observe(this, Observer {
            if (it) {
                progressBarWeek.visibility = View.VISIBLE
                back_button.isClickable = false
                recycler.isClickable = false
            } else {
                progressBarWeek.visibility = View.GONE
                back_button.isClickable = true
                recycler.isClickable = true
            }
        })

        viewModel.weekForecast.observe(this, Observer {
            updateUI(it)
        })
        arguments?.let {
            viewModel.refresh(it.getDouble(TodayFragment.ARG_LAT), it.getDouble(TodayFragment.ARG_LNG))
        }
    }

    private fun updateUI(weatherDetails: WeatherRespons) {
        if (weatherDetails.forecasts.isNotEmpty()) {
            recycler.apply {
                layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
                addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
                adapter = DaysAdapter(weatherDetails.forecasts).apply {
                    onDayTimeSelected = { dayTime ->
                        val dataDaytime =
                            Bundle().apply { putParcelable(DetailsFragment.KEY, dayTime) }
                        navController.navigate(R.id.details_fragment, dataDaytime)
                    }
                    onNightTimeSelected = { nightTime ->
                        val dataNightTime =
                            Bundle().apply { putParcelable(DetailsFragment.KEY, nightTime) }
                        navController.navigate(R.id.details_fragment, dataNightTime)
                    }
                }
            }
        } else {
            toast("There is no forecasts for now...")
        }
    }
}