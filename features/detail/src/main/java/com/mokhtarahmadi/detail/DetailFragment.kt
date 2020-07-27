package com.mokhtarahmadi.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.navArgs
import com.mokhtarahmadi.common.base.BaseFragment
import com.mokhtarahmadi.common.base.BaseViewModel
import com.mokhtarahmadi.detail.databinding.FragmentDetailBinding
import com.mokhtarahmadi.model.User
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

/**
 * A simple [BaseFragment] subclass
 * that will show the [User] details.
 */
class DetailFragment : BaseFragment() {

    // FOR DATA
    private val viewModel: DetailViewModel by viewModel{ parametersOf(args.login)}
    private lateinit var binding: FragmentDetailBinding

    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun getViewModel(): BaseViewModel = viewModel

    override fun getExtras(): FragmentNavigator.Extras =
        FragmentNavigatorExtras(binding.fragmentDetailAvatar to getString(R.string.transition_avatar_dest))
}
