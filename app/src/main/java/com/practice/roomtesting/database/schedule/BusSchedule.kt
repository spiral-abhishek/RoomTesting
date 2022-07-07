package com.practice.roomtesting.database.schedule

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BusSchedule(
    @PrimaryKey val id: Int,

    @NonNull @ColumnInfo(name = "stop_name") val stopName: String,

    @NonNull @ColumnInfo(name = "arrival_time") val arrivalTime: Int
)
