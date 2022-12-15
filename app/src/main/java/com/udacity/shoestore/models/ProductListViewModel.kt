package com.udacity.shoestore.models

import androidx.lifecycle.ViewModel
import com.udacity.shoestore.R

class ProductListViewModel : ViewModel() {
    val listProduct = mutableListOf(
        Shoe(
            "Nike Sport 1",
            26.0,
            "Nike Co.ltd",
            "The best selection for outdoor activities or hi-intensive training",
            mutableListOf(
                ImageResource(resId = R.drawable.shoes1),
                ImageResource(resId = R.drawable.shoes2),
                ImageResource(resId = R.drawable.shoes3),
                ImageResource(resId = R.drawable.shoes4),
                ImageResource(resId = R.drawable.shoes5),
                ImageResource(resId = R.drawable.shoes6),
                ImageResource(resId = R.drawable.shoes7),
                ImageResource(resId = R.drawable.shoes8),
                ImageResource(resId = R.drawable.shoes9)
            )
        ),
        Shoe(
            "Adidas Sport 2",
            26.0,
            "Adidas Co.ltd",
            "The best selection for outdoor activities or hi-intensive training",
            mutableListOf(
                ImageResource(resId = R.drawable.shoes2),
                ImageResource(resId = R.drawable.shoes1),
                ImageResource(resId = R.drawable.shoes3),
                ImageResource(resId = R.drawable.shoes4),
                ImageResource(resId = R.drawable.shoes5),
                ImageResource(resId = R.drawable.shoes6),
                ImageResource(resId = R.drawable.shoes7),
                ImageResource(resId = R.drawable.shoes8),
                ImageResource(resId = R.drawable.shoes9)
            )
        ),
        Shoe(
            "Nike Sport 3",
            26.0,
            "Nike Co.ltd",
            "The best selection for outdoor activities or hi-intensive training",
            mutableListOf(
                ImageResource(resId = R.drawable.shoes3),
                ImageResource(resId = R.drawable.shoes1),
                ImageResource(resId = R.drawable.shoes2),
                ImageResource(resId = R.drawable.shoes4),
                ImageResource(resId = R.drawable.shoes5),
                ImageResource(resId = R.drawable.shoes6),
                ImageResource(resId = R.drawable.shoes7),
                ImageResource(resId = R.drawable.shoes8),
                ImageResource(resId = R.drawable.shoes9)
            )
        ), Shoe(
            "Adidas Sport 4",
            26.0,
            "Adidas Co.ltd",
            "The best selection for outdoor activities or hi-intensive training",
            mutableListOf(
                ImageResource(resId = R.drawable.shoes4),
                ImageResource(resId = R.drawable.shoes3),
                ImageResource(resId = R.drawable.shoes1),
                ImageResource(resId = R.drawable.shoes2),
                ImageResource(resId = R.drawable.shoes5),
                ImageResource(resId = R.drawable.shoes6),
                ImageResource(resId = R.drawable.shoes7),
                ImageResource(resId = R.drawable.shoes8),
                ImageResource(resId = R.drawable.shoes9)
            )
        )
    )

    /**
     * add new product to list
     */
    fun addNewProduct(shoe: Shoe) {
        listProduct.add(shoe)
    }
}