package com.mokhtarahmadi.repository.di

import com.mokhtarahmadi.domain.repositories.UserRepository
import com.mokhtarahmadi.domain.utils.AppDispatchers
import com.mokhtarahmadi.repository.UserRepositoryImpl
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val repositoryModule = module {
    factory {
        AppDispatchers(
            Dispatchers.Main,
            Dispatchers.IO
        )
    }
    factory { UserRepositoryImpl(
        get(),
        get()
    ) as UserRepository
    }
}