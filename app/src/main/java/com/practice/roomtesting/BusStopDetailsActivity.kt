package com.practice.roomtesting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.coroutineScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.practice.roomtesting.adapters.BusScheduleAdapter
import com.practice.roomtesting.databinding.ActivityBusStopDetailsBinding
import com.practice.roomtesting.viewmodel.BusScheduleViewModel
import com.practice.roomtesting.viewmodel.BusScheduleViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class BusStopDetailsActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityBusStopDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel : BusScheduleViewModel by viewModels {
            BusScheduleViewModelFactory(
                (this.application as BusScheduleApplication).database.busScheduleDao()
            )
        }

        val stopName = intent.getStringExtra("action").toString()
        val rv = binding.recyclerView
        rv.layoutManager = LinearLayoutManager(this)
        val busScheduleAdapter = BusScheduleAdapter({})
        rv.adapter = busScheduleAdapter


        lifecycle.coroutineScope.launch {
            viewModel.fullScheduleByStopName(stopName).collect() {
                busScheduleAdapter.submitList(it)
            }
        }
    }
}