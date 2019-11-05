package com.example.web.today

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ahmadrosid.svgloader.SvgLoader
import com.example.web.BaseFragment
import com.example.web.R
import com.example.web.dataclasess.Fact
import kotlinx.android.synthetic.main.fragment_today.*

class TodayFragment : BaseFragment() {

    private lateinit var viewModel: TodayViewModel

    override fun getLayoutID() = R.layout.fragment_today

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        details.setOnClickListener {
//            if (isOnline()) {
                val intent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://yandex.by/pogoda/gomel?from=serp_title")
                )
                startActivity(intent)
//            } else {
//                Toast.makeText(
//                    context,
//                    "No connection",
//                    Toast.LENGTH_LONG
//                ).show()
//            }
        }

        weekInfo.setOnClickListener {
            navController.navigate(R.id.action_fragment_today_to_week_fragment)
        }

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(TodayViewModel::class.java)
        viewModel.refresh()
        viewModel.isLoading.observe(this, Observer<Boolean> {
            if (it) {
                progressBar.visibility = View.VISIBLE
                details.isClickable = false
            } else {
                progressBar.visibility = View.GONE
                details.isClickable = true
            }
        })


        viewModel.factSubscription.observe(viewLifecycleOwner, Observer<Fact> { it ->

            textTemp.text = it.temp
            humidity.text = it.humidity
            windSpeed.text = it.windSpeed

            SvgLoader.pluck().with(activity).load(
                "https://yastatic.net/weather/i/icons/blueye/color/svg/${it.icon}.svg",
                image_today
            )
        })
    }

//    private fun isOnline(): Boolean {
//        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//        return connectivityManager.activeNetworkInfo != null
//    }
}