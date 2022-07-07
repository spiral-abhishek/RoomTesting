package com.practice.roomtesting

import android.app.Application
import com.practice.roomtesting.database.AppDatabase

class BusScheduleApplication: Application() {

    val database : AppDatabase by lazy { AppDatabase.getDataBase(this) }
}