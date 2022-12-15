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
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentAddProductBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_add_product, container, false)
        // init view model
        viewModel = ViewModelProvider(this).get(AddProductViewModel::class.java)
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
        // get All editText for later use
        val nameEdit = binding.prdComponent.prdNameText
        val sizeEdit = binding.prdComponent.prdSizeText
        val companyEdit = binding.prdComponent.prdCompanyText
        val descriptionEdit = binding.prdComponent.prdDescriptionText
        // button cancel
        binding.cancelButton.setOnClickListener { view: View ->
            view.findNavController()
                .navigate(AddProductFragmentDirections.actionAddProductFragmentToProductListFragment())
        }
        // button save
        binding.saveButton.setOnClickListener { view ->
            // save product info before navigate back product list
            viewModel.updateProductInfo(
                nameEdit.text.toString(), sizeEdit.text.toString(),
                companyEdit.text.toString(), descriptionEdit.text.toString()
            )
            sharedViewModel.addNewProduct(viewModel.productEntry)
            view.findNavController()
                .navigate(AddProductFragmentDirections.actionAddProductFragmentToProductListFragment())
        }

        // enable option menu
        setHasOptionsMenu(true)
        return binding.root
    }

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

    private val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) {
        viewModel.uploadImage(it)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.logout_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        findNavController().navigate(AddProductFragmentDirections.actionAddProductFragmentToLoginFragment())
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