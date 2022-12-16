package com.udacity.shoestore.screens.instruction

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.udacity.shoestore.R
import com.udacity.shoestore.adapter.InstructionPageAdapter
import com.udacity.shoestore.databinding.FragmentInstructionBinding
import com.udacity.shoestore.models.InstructionPageData
import com.udacity.shoestore.models.InstructionViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [InstructionFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class InstructionFragment : Fragment() {
    private lateinit var iv1: ImageView
    private lateinit var iv2: ImageView
    private lateinit var iv3: ImageView
    private lateinit var viewPager2: ViewPager2
    private lateinit var viewModel: InstructionViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding: FragmentInstructionBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_instruction, container, false)
        //navigate to Product List Page
        binding.goShoppingButton.setOnClickListener { view: View ->
            view.findNavController()
                .navigate(InstructionFragmentDirections.actionInstructionFragmentToProductListFragment())
        }
        // get viewpager
        viewPager2 = binding.viewPagers
        iv1 = binding.iv1
        iv2 = binding.iv2
        iv3 = binding.iv3
        viewModel = ViewModelProvider(this).get(InstructionViewModel::class.java)
        // create adapter
        val adapter = InstructionPageAdapter(viewModel.instructionPages)
        // hook adapter to view pager
        viewPager2.adapter = adapter
        // setting callback for coloring indicator
        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                changeIndicatorColor()
            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
                changeIndicatorColor()
            }

        })
        return binding.root
    }

    private fun changeIndicatorColor() {
        when (viewPager2.currentItem) {
            0 -> {
                iv1.setBackgroundColor(resources.getColor(R.color.active))
                iv2.setBackgroundColor(resources.getColor(R.color.grey))
                iv3.setBackgroundColor(resources.getColor(R.color.grey))
            }
            1 -> {
                iv1.setBackgroundColor(resources.getColor(R.color.grey))
                iv2.setBackgroundColor(resources.getColor(R.color.active))
                iv3.setBackgroundColor(resources.getColor(R.color.grey))
            }
            2 -> {
                iv1.setBackgroundColor(resources.getColor(R.color.grey))
                iv2.setBackgroundColor(resources.getColor(R.color.grey))
                iv3.setBackgroundColor(resources.getColor(R.color.active))
            }
            else -> {
                iv1.setBackgroundColor(resources.getColor(R.color.grey))
                iv2.setBackgroundColor(resources.getColor(R.color.grey))
                iv3.setBackgroundColor(resources.getColor(R.color.grey))
            }
        }
    }
}