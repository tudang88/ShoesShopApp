package com.udacity.shoestore.screens.productlist

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.udacity.shoestore.R
import com.udacity.shoestore.adapter.ImageViewPagerAdapter
import com.udacity.shoestore.databinding.FragmentProductListBinding
import com.udacity.shoestore.databinding.ProductItemsBinding
import com.udacity.shoestore.models.ProductListViewModel
import com.udacity.shoestore.models.Shoe

class ProductListFragment : Fragment() {
    private lateinit var scrollLinearLayout: LinearLayout
    private lateinit var prdScrollList: ScrollView
    private lateinit var binding: FragmentProductListBinding

    // use share ViewModel with activity lifecycle
    private val shareViewModel: ProductListViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_product_list, container, false)
        binding.fab.setOnClickListener {
            it.findNavController()
                .navigate(ProductListFragmentDirections.actionProductListFragmentToAddProductFragment())
        }
        // setting view pager
        prdScrollList = binding.productScroll
        scrollLinearLayout = binding.productListBoard
        // draw product list base on data from model
        genProductList(shareViewModel.listProduct)
        // enable option menu
        setHasOptionsMenu(true)
        return binding.root
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.logout_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.logout -> {
                Log.i("ProductListFragment", "Press Logout icon")
                findNavController().navigate(ProductListFragmentDirections.actionProductListFragmentToLoginFragment())
            }
        }
        return super.onOptionsItemSelected(item)
    }

    /**
     * gen product layout row by row
     */
    private fun genProductList(listProduct: MutableList<Shoe>) {
        for (item in listProduct) {
            // create layout and add to linear layout on scroll
            val row :ProductItemsBinding = DataBindingUtil.inflate(layoutInflater, R.layout.product_items, null, false)
            val name: TextView = row.prdNameText
            val size: TextView = row.prdSizeText
            val company: TextView = row.prdCompanyText
            val description: TextView = row.prdDescriptionText
            name.text = item.name
            size.text = resources.getString(R.string.product_size, item.size)
            company.text = resources.getString(R.string.Company_name, item.company)
            description.text = item.description
            // update product image to viewpager
            val viewPager: ViewPager2 = row.prdViewpager
            val adapter = ImageViewPagerAdapter(item.images)
            viewPager.adapter = adapter
            // add product row
            scrollLinearLayout.addView(row.prdItemRoot)
            // setting for update indicator
            val iv1: ImageView = row.iv1
            val iv2: ImageView = row.iv2
            val iv3: ImageView = row.iv3
            viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {
                    super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                    changeIndicatorColor(viewPager.currentItem, iv1, iv2, iv3)
                }

                override fun onPageScrollStateChanged(state: Int) {
                    super.onPageScrollStateChanged(state)
                    changeIndicatorColor(viewPager.currentItem, iv1, iv2, iv3)
                }
            })
        }
    }

    private fun changeIndicatorColor(index: Int, iv1: ImageView, iv2: ImageView, iv3: ImageView) {
        when (index % 3) {
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