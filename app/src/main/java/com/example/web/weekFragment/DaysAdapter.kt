package com.example.web.weekFragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.web.R
import com.example.web.dataclasess.Daytime
import com.example.web.dataclasess.Night
import com.example.web.dataclasess.WeatherDetailsByDay
import com.example.web.getRusConditions

class DaysAdapter(private val forecast: List<WeatherDetailsByDay>):
    RecyclerView.Adapter<DaysAdapter.ViewHold>() {

    lateinit var onDayTimeSelected : (Daytime) -> Unit
    lateinit var onNightTimeSelected : (Night) -> Unit
    lateinit var loadImage: (String, ImageView) -> Unit

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
            if (::loadImage.isInitialized) {
                loadImage.invoke(dayByPosition.dayInfo.daytime.iconViewDaytime, daytimeImage)
                loadImage.invoke(dayByPosition.dayInfo.night.iconViewNight, nightImage)
            }
            nightTemperature.text = dayByPosition.dayInfo.night.feelsLikeNight
            daytimeTemperature.text = dayByPosition.dayInfo.daytime.feelsLikeDaytime
            nightCondition.text = getRusConditions(dayByPosition.dayInfo.night.conditionNight)
            daytimeCondition.text = getRusConditions(dayByPosition.dayInfo.daytime.conditionDaytime)
            daytime.apply {
                tag = position
                setOnClickListener {
                    val tagPos = it.tag as Int
                    if(::onDayTimeSelected.isInitialized)
                        onDayTimeSelected.invoke(forecast[tagPos].dayInfo.daytime)
                }
            }
            night.apply{
                tag = position
                setOnClickListener {
                    val tagPos = it.tag as Int
                    if(::onNightTimeSelected.isInitialized)
                        onNightTimeSelected.invoke(forecast[tagPos].dayInfo.night)
                }
            }
        }
    }

    inner class ViewHold(rootView: View) : RecyclerView.ViewHolder(rootView) {
        val daytime: ConstraintLayout = rootView.findViewById(R.id.daytime)
        val night: ConstraintLayout = rootView.findViewById(R.id.night)

        val date: TextView = rootView.findViewById(R.id.dateInformationTextView)
        val daytimeImage: ImageView = rootView.findViewById(R.id.dayImageViewDaytime)
        val nightImage: ImageView = rootView.findViewById(R.id.dayImageViewNight)
        val nightTemperature: TextView = rootView.findViewById(R.id.informationTemperatureTextViewNight)
        val daytimeTemperature: TextView = rootView.findViewById(R.id.informationTemperatureTextViewDaytime)
        val nightCondition: TextView = rootView.findViewById(R.id.informationConditionTextViewNight)
        val daytimeCondition: TextView = rootView.findViewById(R.id.informationConditionTextViewDaytime)
    }

}