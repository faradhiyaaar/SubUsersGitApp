package com.faradhy.subusersgitapp.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.faradhy.subusersgitapp.databinding.RowItemUserBinding
import com.faradhy.subusersgitapp.databs.ItemsItem

class AdapterSubUsersGitApp :
    ListAdapter<ItemsItem, AdapterSubUsersGitApp.MyViewHolder>(DIFF_CALLBACK) {

    private var onItemClickCallback: ((ItemsItem) -> Unit)? = null

    fun setOnItemClickCallback(callback: (ItemsItem) -> Unit) {
        this.onItemClickCallback = callback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = RowItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user)
    }

    inner class MyViewHolder(private val binding: RowItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                @Suppress("DEPRECATION")
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val user = getItem(position)
                    onItemClickCallback?.invoke(user)
                }
            }
        }

        fun bind(user: ItemsItem) {
            binding.tvItemName.text = "${user.login}"
            Glide.with(itemView)
                .load(user.avatarUrl)
                .into(binding.imgItemPhoto)
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ItemsItem>() {
            override fun areItemsTheSame(oldItem: ItemsItem, newItem: ItemsItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: ItemsItem, newItem: ItemsItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}
