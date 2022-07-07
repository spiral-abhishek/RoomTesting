package com.practice.roomtesting.database.schedule

import androidx.room.Dao
import androidx.room.Query

@Dao
interface BusScheduleDao {

    @Query("Select * from BusSchedule order by arrival_time")
    fun getAllBusSchedule(): kotlinx.coroutines.flow.Flow<List<BusSchedule>>

    @Query("Select * from BusSchedule where stop_name = :stopName order by arrival_time")
    fun getAllByStopName(stopName: String): kotlinx.coroutines.flow.Flow<List<BusSchedule>>
}