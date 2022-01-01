package com.jay.wjshop.ui.home.product

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jay.wjshop.databinding.ItemProductBinding
import com.jay.wjshop.model.ShopSales

typealias GoodsItemClick = (ShopSales) -> Unit

class ProductAdapter(
    private val onItemClick: GoodsItemClick? = null
) : ListAdapter<ShopSales, ProductAdapter.GoodsItemHolder>(object : DiffUtil.ItemCallback<ShopSales>() {
    override fun areItemsTheSame(oldItem: ShopSales, newItem: ShopSales): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: ShopSales, newItem: ShopSales): Boolean {
        return oldItem == newItem
    }
}) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoodsItemHolder {
        return GoodsItemHolder.create(parent).also {
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

    override fun onBindViewHolder(holder: GoodsItemHolder, position: Int) {
        holder.bind(currentList[position])
    }

    override fun onViewRecycled(holder: GoodsItemHolder) {
        holder.recycle()
        super.onViewRecycled(holder)
    }

    class GoodsItemHolder(
        private val binding: ItemProductBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ShopSales) {
            binding.item = item
            binding.executePendingBindings()
        }

        fun recycle() = with(binding) {
            try {
                Glide.with(this.ivPoster).clear(this.ivPoster)
                tvSoldOut.text = null
                tvPreOrder.text = null
                tvGoods.text = null
                tvSalePrice.text = null
                tvOriginPrice.text = null
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        companion object Factory {
            fun create(parent: ViewGroup): GoodsItemHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = ItemProductBinding.inflate(layoutInflater, parent, false)

                return GoodsItemHolder(view)
            }
        }

    }

}