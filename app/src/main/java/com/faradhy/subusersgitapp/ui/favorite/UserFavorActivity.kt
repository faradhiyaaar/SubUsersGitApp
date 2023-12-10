package com.faradhy.subusersgitapp.ui.favorite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.faradhy.subusersgitapp.databinding.ActivityFavUserBinding

@Suppress("DEPRECATION")
class UserFavorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavUserBinding
    private lateinit var adapter: UserFavorAdapter
    private val viewModel: UserFavorViewModel by viewModels {
        UserFavorViewModelFactory.getInstance(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFavUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = UserFavorAdapter()
        adapter.notifyDataSetChanged()

        binding.apply {
            rvUser.setHasFixedSize(true)
            rvUser.layoutManager=LinearLayoutManager(this@UserFavorActivity)
            rvUser.adapter=adapter
        }

        viewModel.getAllFavorUser().observe(this) { favUsers ->
            adapter.submitList(favUsers)
        }

        supportActionBar?.title = "Favorite User"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}