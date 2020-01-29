package com.example.web.week

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android_test_tack.mbicycle.weather_forecast.R
import com.example.web.BaseFragment
import com.example.web.dataObject.WeatherRespons
import com.example.web.detailsDay.DetailsDayFragment
import com.example.web.toast
import com.example.web.today.TodayFragment
import kotlinx.android.synthetic.main.fragment_week.*

class WeekFragment : BaseFragment() {

    private lateinit var viewModel: WeekFragmentViewModel

    override fun getLayoutID() = R.layout.fragment_week

    override fun onResume() {
        super.onResume()
        setTolBarTitle(R.string.weather_for_week)
        setBackButtonVisibility(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel = ViewModelProviders.of(this).get(WeekFragmentViewModel::class.java)
        viewModel.isLoading.observe(viewLifecycleOwner, Observer {
            if (it) {
                progressBarWeek.visibility = View.VISIBLE
                recycler.isClickable = false
            } else {
                progressBarWeek.visibility = View.GONE
                recycler.isClickable = true
            }
        })

        viewModel.weekForecast.observe(viewLifecycleOwner, Observer {
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
                            Bundle().apply { putParcelable(DetailsDayFragment.KEY, dayTime) }
                        navController.navigate(R.id.details_fragment, dataDaytime)
                    }
                    onNightTimeSelected = { nightTime ->
                        val dataNightTime =
                            Bundle().apply { putParcelable(DetailsDayFragment.KEY, nightTime) }
                        navController.navigate(R.id.details_fragment, dataNightTime)
                    }
                }
            }
        } else {
            toast(R.string.there_no_forecasts)
        }
    }
}