package com.udacity.shoestore.screens.addproduct

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.udacity.shoestore.R
import com.udacity.shoestore.adapter.ImageViewPagerAdapter
import com.udacity.shoestore.databinding.FragmentAddProductBinding
import com.udacity.shoestore.models.AddProductViewModel
import com.udacity.shoestore.models.ProductListViewModel

class AddProductFragment : Fragment() {
    private val sharedViewModel: ProductListViewModel by activityViewModels()
    private lateinit var viewModel: AddProductViewModel
    private lateinit var pageAdapter: ImageViewPagerAdapter
    private lateinit var viewPager: ViewPager2
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding: FragmentAddProductBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_add_product, container, false)
        // init view model
        viewModel = ViewModelProvider(this).get(AddProductViewModel::class.java)
        // binding variable in layout to view model
        binding.prdComponent.addProductViewModel = viewModel
        // upload button
        binding.prdComponent.uploadButton.setOnClickListener {
            uploadImageFromDevice()
        }
        // get viewpager
        viewPager = binding.prdComponent.prdViewpager
        pageAdapter = ImageViewPagerAdapter(mutableListOf())
        viewPager.adapter = pageAdapter
        viewModel.newImage.observe(viewLifecycleOwner, Observer {
            Log.i("AddProductFragment", "image added")
            pageAdapter.addImage(it)
        })

        // config indicator color and update when pageChanged occur
        configIndicator(
            viewPager,
            binding.prdComponent.iv1,
            binding.prdComponent.iv2,
            binding.prdComponent.iv3
        )
        // button cancel
        binding.cancelButton.setOnClickListener { view: View ->
            view.findNavController()
                .navigate(AddProductFragmentDirections.actionAddProductFragmentToProductListFragment())
        }
        // button save
        binding.saveButton.setOnClickListener { view ->
            // share new product to shareViewModel
            sharedViewModel.addNewProduct(viewModel.productEntry)
            // navigate back to product list page
            view.findNavController()
                .navigate(AddProductFragmentDirections.actionAddProductFragmentToProductListFragment())
        }
        return binding.root
    }

    /**
     * handle viewPagers callback to update indicator color
     */
    private fun configIndicator(
        viewPager: ViewPager2,
        iv1: ImageView,
        iv2: ImageView,
        iv3: ImageView
    ) {
        viewPager.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                changeIndicatorColor(viewPager.currentItem, iv1, iv2, iv3)
            }

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                changeIndicatorColor(viewPager.currentItem, iv1, iv2, iv3)
            }

        })
    }

    // get image from gallery
    private fun uploadImageFromDevice() {
        getContent.launch("image/*")
    }

    /**
     * due to StartActivityForResult() deprecated, so get Image content via ActivityResultContracts
     */
    private val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) {
        viewModel.uploadImage(it)
    }

    /**
     * change color of indicator regarding to current viewed image on ViewPager
     */
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