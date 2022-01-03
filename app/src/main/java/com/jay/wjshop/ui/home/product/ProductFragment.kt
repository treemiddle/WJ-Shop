package com.jay.wjshop.ui.home.product

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
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jay.wjshop.R
import com.jay.wjshop.databinding.FragmentProductBinding
import com.jay.wjshop.model.ShopSales
import com.jay.wjshop.ui.home.WJHomeViewModel
import com.jay.wjshop.utils.ext.shortToast
import com.jay.wjshop.utils.getRecyclerAnimation
import dagger.hilt.android.AndroidEntryPoint
import kotlin.jvm.internal.MagicApiIntrinsics

/**
 * [ProductFragment]
 * 선언형식이 아닌 펑션들 다 바인딩으로 뺴야함
 */
@AndroidEntryPoint
class ProductFragment : Fragment() {

    private val activityViewModel by activityViewModels<WJHomeViewModel>()
    private val viewModel by viewModels<ProductViewModel>()

    private lateinit var binding: FragmentProductBinding
    private val position by lazy { arguments?.getInt(FRAGMENT_POSITION) }
    private val adapter by lazy {
        ProductAdapter {
            if (it is ShopSales) {
                viewModel.onClickGoods(it to activityViewModel.getCurrentShop())
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_product,
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
        position?.let { viewModel.setProducts(activityViewModel.getProductList()[it]) }
    }

    private fun setupObserver() {
        with(viewModel) {
            salesList.observe(viewLifecycleOwner, {
                if (it.isNotEmpty()) {
                    adapter.submitList(it)
                } else {
                    adapter.submitList(null)
                }
            })
            toast.observe(viewLifecycleOwner, {
                it.getContentIfNotHandled()?.let {
                    context?.shortToast(
                        String.format(
                            requireContext().getString(R.string.selected_goods),
                            "${viewModel.getRecentlyGoods().first.name}"
                        )
                    )
                }
            })
        }
    }

    private fun initAdapter() {
        binding.rvGoods.adapter = adapter
        binding.rvGoods.animation = getRecyclerAnimation(context)
        val gridLayoutManager = GridLayoutManager(context, 2)
        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when (adapter.getItemViewType(position)) {
                    ProductAdapter.MAIN -> 1
                    else -> 2
                }
            }
        }
        binding.rvGoods.layoutManager = gridLayoutManager
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
        private const val FRAGMENT_POSITION = "fragment_position"

        fun newInstance(position: Int) = ProductFragment().apply {
            arguments = bundleOf(
                FRAGMENT_POSITION to position
            )
        }
    }

}