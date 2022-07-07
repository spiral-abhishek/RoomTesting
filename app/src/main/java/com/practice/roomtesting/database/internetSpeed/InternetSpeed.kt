package com.practice.roomtesting.database.internetSpeed

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "internet_speed")
data class InternetSpeed(

    @PrimaryKey(autoGenerate = true) val id: Int,

    @ColumnInfo val data: Long
)
