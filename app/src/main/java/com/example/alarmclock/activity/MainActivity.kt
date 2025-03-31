package com.example.alarmclock.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.alarmclock.AlarmAdapter
import com.example.alarmclock.data.Alarm
import com.example.alarmclock.data.AlarmRepository
import com.example.alarmclock.databinding.ActivityMainBinding
import com.example.alarmclock.utils.AlarmScheduler

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var alarmRepository: AlarmRepository
    private lateinit var alarmScheduler: AlarmScheduler
    private lateinit var adapter: AlarmAdapter
    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                // Permission is granted. Continue the action or workflow in your
                // app.
                Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show()
            } else {
                // Explain to the user that the feature is unavailable because the
                // feature requires a permission that the user has denied. At the
                // same time, respect the user's decision. Don't link to system
                // settings in an effort to convince the user to change their
                // decision.
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        alarmRepository = AlarmRepository()
        alarmScheduler = AlarmScheduler(this)

        adapter = AlarmAdapter(alarmRepository.getAlarms(), { alarm ->
            alarm.isActive = !alarm.isActive
            if (alarm.isActive) {
                alarmScheduler.schedule(alarm)
            } else {
                alarmScheduler.cancel(alarm)
            }
        }, { alarm ->
            alarmRepository.deleteAlarm(alarm)
            adapter.notifyDataSetChanged()
        })

        binding.alarmsRecyclerView.adapter = adapter
        binding.alarmsRecyclerView.layoutManager = LinearLayoutManager(this)

        binding.addAlarmFab.setOnClickListener {
            val intent = Intent(this, EditAlarmActivity::class.java)
            startActivity(intent)
        }
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show()
        } else if (shouldShowRequestPermissionRationale(Manifest.permission.POST_NOTIFICATIONS)){
            Toast.makeText(this, "Permission is required to send notifications", Toast.LENGTH_SHORT).show()
            requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
        }else{
            requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
        }
    }
    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
    }
}