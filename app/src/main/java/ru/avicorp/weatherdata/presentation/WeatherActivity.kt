package ru.avicorp.weatherdata

import android.R
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import ru.avicorp.weatherdata.app.App
import ru.avicorp.weatherdata.databinding.ActivityMainBinding
import ru.avicorp.weatherdata.presentation.WeatherApi
import ru.avicorp.weatherdata.presentation.WeatherViewModel
import ru.avicorp.weatherdata.presentation.WeatherViewModelFactory

const val ICON_URL = "https://openweathermap.org/img/wn/%s@2x.png"

class WeatherActivity : AppCompatActivity() {

    lateinit var vmFactory: WeatherViewModelFactory
    private lateinit var binding: ActivityMainBinding

    private lateinit var vm:WeatherViewModel

    var weatherData: ArrayList<String> = ArrayList()
    var adapter: ArrayAdapter<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val api = (application as App).retrofit.create(WeatherApi::class.java)
        //vm = ViewModelProvider(this,vmFactory).get(WeatherViewModel::class.java)

        vm = ViewModelProvider(
            this,
            WeatherViewModelFactory(api)
        ).get(WeatherViewModel::class.java)
        vm.data.observe(this) { data ->

            weatherData.clear()
            weatherData.add("source: openweathermap.org")
            weatherData.add("city: ${data.name}")
            weatherData.add("latitude: ${data.coord.lat}")
            weatherData.add("longitude: ${data.coord.lon}")
            weatherData.add("temp: ${data.main.temp}")
            weatherData.add("feelsLike: ${data.main.feelsLike}")
            weatherData.add("humidity: ${data.main.humidity}")
            weatherData.add("pressure: ${data.main.pressure}")
            weatherData.add("tempMax: ${data.main.tempMax}")
            weatherData.add("tempMin: ${data.main.tempMin}")

            adapter = ArrayAdapter<String>(
                this,
                R.layout.simple_list_item_1, weatherData
            )

            binding.listView.setAdapter(adapter)

           Picasso.get()
                .load(String.format(ICON_URL,data.weather[0].icon)).resize(250,250)
                .into(binding.iconWeather)
        }

    }

    override fun onResume() {
        super.onResume()
    }

    fun getWeatherData(view: View) {
        try {
            vm.loadWeather()
        }
        catch (ex: Exception) {
            Log.e("Exception", "$ex")
        }

    }
}