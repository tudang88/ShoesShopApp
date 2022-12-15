package com.udacity.shoestore.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.udacity.shoestore.R
import com.udacity.shoestore.models.InstructionPageData

/**
 * Create a custom RecyclerViewAdapter for Instruction page
 */
class InstructionPageAdapter(private val items: List<InstructionPageData>) :
    RecyclerView.Adapter<InstructionPageAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.page_image)
        val textView: TextView = itemView.findViewById(R.id.page_label)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.page_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val curImage = items[position].imageResId
        val curLabel = items[position].msg
        holder.imageView.setImageResource(curImage)
        holder.textView.text = curLabel
    }

    override fun getItemCount(): Int {
        return items.size
    }
}