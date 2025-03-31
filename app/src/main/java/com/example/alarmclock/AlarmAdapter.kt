package com.example.alarmclock

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.alarmclock.data.Alarm
import com.example.alarmclock.databinding.ItemAlarmBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class AlarmAdapter(
    private val alarms: List<Alarm>,
    private val onToggleAlarm: (Alarm) -> Unit,
    private val onDeleteAlarm: (Alarm) -> Unit
) : RecyclerView.Adapter<AlarmAdapter.AlarmViewHolder>() {

    class AlarmViewHolder(val binding: ItemAlarmBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlarmViewHolder {
        val binding = ItemAlarmBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AlarmViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AlarmViewHolder, position: Int) {
        val alarm = alarms[position]
        val calendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, alarm.hour)
            set(Calendar.MINUTE, alarm.minute)
        }
        val timeFormat = SimpleDateFormat("hh:mm a", Locale.getDefault())
        holder.binding.timeTextView.text = timeFormat.format(calendar.time)
        holder.binding.labelTextView.text = alarm.label
        holder.binding.alarmSwitch.isChecked = alarm.isActive

        holder.binding.alarmSwitch.setOnCheckedChangeListener { _, isChecked ->
            alarm.isActive = isChecked
            onToggleAlarm(alarm)
        }
        holder.binding.root.setOnLongClickListener {
            onDeleteAlarm(alarm)
            true
        }
    }

    override fun getItemCount(): Int = alarms.size
}