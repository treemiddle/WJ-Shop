package com.jay.wjshop.utils

import android.content.Context
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.jay.wjshop.R

fun getProgressDrawable(context: Context): CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 10f
        centerRadius = 50f
        start()
    }
}

fun getRecyclerAnimation(context: Context?): Animation? {
    return context?.let {
        AnimationUtils.loadAnimation(context, R.anim.anim_item_goods)
    } ?: run { null }
}

fun AppCompatActivity.removeAnimation() {
    this.overridePendingTransition(0, 0)
}