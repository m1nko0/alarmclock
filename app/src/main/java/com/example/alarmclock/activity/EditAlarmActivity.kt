package com.example.alarmclock.activity

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.alarmclock.data.Alarm
import com.example.alarmclock.data.AlarmRepository
import com.example.alarmclock.databinding.ActivityEditAlarmBinding
import com.example.alarmclock.utils.AlarmScheduler

class EditAlarmActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditAlarmBinding
    private lateinit var alarmRepository: AlarmRepository
    private lateinit var alarmScheduler: AlarmScheduler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditAlarmBinding.inflate(layoutInflater)
        setContentView(binding.root)

        alarmRepository = AlarmRepository()
        alarmScheduler = AlarmScheduler(this)

        binding.saveButton.setOnClickListener {
            val hour = binding.timePicker.hour
            val minute = binding.timePicker.minute
            val label = binding.labelEditText.text.toString()

            val alarm = Alarm(
                id = (0..Int.MAX_VALUE).random(), // Generate a random ID
                hour = hour,
                minute = minute,
                label = label
            )
            Log.d("EditAlarmActivity", "Alarm saved ${alarm.id} at $hour:$minute")
            alarmRepository.addAlarm(alarm)
            alarmScheduler.schedule(alarm)

            Toast.makeText(this, "Alarm set for $hour:$minute", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}