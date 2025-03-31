package com.example.alarmclock

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class AlarmDismissReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        // Handle dismiss action
        val alarmId = intent.getIntExtra("ALARM_ID", -1)
        Toast.makeText(context, "Alarm dismissed with ID: $alarmId", Toast.LENGTH_SHORT).show()
        // Implement dismiss logic, e.g., stop any ongoing sound, etc.
    }
}