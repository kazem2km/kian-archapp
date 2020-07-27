package com.mokhtarahmadi.home.di

import com.mokhtarahmadi.home.HomeViewModel
import com.mokhtarahmadi.domain.usecase.home.GetTopUsersUseCase
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureHomeModule = module {
    factory { GetTopUsersUseCase(get()) }
    viewModel { HomeViewModel(get(), get()) }
}