package com.practice.roomtesting

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.coroutineScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.practice.roomtesting.adapters.BusScheduleAdapter
import com.practice.roomtesting.databinding.ActivityMainBinding
import com.practice.roomtesting.viewmodel.BusScheduleViewModel
import com.practice.roomtesting.viewmodel.BusScheduleViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: BusScheduleViewModel by viewModels {

            BusScheduleViewModelFactory(
                (this.application as BusScheduleApplication).database.busScheduleDao()
            )
        }

        val rv = binding.recyclerView
        rv.layoutManager = LinearLayoutManager(this)
        val busScheduleAdapter = BusScheduleAdapter {
            val busStopDetailsActivity = Intent(this, BusStopDetailsActivity::class.java)
            busStopDetailsActivity.putExtra("action", it.stopName)
            startActivity(busStopDetailsActivity)
        }
        rv.adapter = busScheduleAdapter

        lifecycle.coroutineScope.launch {
            viewModel.fullSchedule().collect() {
                busScheduleAdapter.submitList(it)
            }
        }
    }
}