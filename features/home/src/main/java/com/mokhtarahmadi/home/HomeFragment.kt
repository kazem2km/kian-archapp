package com.mokhtarahmadi.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mokhtarahmadi.common.base.BaseFragment
import com.mokhtarahmadi.common.base.BaseViewModel
import com.mokhtarahmadi.home.databinding.FragmentHomeBinding
import com.mokhtarahmadi.home.views.HomeAdapter
import com.mokhtarahmadi.model.User
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * A simple [BaseFragment] subclass
 * that will show a list of top [User] from Github's API.
 */
class HomeFragment : BaseFragment() {

    // FOR DATA
    private val viewModel: HomeViewModel by viewModel()
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        configureRecyclerView()
    }

    override fun getViewModel(): BaseViewModel = viewModel

    // ---

    private fun configureRecyclerView() {
        binding.fragmentHomeRv.adapter = HomeAdapter(viewModel)
    }
}
