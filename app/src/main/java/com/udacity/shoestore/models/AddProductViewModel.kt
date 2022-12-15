package com.udacity.shoestore.models

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AddProductViewModel : ViewModel() {
    val productEntry: Shoe
        get() = Shoe(_productName, _productSize, _productMaker, _description, _imgList)
    private val _newImages = MutableLiveData<ImageResource>()
    val newImage: LiveData<ImageResource>
        get() = _newImages
    private var _productName: String = ""
    private var _productSize: Double = 0.0
    private var _productMaker: String = ""
    private var _description: String = ""
    private val _imgList = mutableListOf<ImageResource>()

    fun updateProductInfo(name: String, size: String, company: String, description: String) {
        _productName = name
        _productSize = try {
            size.toDouble()
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            0.0
        }
        _productMaker = company
        _description = description
    }

    // add image from local device
    fun uploadImage(uri: Uri) {
        _newImages.value = ImageResource(uri = uri)
        _imgList.add(ImageResource(uri = uri))
    }
}