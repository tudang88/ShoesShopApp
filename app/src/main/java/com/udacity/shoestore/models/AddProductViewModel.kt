package com.udacity.shoestore.models

import android.net.Uri
import android.widget.EditText
import androidx.databinding.InverseMethod
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AddProductViewModel : ViewModel() {
    val productEntry: Shoe
        get() = Shoe(productName, productSize, productMaker, description, _imgList)
    private val _newImages = MutableLiveData<ImageResource>()
    val newImage: LiveData<ImageResource>
        get() = _newImages
    var productName: String = ""
    var productSize: Double = 0.0
    var productMaker: String = ""
    var description: String = ""
    private val _imgList = mutableListOf<ImageResource>()

    // add image from local device
    fun uploadImage(uri: Uri) {
        _newImages.value = ImageResource(uri = uri)
        _imgList.add(ImageResource(uri = uri))
    }
}