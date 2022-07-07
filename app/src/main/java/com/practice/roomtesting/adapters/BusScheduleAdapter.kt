package com.practice.roomtesting.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.practice.roomtesting.database.schedule.BusSchedule
import com.practice.roomtesting.databinding.BusScheduleItemBinding
import java.text.SimpleDateFormat
import java.util.*

class BusScheduleAdapter(private val onItemClicked:(BusSchedule)-> Unit) : ListAdapter<BusSchedule,BusScheduleAdapter.BusScheduleViewHolder>(
    BusScheduleDiffCallback){

     class BusScheduleViewHolder(private var binding: BusScheduleItemBinding): RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SimpleDateFormat")
        fun bind(busSchedule: BusSchedule) {
            binding.stopNameTextView.text = busSchedule.stopName
            binding.arrivalTimeTextView.text = SimpleDateFormat("h:mm:a").format(Date(busSchedule.arrivalTime.toLong()*1000))
        }
    }

    companion object {

        private val BusScheduleDiffCallback = object : DiffUtil.ItemCallback<BusSchedule>() {
            override fun areItemsTheSame(oldItem: BusSchedule, newItem: BusSchedule): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: BusSchedule, newItem: BusSchedule): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusScheduleViewHolder {
        val viewHolder = BusScheduleViewHolder(BusScheduleItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))

        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.absoluteAdapterPosition
            onItemClicked(getItem(position))

        }

        return viewHolder
    }

    override fun onBindViewHolder(holder: BusScheduleViewHolder, position: Int) {
        holder.bind(getItem(position))
    }




}