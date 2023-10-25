package com.example.getdatafromjsonaset.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.getdatafromjsonaset.R
import com.example.getdatafromjsonaset.databinding.ActivityMainBinding
import com.example.getdatafromjsonaset.model.DataItemCarResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.InputStream

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    private var itemsDataCar: MutableList<DataItemCarResponse> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initData()
    }

    private fun initData(){
        getDataFromJson()
        loadData()
    }

    private fun getDataFromJson(){

        val inputStream: InputStream = this.assets.open("data_list_car.json")
        val inputString = inputStream.bufferedReader().use{it.readText()}
        val data: List<DataItemCarResponse> = Gson().fromJson(
            inputString,
            object : TypeToken<List<DataItemCarResponse?>?>() {}.type
        )

        itemsDataCar.clear()
        itemsDataCar.addAll(data)
    }

    private fun loadData(){

        var dataString: String? = null

        for (item in itemsDataCar){
            if(dataString.isNullOrBlank()){
                dataString = item.carId+ ". "+item.carName+"\n"
            }else{
                dataString = dataString +item.carId+ ". "+item.carName+"\n"
            }
        }

        binding.tvDataList.text = dataString
    }
}