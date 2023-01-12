package com.example.weatherapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.weatherapi.databinding.ActivityMainBinding
import com.example.weatherapi.model.weather
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
     lateinit var binding: ActivityMainBinding
     lateinit var weatherAdapter: WeatherAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val retrofit=RetrofitHelper.getInstanse()
        val call=retrofit.create(NetworkApi::class.java)
        weatherAdapter=WeatherAdapter()
        binding.recview.adapter=weatherAdapter
        binding.btnsearch.setOnClickListener {
            call.getWeather("3"," 9b48712078de40ea89655022231201",
                binding.Editname.text.toString(),
                "no"
            ).enqueue(object:Callback<weather>{
                override fun onResponse(call: Call<weather>, response: Response<weather>) {
                    val imgCurrent:String="https:"+response.body()!!.current.condition.icon
                Glide.with(this@MainActivity).load(imgCurrent).into(binding.imageview)
                val tempCurrent:String=response.body()!!.current.temp_c.toString()+"C"
                binding.degree.text=tempCurrent
                weatherAdapter.submitList(response.body()!!.forecast.forecastday)}

                override fun onFailure(call: Call<weather>, t: Throwable) {
                }
            })
        }
    }
}