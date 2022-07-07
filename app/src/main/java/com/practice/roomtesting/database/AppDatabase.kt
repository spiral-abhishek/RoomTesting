package com.practice.roomtesting.database

import android.content.Context
import androidx.room.*
import com.practice.roomtesting.database.schedule.BusSchedule
import com.practice.roomtesting.database.schedule.BusScheduleDao

@Database(entities = [BusSchedule::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun busScheduleDao() : BusScheduleDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDataBase(context: Context): AppDatabase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "app_database")
                    .createFromAsset("database/bus_schedule.db")
                    .build()

                INSTANCE = instance

                instance
            }
        }

    }


}