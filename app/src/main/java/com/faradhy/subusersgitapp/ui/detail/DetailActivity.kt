package com.faradhy.subusersgitapp.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.faradhy.subusersgitapp.R
import com.faradhy.subusersgitapp.databinding.ActivityDetailBinding
import com.faradhy.subusersgitapp.databs.UserFavor
import com.faradhy.subusersgitapp.ui.favorite.UserFavorViewModel
import com.faradhy.subusersgitapp.ui.favorite.UserFavorViewModelFactory

@Suppress("DEPRECATION")
class DetailActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_USERNAME="extra_username"
        const val EXTRA_URL = "extra_url"

    }

    private lateinit var binding: ActivityDetailBinding
    private val userFavorViewModel by viewModels<UserFavorViewModel> {
        UserFavorViewModelFactory.getInstance(application)
    }
    private var userFavor = UserFavor()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title="Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val username = intent.getStringExtra(EXTRA_USERNAME)
        val bundle = Bundle().apply { putString(EXTRA_USERNAME, username) }

        val viewModel: DetailViewModel by viewModels {
            ViewModelProvider.NewInstanceFactory()
        }

        viewModel.isLoading.observe(this) { isLoading ->
            showLoading(isLoading)
        }

        if (username != null) {
            viewModel.setUserDetail(username)
            viewModel.getUserDetail().observe(this) {
                if (it != null) {
                    binding.apply {
                        tvName.text = it.name
                        tvUsername.text = "${it.login}"
                        tvFollower.text = "${it.followers} Followers"
                        tvFollowing.text = "${it.following} Following"
                        Glide.with(this@DetailActivity)
                            .load(it.avatarUrl)
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .centerCrop()
                            .into(ivPhoto)

                        userFavor.username=it.login!!
                        userFavor.urlAvatar=it.avatarUrl

                    }
                }
            }
            userFavorViewModel.getFavorUserByUsername(username).observe(this){
                if (it!=null){
                    binding.fabFav.setImageResource(R.drawable.img_fav_full)
                    binding.fabFav.setOnClickListener{
                        userFavorViewModel.deleteUserFavor(userFavor)
                    }
                }else{
                    binding.fabFav.setImageResource(R.drawable.img_fav)
                    binding.fabFav.setOnClickListener{
                        userFavorViewModel.insertUserFavor(userFavor)
                    }
                }
            }

            val pagerAdapter = PagerAdapter(this,supportFragmentManager,bundle)
            binding.apply {
                viewPager.adapter = pagerAdapter
                tabs.setupWithViewPager(viewPager)
            }

        }

    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
