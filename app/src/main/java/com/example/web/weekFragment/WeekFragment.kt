package com.example.web.weekFragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.web.BaseFragment
import com.example.web.R
import com.example.web.dataclasess.WeatherDetails
import kotlinx.android.synthetic.main.fragment_week.*
import java.lang.ref.WeakReference

class WeekFragment : BaseFragment() {

    private lateinit var viewModel: WeekFragmentViewModel

    override fun getLayoutID() = R.layout.fragment_week

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        back_button.setOnClickListener {
            navController.popBackStack(R.id.fragment_today, false)
        }

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            WeekFragmentViewModel::class.java
        )
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
        viewModel.refresh()
    }

    private fun updateUI(weatherDetails: WeatherDetails) {

        if (weatherDetails.forecasts.isNotEmpty()) {
            recycler.apply {
                layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
                addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
                adapter = DaysAdapter(weatherDetails.forecasts, WeakReference(activity)).apply {
                    onDayTimeSelected = { dayTime ->
                        val dataDaytime = Bundle().apply { putParcelable("data", dayTime) }
                        navController.navigate(R.id.details_fragment, dataDaytime)
                    }
                    onNightTimeSelected = {nightTime ->
                        val dataNightTime = Bundle().apply { putParcelable("data", nightTime) }
                        navController.navigate(R.id.details_fragment, dataNightTime)
                    }
                }
            }
        } else {

            Toast.makeText(
                activity,
                "There is no forecasts for now...",
                Toast.LENGTH_LONG
            ).show()
        }
    }

}