package com.udacity.shoestore.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.udacity.shoestore.R
import com.udacity.shoestore.models.ImageResource

class ImageViewPagerAdapter(private val imgResList: MutableList<ImageResource>) :
    RecyclerView.Adapter<ImageViewPagerAdapter.ViewHolder>() {
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
        if (curImgRes.resId != null) {
            holder.imgView.setImageResource(curImgRes.resId)
        } else if (curImgRes.uri != null) {
            holder.imgView.setImageURI(curImgRes.uri)
        } else {
            holder.imgView.setImageResource(R.drawable.rectangle_box)
        }
    }

    override fun getItemCount(): Int {
        return imgResList.size
    }

    /**
     * add Image to current List
     */
    fun addImage(imgRes: ImageResource) {
        imgResList.add(imgRes)
        notifyDataSetChanged()
    }
}