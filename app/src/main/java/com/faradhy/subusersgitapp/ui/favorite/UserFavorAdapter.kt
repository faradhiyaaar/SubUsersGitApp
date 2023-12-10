package com.faradhy.subusersgitapp.ui.favorite

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import androidx.recyclerview.widget.ListAdapter
import com.faradhy.subusersgitapp.databinding.RowItemUserBinding
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.faradhy.subusersgitapp.R
import com.faradhy.subusersgitapp.databs.UserFavor
import com.faradhy.subusersgitapp.ui.detail.DetailActivity

class UserFavorAdapter() : ListAdapter<UserFavor, UserFavorAdapter.FavViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavViewHolder {
        val binding = RowItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavViewHolder, position: Int) {
        val favuser = getItem(position)
        holder.bind(favuser)

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intentDetail = Intent(context, DetailActivity::class.java).apply {
                putExtra(DetailActivity.EXTRA_USERNAME, favuser.username)
                putExtra(DetailActivity.EXTRA_URL, favuser.urlAvatar)
            }
            context.startActivity(intentDetail)
        }
    }

    class FavViewHolder(private val binding: RowItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(userFavor: UserFavor) {
            binding.tvItemName.text = userFavor.username
            Glide.with(binding.imgItemPhoto)
                .load(userFavor.urlAvatar)
                .apply(RequestOptions.placeholderOf(R.drawable.img_load))
                .into(binding.imgItemPhoto)
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<UserFavor>() {
            override fun areItemsTheSame(oldItem: UserFavor, newItem: UserFavor): Boolean {
                return oldItem.username == newItem.username
            }
            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: UserFavor, newItem: UserFavor): Boolean {
                return oldItem == newItem
            }
        }
    }
}