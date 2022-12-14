package com.udacity.shoestore.screens.productlist

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentProductListBinding
import com.udacity.shoestore.models.Shoe

/**
 * A simple [Fragment] subclass.
 * Use the [ProductListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProductListFragment : Fragment() {
    private lateinit var scrollLinearLayout: LinearLayout
    private lateinit var prdScrollList: ScrollView
    private val listProduct = mutableListOf(
        Shoe(
            "Nike Sport 1",
            26.0,
            "Nike Co.ltd",
            "The best selection for outdoor activities or hi-intensive training",
            mutableListOf(
                R.drawable.shoes1,
                R.drawable.shoes2,
                R.drawable.shoes3,
                R.drawable.shoes4,
                R.drawable.shoes5,
                R.drawable.shoes6,
                R.drawable.shoes7,
                R.drawable.shoes8,
                R.drawable.shoes9
            )
        ),
        Shoe(
            "Nike Sport 2",
            26.0,
            "Nike Co.ltd",
            "The best selection for outdoor activities or hi-intensive training",
            mutableListOf(
                R.drawable.shoes1,
                R.drawable.shoes2,
                R.drawable.shoes3,
                R.drawable.shoes4,
                R.drawable.shoes5,
                R.drawable.shoes6,
                R.drawable.shoes7,
                R.drawable.shoes8,
                R.drawable.shoes9
            )
        ),
        Shoe(
            "Nike Sport 3",
            26.0,
            "Nike Co.ltd",
            "The best selection for outdoor activities or hi-intensive training",
            mutableListOf(
                R.drawable.shoes1,
                R.drawable.shoes2,
                R.drawable.shoes3,
                R.drawable.shoes4,
                R.drawable.shoes5,
                R.drawable.shoes6,
                R.drawable.shoes7,
                R.drawable.shoes8,
                R.drawable.shoes9
            )
        ), Shoe(
            "Nike Sport 4",
            26.0,
            "Nike Co.ltd",
            "The best selection for outdoor activities or hi-intensive training",
            mutableListOf(
                R.drawable.shoes1,
                R.drawable.shoes2,
                R.drawable.shoes3,
                R.drawable.shoes4,
                R.drawable.shoes5,
                R.drawable.shoes6,
                R.drawable.shoes7,
                R.drawable.shoes8,
                R.drawable.shoes9
            )
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentProductListBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_product_list, container, false)
        binding.fab.setOnClickListener{
            it.findNavController().navigate(ProductListFragmentDirections.actionProductListFragmentToAddProductFragment())
        }
        // setting view pager
        prdScrollList = binding.productScroll
        scrollLinearLayout = binding.productListBoard
        for (item in listProduct) {
            // create layout and add to linear layout on scroll
            val row: View = layoutInflater.inflate(R.layout.product_items, null, false)
            val name: TextView = row.findViewById(R.id.prd_name_text)
            val size: TextView = row.findViewById(R.id.prd_size_text)
            val company: TextView = row.findViewById(R.id.prd_company_text)
            val description: TextView = row.findViewById(R.id.prd_description_text)
            name.text = item.name
            size.text = resources.getString(R.string.product_size, item.size)
            company.text = resources.getString(R.string.Company_name, item.company)
            description.text = item.description
            scrollLinearLayout.addView(row)
            val viewPager: ViewPager2 = row.findViewById(R.id.prd_viewpager)
            val adapter = ProductPageAdapter(item.images.shuffled())
            viewPager.adapter = adapter
            val iv1: ImageView = row.findViewById(R.id.iv1)
            val iv2: ImageView = row.findViewById(R.id.iv2)
            val iv3: ImageView = row.findViewById(R.id.iv3)

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

        // enable option menu
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.logout_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        findNavController().navigate(ProductListFragmentDirections.actionProductListFragmentToLoginFragment())
        return super.onOptionsItemSelected(item)
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