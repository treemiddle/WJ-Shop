package com.jay.wjshop.ui

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.jay.wjshop.R
import com.jay.wjshop.databinding.FragmentCategoryBinding
import com.jay.wjshop.utils.ext.shortToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryFragment : Fragment() {

    private val activityViewModel by activityViewModels<WJViewModel>()
    private val viewModel by viewModels<CategoryViewModel>()

    private lateinit var binding: FragmentCategoryBinding
    private val position by lazy { arguments?.getInt(POSITION) }
    private val adapter by lazy {
        CategoryAdapter {
            context?.shortToast(String.format(requireContext().getString(R.string.selected_goods), it.name))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_category,
            container,
            false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupBinding()
        setupObserver()
        initAdapter()
    }

    private fun setupBinding() {
        binding.vm = viewModel
        binding.lifecycleOwner = this
        position?.let { viewModel.setGoodsItem(activityViewModel.getGoodsList()[it]) }
    }

    private fun setupObserver() {
        with(viewModel) {
            goodsItem.observe(viewLifecycleOwner, {
                adapter.submitList(it)
            })
        }
    }

    private fun initAdapter() {
        binding.rvGoods.adapter = adapter
        binding.rvGoods.addItemDecoration(object : RecyclerView.ItemDecoration() {
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
    }

    companion object {
        private const val POSITION = "position"

        fun newInstance(position: Int) = CategoryFragment().apply {
            arguments = bundleOf(
                POSITION to position
            )
        }
    }

}