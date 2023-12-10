package com.faradhy.subusersgitapp.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.faradhy.subusersgitapp.R
import com.faradhy.subusersgitapp.databinding.ActivityMainBinding
import com.faradhy.subusersgitapp.databs.ItemsItem
import com.faradhy.subusersgitapp.ui.detail.DetailActivity
import com.faradhy.subusersgitapp.ui.favorite.UserFavorActivity
import com.faradhy.subusersgitapp.ui.settings.dataStore
import com.faradhy.subusersgitapp.ui.settings.ThemeModeSetActivity
import com.faradhy.subusersgitapp.ui.settings.ThemeModeSetViewModel
import com.faradhy.subusersgitapp.ui.settings.ThemeModeSetPreference
import com.faradhy.subusersgitapp.ui.settings.ThemeModeSetViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: AdapterSubUsersGitApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = AdapterSubUsersGitApp()
        adapter.setOnItemClickCallback { user ->
            val intent = Intent(this@MainActivity, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_USERNAME, user.login)
            startActivity(intent)
        }


        val mainViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            MainViewModel::class.java)

        binding.rvUser.adapter = adapter // Set adapter to RecyclerView

        mainViewModel.listUser.observe(this) { listUser ->
            setUserData(listUser)
        }
        mainViewModel.isLoading.observe(this) {
            showLoading(it)
        }

        with(binding) {
            searchView.setupWithSearchBar(searchBar)
            searchView
                .editText
                .setOnEditorActionListener { _, actionId, _ ->
                    searchView.hide()
                    if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                        val query = searchView.text.toString().trim()
                        if (query.isNotEmpty()) {
                            mainViewModel.findUser(query)
                            searchBar.text = searchView.text
                        } else {
                            Toast.makeText(this@MainActivity, "Tolong Masukan Username", Toast.LENGTH_SHORT).show()
                        }
                        return@setOnEditorActionListener true
                    }

                    false
                }
        }
        val layoutManager = LinearLayoutManager(this)
        binding.rvUser.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvUser.addItemDecoration(itemDecoration)

        val preferences = ThemeModeSetPreference.getInstance(application.dataStore)
        val modeViewModel = ViewModelProvider(this, ThemeModeSetViewModelFactory(preferences)).get(
            ThemeModeSetViewModel::class.java
        )
        modeViewModel.getThemeMode().observe(this){isDarkModeActive:Boolean->
            if (isDarkModeActive){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }

        }

    }

    private fun setUserData(userData: List<ItemsItem?>?) {
        if (userData != null) {
            adapter.submitList(userData)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.fav_button -> {
                Intent(this, UserFavorActivity::class.java).also {
                    startActivity(it)
                }
                return true
            }

            R.id.darkmode -> {
                Intent(this, ThemeModeSetActivity::class.java).also {
                    startActivity(it)
                }
                return true
            }

            else -> return super.onOptionsItemSelected(item)
        }
    }

}
