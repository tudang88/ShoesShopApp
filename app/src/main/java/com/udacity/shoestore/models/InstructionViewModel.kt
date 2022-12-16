package com.udacity.shoestore.models

import androidx.lifecycle.ViewModel
import com.udacity.shoestore.R

class InstructionViewModel: ViewModel() {
    val instructionPages = listOf(
    InstructionPageData(R.drawable.shop_img, "Tracking your available items"),
    InstructionPageData(R.drawable.profile_page, "View personal profile"),
    InstructionPageData(R.drawable.add_item, "Add new item")
    )
}