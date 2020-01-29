package com.example.web

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.android_test_tack.mbicycle.weather_forecast.R

abstract class BaseFragment : Fragment() {
    protected lateinit var navController: NavController

    private lateinit var backButton: ImageView
    private lateinit var toolbarTitle: TextView


    abstract fun getLayoutID(): Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getLayoutID(), container, false)
    }


    fun setTolBarTitle(textTitle: Int) {
        toolbarTitle.setText(textTitle)
    }

    fun setBackButtonVisibility(visibility: Boolean){
        if (visibility){
            backButton.visibility = View.VISIBLE
        } else {
            backButton.visibility = View.GONE
        }
    }

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        navController = NavHostFragment.findNavController(this)

        val activity = activity as MainActivity

        backButton = activity.findViewById(R.id.back_btn)
        toolbarTitle = activity.findViewById(R.id.toolbar_title)

        backButton.setOnClickListener {
            navController.popBackStack()
        }
    }

}