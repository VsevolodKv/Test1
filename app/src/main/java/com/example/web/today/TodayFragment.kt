package com.example.web.today

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.android_test_tack.mbicycle.weather_forecast.R
import com.example.web.BaseFragment
import com.example.web.dataObject.Fact
import com.example.web.getRusConditions
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import kotlinx.android.synthetic.main.fragment_today.*

class TodayFragment : BaseFragment() {
    companion object {
        const val ARG_LAT = "ARG_LAT"
        const val ARG_LNG = "ARG_LNG"
        const val ARG_CITY_NAME = "NAME"
    }


    private lateinit var viewModel: TodayFragmentViewModel

    override fun getLayoutID() = R.layout.fragment_today

    override fun onResume() {
        super.onResume()
        setTolBarTitle(R.string.forecast_for_today)
        setBackButtonVisibility(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (isOnline()) {
            details.setOnClickListener {
                val intent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://yandex.by/pogoda/gomel?from=serp_title")
                )
                startActivity(intent)
            }
        } else {
            progressBar.visibility = View.GONE
            Toast.makeText(
                context,
                "No connection",
                Toast.LENGTH_LONG
            ).show()
        }

        weekInfo.setOnClickListener() {
            navController.navigate(R.id.action_fragment_today_to_week_fragment, arguments)
        }

        viewModel = ViewModelProviders.of(this).get(TodayFragmentViewModel::class.java)

        arguments?.let {
            viewModel.refresh(it.getDouble(ARG_LAT), it.getDouble(ARG_LNG))
            text.setText(it.getString(ARG_CITY_NAME))
        }

        viewModel.isLoading.observe(viewLifecycleOwner, Observer<Boolean> {
            if (it) {
                progressBar.visibility = View.VISIBLE
                details.isClickable = false
                weekInfo.isClickable = false
            } else {
                progressBar.visibility = View.GONE
                details.isClickable = true
                weekInfo.isClickable = true
            }
        })


        viewModel.factSubscription.observe(viewLifecycleOwner, Observer<Fact> { fact ->
            if (fact != null) {
                todayInfo(fact)
            } else {
                Toast.makeText(
                    activity,
                    R.string.there_no_forecasts,
                    Toast.LENGTH_LONG
                ).show()
            }
        })
    }

    private fun isOnline(): Boolean {
        val connectivityManager =
            context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.activeNetworkInfo != null
    }

    private fun todayInfo(fact: Fact) {
        tempTextView.text = fact.temp.toString()
        humidity.text = fact.humidity.toString()
        windSpeed.text = fact.windSpeed.toString()
        feelsLikeInformationTextView.text = fact.feelsLike.toString()
        conditionInformationTextView.text = getRusConditions(fact.condition)

        GlideToVectorYou
            .init()
            .with(context)
            .load(
                Uri.parse("https://yastatic.net/weather/i/icons/blueye/color/svg/${fact.icon}.svg"),
                image_today
            )
    }


}