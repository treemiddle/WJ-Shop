package com.jay.wjshop.ui.home.product

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.jay.wjshop.databinding.ItemProductBinding
import com.jay.wjshop.databinding.ItemProductButtonBinding
import com.jay.wjshop.model.ShopSales
import com.jay.wjshop.model.ShopSalesButton
import com.jay.wjshop.model.ShopSalesModel
import com.jay.wjshop.ui.base.WJBaseListAdapter
import com.jay.wjshop.ui.base.WJBaseViewHolder

typealias ProductItemClick = (ShopSalesModel) -> Unit

class ProductAdapter(
    private val onItemClick: ProductItemClick? = null
) : WJBaseListAdapter<ShopSalesModel>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WJBaseViewHolder<ShopSalesModel> {
        return when (viewType) {
            MAIN -> {
                ProductItemHolder(
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
            BUTTON -> {
                ProductButtonItemHolder(
                    ItemProductButtonBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            else -> throw IllegalStateException("don't make create view holder")
        }
    }

//    override fun onBindViewHolder(holder: WJBaseViewHolder<ShopSalesModel>, position: Int) {
//        if (holder is ProductItemHolder) {
//            holder.bind(currentList[position])
//        } else {
//            val layoutParams = StaggeredGridLayoutManager.LayoutParams(
//                ViewGroup.LayoutParams.MATCH_PARENT,
//                ViewGroup.LayoutParams.WRAP_CONTENT
//            ).apply {
//                isFullSpan = true
//            }
//
//            holder.itemView.layoutParams = layoutParams
//        }
//    }

    override fun getItemViewType(position: Int) = when (currentList[position]) {
        is ShopSales -> MAIN
        is ShopSalesButton -> BUTTON
        else -> throw IllegalStateException("can't find view type")
    }

    class ProductItemHolder(
        private val binding: ItemProductBinding
    ) : WJBaseViewHolder<ShopSalesModel>(binding) {
        override fun bind(item: ShopSalesModel) {
            if (item is ShopSales) {
                binding.item = item
                binding.executePendingBindings()
            }
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

    class ProductButtonItemHolder(
        binding: ItemProductButtonBinding
    ) : WJBaseViewHolder<ShopSalesModel>(binding) {
        override fun bind(item: ShopSalesModel) {

        }

        override fun recycle() {

        }
    }

    companion object {
        const val MAIN = 0
        const val BUTTON = 1
    }
}