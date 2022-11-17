package com.example.databinding.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.databinding.R
import com.example.databinding.databinding.ItemLayoutBinding
import com.example.databinding.model.Owner

class DataBindingAdapter : ListAdapter<Owner, DataBindingAdapter.DataViewHolder>(DiffCallBack()) {
    private class DiffCallBack : DiffUtil.ItemCallback<Owner>() {
        override fun areItemsTheSame(oldItem: Owner, newItem: Owner): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Owner, newItem: Owner): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        return DataViewHolder(
            ItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class DataViewHolder(private val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(owner: Owner) {
            binding.owner = owner
            binding.executePendingBindings()
        }
    }

    companion object {
        @JvmStatic
        @BindingAdapter("loadImage")
        fun loadImage(imageView: AppCompatImageView, url: String) {
            Glide.with(imageView)
                .load(url)
                .circleCrop()
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(imageView)
        }
    }
}