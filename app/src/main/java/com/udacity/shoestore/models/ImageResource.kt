package com.udacity.shoestore.models

import android.net.Uri
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ImageResource(val resId: Int? = null, val uri: Uri? = null): Parcelable
