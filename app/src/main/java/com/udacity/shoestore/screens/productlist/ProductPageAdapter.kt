package com.udacity.shoestore.screens.productlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.udacity.shoestore.R

class ProductPageAdapter(private val imgResList: List<Int>) :
    RecyclerView.Adapter<ProductPageAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgView: ImageView = itemView.findViewById(R.id.prd_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.product_item_image, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val curImgRes = imgResList[position]
        holder.imgView.setImageResource(curImgRes)
    }

    override fun getItemCount(): Int {
        return imgResList.size
    }
}