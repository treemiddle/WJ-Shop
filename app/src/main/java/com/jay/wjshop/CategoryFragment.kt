package com.jay.wjshop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.jay.wjshop.databinding.FragmentCategoryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryFragment : Fragment() {

    private lateinit var binding: FragmentCategoryBinding

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

        binding.lifecycleOwner = this

        arguments?.getInt("test")?.let {
            binding.tvId.text = it.toString()
        }
    }

    companion object {
        fun newInstance(id: Int): CategoryFragment {
            val fragment = CategoryFragment().apply {
                arguments = Bundle().also {
                    //it.putParcelableArrayList("", goodsList as ArrayList)
                    it.putInt("test", id)
                }
            }

            return fragment
        }
    }

}