package com.mokhtarahmadi.home.views

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.mokhtarahmadi.home.HomeViewModel
import com.mokhtarahmadi.home.databinding.ItemHomeBinding
import com.mokhtarahmadi.model.User

class HomeViewHolder(parent: View): RecyclerView.ViewHolder(parent) {

    private val binding = ItemHomeBinding.bind(parent)

    fun bindTo(user: User, viewModel: HomeViewModel) {
        binding.user = user
        binding.viewmodel = viewModel
    }
}