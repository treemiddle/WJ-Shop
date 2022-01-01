package com.jay.wjshop.utils.bindingadapter

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.jay.wjshop.utils.ext.loadImage
import com.jay.wjshop.utils.getProgressDrawable

@BindingAdapter("setGoodsImageUrl")
fun bindGoodsImageUrl(iv: ImageView, path: String?) {
    iv.loadImage(path, getProgressDrawable(iv.context))
}

@BindingAdapter("setGoodsName")
fun bindGoodsName(tv: TextView, name: String) {
    tv.text = name
}