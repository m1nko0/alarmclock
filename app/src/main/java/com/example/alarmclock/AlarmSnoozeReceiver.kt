package com.example.alarmclock

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class AlarmSnoozeReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        // Handle snooze action
        Toast.makeText(context, "Alarm Snoozed", Toast.LENGTH_SHORT).show()
        // Add snooze logic here, e.g., reschedule the alarm for a few minutes later
    }
}