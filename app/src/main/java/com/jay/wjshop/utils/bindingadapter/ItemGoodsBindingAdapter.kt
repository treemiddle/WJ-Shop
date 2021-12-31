package com.jay.wjshop.utils.bindingadapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.jay.wjshop.R
import com.jay.wjshop.utils.ext.loadImage
import com.jay.wjshop.utils.getProgressDrawable

@BindingAdapter("setImageUrl")
fun bindImageUrl(iv: ImageView, path: String?) {
    iv.loadImage(path, getProgressDrawable(iv.context))
}

@BindingAdapter("setSoldOut")
fun bindSoldOut(tv: TextView, isSoldOut: Boolean) = if (isSoldOut) {
    tv.visibility = View.VISIBLE
    tv.background = ContextCompat.getDrawable(tv.context, R.drawable.bg_sold_out)
    tv.text = tv.context.getString(R.string.goods_sold_out)
} else {
    tv.visibility = View.GONE
    tv.background = null
    tv.text = null
}

@BindingAdapter("setPreOrder")
fun bindPreOrder(tv: TextView, isPreOrder: Boolean) = if (isPreOrder) {
    tv.visibility = View.VISIBLE
    tv.text = tv.context.getString(R.string.goods_pre_order)
} else {
    tv.visibility = View.GONE
    tv.text = null
}

@BindingAdapter("setSalePrice")
fun bindSalePrice(tv: TextView, salePrice: Int) {
    tv.text = salePrice.toString()
}

@BindingAdapter("setOriginalPrice", "setSalePrice")
fun bindOriginalAndSalePrice(tv: TextView, originalPrice: Int, salePrice: Int) {
    if (originalPrice == salePrice) {
        tv.visibility = View.GONE
        tv.text = null
    } else {
        tv.visibility = View.VISIBLE
        tv.text = originalPrice.toString()
    }
}