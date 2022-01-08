package com.jay.wjshop.ui.home.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.jay.wjshop.R
import com.jay.wjshop.databinding.FragmentProductBinding
import com.jay.wjshop.model.ShopSales
import com.jay.wjshop.ui.home.WJHomeViewModel
import com.jay.wjshop.utils.ext.shortToast
import dagger.hilt.android.AndroidEntryPoint

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
        initData()
        initAdapter()
    }

    private fun setupBinding() {
        binding.vm = viewModel
        binding.lifecycleOwner = this
    }

    private fun setupObserver() {
        with(viewModel) {
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

    private fun initData() {
        position?.let { viewModel.setProducts(activityViewModel.getProductList()[it]) }
    }

    private fun initAdapter() = with(binding) {
        rvGoods.adapter = adapter
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