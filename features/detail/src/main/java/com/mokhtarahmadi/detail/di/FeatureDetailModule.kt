package com.mokhtarahmadi.detail.di

import com.mokhtarahmadi.detail.DetailImageViewModel
import com.mokhtarahmadi.detail.DetailViewModel
import com.mokhtarahmadi.domain.usecase.detail.GetUserDetailUseCase
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureDetailModule = module {
    factory { GetUserDetailUseCase(get()) }
    viewModel { (argsLogin: String) -> DetailViewModel(get(), get(), argsLogin) }
    viewModel { (argsImageUrl: String) ->DetailImageViewModel(argsImageUrl) }
}