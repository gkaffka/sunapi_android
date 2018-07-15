package com.app.kaffka.sun

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.app.kaffka.sun.network.DayResponse
import com.app.kaffka.sun.network.service
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadDayData()
        updateButton.setOnClickListener { updateDayData() }
    }

    private fun updateDayData() {
        progressIndicator.visibility = View.VISIBLE
        val sunrise = sunriseEditText.text.toString()
        val sunset = sunsetEditText.text.toString()

        service.updateDay(sunrise, sunset).enqueue(object : Callback<DayResponse> {
            override fun onFailure(call: Call<DayResponse>?, t: Throwable?) {
                updateValues(null)
                progressIndicator.visibility = View.GONE
            }

            override fun onResponse(call: Call<DayResponse>?, response: Response<DayResponse>?) {
                updateValues(response?.body())
                progressIndicator.visibility = View.GONE
            }

        })
    }

    private fun loadDayData() {
        progressIndicator.visibility = View.VISIBLE
        service.getDay().enqueue(object : Callback<DayResponse> {
            override fun onFailure(call: Call<DayResponse>?, t: Throwable?) {
                updateValues(null)
                progressIndicator.visibility = View.GONE
            }

            override fun onResponse(call: Call<DayResponse>?, response: Response<DayResponse>?) {
                updateValues(response?.body())
                progressIndicator.visibility = View.GONE
            }

        })
    }

    private fun updateValues(day: DayResponse?) {
        sunriseEditText.setText(day?.sunrise ?: "")
        sunsetEditText.setText(day?.sunset ?: "")
    }

}
