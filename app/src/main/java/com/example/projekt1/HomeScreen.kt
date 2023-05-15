package com.example.projekt1;


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject

class HomeScreen : Fragment() {

    private lateinit var editText: EditText

    private val API_KEY = "3a26aa6873cc4f52394932f9575bce29"
    private val client = WeatherApiClient(API_KEY)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home_screen, container, false)

        editText = view.findViewById(R.id.editText)



        val weatherTextView = view.findViewById<TextView>(R.id.weatherTextView)
        getWeatherForCity("Stockholm") { weatherData, error ->
            if (error != null) {
                activity?.runOnUiThread {
                    weatherTextView.text = "Error: ${error.message}"
                }
            } else {
                val weatherJson = JSONObject(weatherData)
                val temperatureInKelvin = weatherJson.getJSONObject("main").getDouble("temp")
                val temperatureInCelsius = temperatureInKelvin - 273.15
                val weatherDescription = weatherJson.getJSONArray("weather").getJSONObject(0).getString("description")
                val formattedWeatherData = "Temperature: ${String.format("%.1f", temperatureInCelsius)} °C\nDescription: $weatherDescription"
                activity?.runOnUiThread {
                    weatherTextView.text = formattedWeatherData
                }
            }
        }


        val playButton: Button = view.findViewById(R.id.playButton)
        playButton.setOnClickListener(){
            Navigation.findNavController(view).navigate(R.id.action_homeScreen_to_firstQ)

        }

        return view
    }

    private fun getWeatherForCity(cityName: String, callback: (String?, Throwable?) -> Unit) {
        client.getWeatherForCity(cityName, callback)
    }


}
