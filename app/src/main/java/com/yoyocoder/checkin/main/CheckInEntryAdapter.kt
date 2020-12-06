package com.yoyocoder.checkin.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yoyocoder.checkin.R
import com.yoyocoder.checkin.databinding.CheckInEntryItemBinding
import com.yoyocoder.checkin.main.CheckInEntryAdapter.CheckInEntryViewHolder
import com.yoyocoder.checkin.model.CheckInEntry

class CheckInEntryAdapter(
    private val onItemLongClicked: (Int) -> Unit
) : ListAdapter<CheckInEntry, CheckInEntryViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheckInEntryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.check_in_entry_item,
            parent,
            false
        )
        return CheckInEntryViewHolder(view, onItemLongClicked)
    }

    override fun onBindViewHolder(holder: CheckInEntryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class CheckInEntryViewHolder(
        view: View,
        val onItemLongClicked: (Int) -> Unit
    ) : RecyclerView.ViewHolder(view) {
        private val viewBinding: CheckInEntryItemBinding = CheckInEntryItemBinding.bind(view)

        fun bind(checkInEntry: CheckInEntry) {
            viewBinding.checkInEntryName.text = checkInEntry.name
            viewBinding.checkInEntryName.setOnLongClickListener {
                onItemLongClicked(checkInEntry.id!!)
                true
            }
        }
    }

    companion object {
        private var DIFF_CALLBACK = object : DiffUtil.ItemCallback<CheckInEntry>() {
            override fun areItemsTheSame(
                oldItem: CheckInEntry,
                newItem: CheckInEntry
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: CheckInEntry,
                newItem: CheckInEntry
            ): Boolean {
                return oldItem.name == newItem.name
            }
        }
    }
}