package com.jay.wjshop.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.jay.wjshop.databinding.ItemGoodsBinding
import com.jay.wjshop.model.Goods
import com.jay.wjshop.ui.base.WJBaseListAdapter
import com.jay.wjshop.ui.base.WJBaseViewHolder

class RecentlyGoodsAdapter : WJBaseListAdapter<Goods>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WJBaseViewHolder<Goods> {
        return GoodsHolder(
            ItemGoodsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    class GoodsHolder(
        private val binding: ItemGoodsBinding
    ) : WJBaseViewHolder<Goods>(binding) {
        override fun bind(item: Goods) {
            binding.item = item
            binding.executePendingBindings()
        }

        override fun recycle() {
            binding.tvName.text = null
            Glide.with(binding.ivPoster).clear(binding.ivPoster)
        }
    }

}