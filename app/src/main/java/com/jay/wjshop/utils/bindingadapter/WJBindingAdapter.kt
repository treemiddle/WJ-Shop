package com.jay.wjshop.utils.bindingadapter

import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.jay.wjshop.R
import com.jay.wjshop.model.ShopInfo

@BindingAdapter("setShopImage")
fun bindShopImage(iv: ImageView, shop: ShopInfo?) {
    shop ?: return

    iv.background = ContextCompat.getDrawable(iv.context, R.drawable.ic_check)
}