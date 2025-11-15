package com.aqtanb.sis4.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aqtanb.sis4.data.ApodItem
import com.aqtanb.sis4.databinding.ItemMarsPhotoBinding
import com.bumptech.glide.Glide

class ApodAdapter(private val items: List<ApodItem>) :
    RecyclerView.Adapter<ApodAdapter.ApodViewHolder>() {

    class ApodViewHolder(private val binding: ItemMarsPhotoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ApodItem) {
            binding.cameraTextView.text = item.title
            binding.dateTextView.text = "Date: ${item.date}"
            binding.solTextView.text = if (item.copyright != null) {
                "© ${item.copyright}"
            } else {
                "NASA"
            }
            binding.roverTextView.text = "Type: ${item.mediaType}"
            binding.explanationTextView.text = item.explanation

            val imageUrl = item.hdUrl ?: item.url
            Glide.with(binding.imageView.context)
                .load(imageUrl)
                .centerCrop()
                .into(binding.imageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApodViewHolder {
        val binding = ItemMarsPhotoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ApodViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ApodViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}
