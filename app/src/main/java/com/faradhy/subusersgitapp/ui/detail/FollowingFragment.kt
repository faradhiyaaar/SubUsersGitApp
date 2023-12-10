package com.faradhy.subusersgitapp.ui.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.faradhy.subusersgitapp.R
import com.faradhy.subusersgitapp.databinding.FragmentFollowBinding
import com.faradhy.subusersgitapp.ui.main.AdapterSubUsersGitApp

class FollowingFragment : Fragment(R.layout.fragment_follow) {
    private lateinit var binding: FragmentFollowBinding
    private lateinit var viewModel: FollowViewModel
    private lateinit var adapter: AdapterSubUsersGitApp
    private lateinit var username: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFollowBinding.bind(view)

        val args = arguments
        username = args?.getString(DetailActivity.EXTRA_USERNAME).toString()

        setupRecyclerView()

        viewModel = ViewModelProvider(this).get(FollowViewModel::class.java)
        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            showLoading(isLoading)
        }

        viewModel.getListFollowing().observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
                showLoading(false)
            }
        })

        viewModel.setListFollowing(username)
    }

    private fun setupRecyclerView() {
        adapter = AdapterSubUsersGitApp()
        val layoutManager = LinearLayoutManager(requireContext())
        binding.rvUser.apply {
            setHasFixedSize(true)
            this.layoutManager = layoutManager
            adapter = this@FollowingFragment.adapter
            addItemDecoration(DividerItemDecoration(requireContext(), layoutManager.orientation))
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}