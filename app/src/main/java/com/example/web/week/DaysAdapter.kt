package com.example.web.week

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.android_training_room.mbicycle.weather_forecast.R
import com.example.web.dataObject.Day
import com.example.web.dataObject.forecasts
import com.example.web.dataObject.Night
import com.example.web.getRusConditions
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou

class DaysAdapter(private val forecast: List<forecasts>) :
    RecyclerView.Adapter<DaysAdapter.ViewHold>() {

    lateinit var onDayTimeSelected: (Day) -> Unit
    lateinit var onNightTimeSelected: (Night) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        val rootView =
            LayoutInflater.from(parent.context).inflate(R.layout.fragment_day_week, parent, false)
        return ViewHold(rootView)
    }

    override fun getItemCount(): Int = forecast.size

    override fun onBindViewHolder(holder: ViewHold, position: Int) {

        holder.apply {
            val dayByPosition = forecast[position]
            date.text = dayByPosition.date
            getIcon(dayByPosition.parts.day.icon, daytimeImage)
            getIcon(dayByPosition.parts.night.icon, nightImage)
            nightTemperature.text = dayByPosition.parts.night.feelsLike.toString()
            daytimeTemperature.text = dayByPosition.parts.day.feelsLike.toString()
            nightCondition.text = getRusConditions(dayByPosition.parts.night.condition)
            daytimeCondition.text = getRusConditions(dayByPosition.parts.day.condition)
            daytime.apply {
                tag = position
                setOnClickListener {
                    val tagPos = it.tag as Int
                    if (::onDayTimeSelected.isInitialized)
                        onDayTimeSelected.invoke(forecast[tagPos].parts.day)
                }
            }
            night.apply {
                tag = position
                setOnClickListener {
                    val tagPos = it.tag as Int
                    if (::onNightTimeSelected.isInitialized)
                        onNightTimeSelected.invoke(forecast[tagPos].parts.night)
                }
            }
        }
    }

    private fun getIcon(iconName: String, imageView: ImageView) {
        GlideToVectorYou
            .init()
            .with(imageView.context)
            .load(
                Uri.parse("https://yastatic.net/weather/i/icons/blueye/color/svg/$iconName.svg"),
                imageView
            )
    }


    class ViewHold(rootView: View) : RecyclerView.ViewHolder(rootView) {

        val daytime: ConstraintLayout = rootView.findViewById(R.id.daytime)
        val night: ConstraintLayout = rootView.findViewById(R.id.night)

        val date: TextView = rootView.findViewById(R.id.dateInformationTextView)
        val daytimeImage: ImageView = rootView.findViewById(R.id.dayImageViewDaytime)
        val nightImage: ImageView = rootView.findViewById(R.id.dayImageViewNight)
        val nightTemperature: TextView =
            rootView.findViewById(R.id.informationTemperatureTextViewNight)
        val daytimeTemperature: TextView =
            rootView.findViewById(R.id.informationTemperatureTextViewDaytime)
        val nightCondition: TextView = rootView.findViewById(R.id.informationConditionTextViewNight)
        val daytimeCondition: TextView =
            rootView.findViewById(R.id.informationConditionTextViewDaytime)
    }

}