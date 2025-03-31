package com.example.alarmclock.data

class AlarmRepository {
    // In a real app, you'd use a database or other persistent storage here
    private val alarms = mutableListOf<Alarm>()

    fun getAlarms(): List<Alarm> {
        return alarms
    }

    fun addAlarm(alarm: Alarm) {
        alarms.add(alarm)
    }

    fun deleteAlarm(alarm: Alarm) {
        alarms.remove(alarm)
    }
}