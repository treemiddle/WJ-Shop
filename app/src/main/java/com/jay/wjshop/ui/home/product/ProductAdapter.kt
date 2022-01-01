package com.jay.wjshop.ui.home.product

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.jay.wjshop.databinding.ItemProductBinding
import com.jay.wjshop.model.ShopSales
import com.jay.wjshop.ui.base.WJBaseListAdapter
import com.jay.wjshop.ui.base.WJBaseViewHolder

typealias ProductItemClick = (ShopSales) -> Unit

class ProductAdapter(
    private val onItemClick: ProductItemClick? = null
) : WJBaseListAdapter<ShopSales>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WJBaseViewHolder<ShopSales> {
        return ProductItemHolder(
            ItemProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ).also {
            if (onItemClick == null) {
                return@also
            } else {
                it.itemView.setOnClickListener { _ ->
                    val currentItem = currentList.getOrNull(it.adapterPosition) ?: return@setOnClickListener
                    onItemClick.invoke(currentItem)
                }
            }
        }
    }

    class ProductItemHolder(
        private val binding: ItemProductBinding
    ) : WJBaseViewHolder<ShopSales>(binding) {
        override fun bind(item: ShopSales) {
            binding.item = item
            binding.executePendingBindings()
        }

        override fun recycle() {
            try {
                with(binding) {
                    Glide.with(ivPoster).clear(ivPoster)
                    tvSoldOut.text = null
                    tvPreOrder.text = null
                    tvGoods.text = null
                    tvSalePrice.text = null
                    tvOriginPrice.text = null
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}