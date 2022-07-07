package com.practice.roomtesting.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.practice.roomtesting.database.schedule.BusSchedule
import com.practice.roomtesting.database.schedule.BusScheduleDao
import kotlinx.coroutines.flow.Flow
import java.lang.IllegalArgumentException

class BusScheduleViewModel(private val scheduleDao: BusScheduleDao):ViewModel() {

    fun fullSchedule(): Flow<List<BusSchedule>> = scheduleDao.getAllBusSchedule()

    fun fullScheduleByStopName(stopName: String): Flow<List<BusSchedule>> = scheduleDao.getAllByStopName(stopName)
}

class BusScheduleViewModelFactory(val scheduleDao: BusScheduleDao): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BusScheduleViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BusScheduleViewModel(scheduleDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }


}