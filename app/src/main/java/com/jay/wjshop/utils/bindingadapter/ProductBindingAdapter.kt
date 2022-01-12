package com.jay.wjshop.utils.bindingadapter

import android.graphics.Rect
import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jay.wjshop.model.ShopSalesModel
import com.jay.wjshop.ui.home.product.ProductAdapter
import com.jay.wjshop.utils.getRecyclerAnimation

@BindingAdapter("setProductList")
fun RecyclerView.bindProductList(products: List<ShopSalesModel>?) {
    animation = getRecyclerAnimation(context)
    layoutManager = getLayoutManager(this)

    addItemDecoration(object : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            super.getItemOffsets(outRect, view, parent, state)

            outRect.top = 10
            outRect.bottom = 10
            outRect.left = 10
            outRect.right = 10
        }
    })

    with((adapter as ProductAdapter)) {
        if (products.isNullOrEmpty()) {
            this.submitList(null)
        } else {
            this.submitList(products)
        }
    }

}

private fun getLayoutManager(recyclerView: RecyclerView): GridLayoutManager {
    return GridLayoutManager(recyclerView.context, 2).apply {
        spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when (recyclerView.adapter?.getItemViewType(position)) {
                    ProductAdapter.MAIN -> 1
                    else -> 2
                }
            }
        }
    }
}