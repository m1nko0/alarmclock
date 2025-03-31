package com.example.alarmclock.data

data class Alarm(
    val id: Int = 0, // You might want to use a more robust unique identifier
    val hour: Int,
    val minute: Int,
    val label: String = "",
    var isActive: Boolean = false
)