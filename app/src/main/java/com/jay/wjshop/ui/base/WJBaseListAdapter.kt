package com.jay.wjshop.ui.base

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

abstract class WJBaseListAdapter<T> :
    ListAdapter<T, WJBaseViewHolder<T>>(object : DiffUtil.ItemCallback<T>() {
        override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
            return oldItem == newItem
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
            return oldItem == newItem
        }
    }) {

    override fun onBindViewHolder(holder: WJBaseViewHolder<T>, position: Int) {
        holder.bind(currentList[position])
    }

}