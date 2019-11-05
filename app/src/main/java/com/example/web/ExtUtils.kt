package com.example.web
//
//import android.graphics.Bitmap
//import android.graphics.BitmapFactory
//import android.util.Log
//import android.widget.Toast
//import androidx.annotation.WorkerThread
//import com.ahmadrosid.svgloader.SvgLoader
//import java.net.HttpURLConnection
//import java.net.URL
//
//fun getIcon(iconName: String): Bitmap?{
//    var icon: Bitmap? = null
//    val connection = URL("https://yastatic.net/weather/i/icons/blueye/color/svg/$iconName.svg").openConnection() as HttpURLConnection
//    connection.requestMethod = "GET"
//    connection .doInput = true
////    Log.e("asd", connection.responseCode.toString())
//    if (connection.responseCode == 200) {
//        val istr = connection.inputStream
//        val bytes = istr.readBytes()
//
//        icon = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
//    }
//    return icon
//}