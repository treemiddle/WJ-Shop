package com.jay.wjshop.utils.ext

import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.jay.wjshop.R

fun ImageView.loadImage(path: String?, circularProgress: CircularProgressDrawable) {
    Glide.with(context)
        .load(path)
        .apply(
            RequestOptions()
            .placeholder(circularProgress)
            .error(R.drawable.ic_error))
        .into(this)
}