package com.mokhtarahmadi.archapp.di

import com.mokhtarahmadi.detail.di.featureDetailModule
import com.mokhtarahmadi.home.di.featureHomeModule
import com.mokhtarahmadi.local.di.localModule
import com.mokhtarahmadi.remote.di.createRemoteModule
import com.mokhtarahmadi.repository.di.repositoryModule

val appComponent= listOf(createRemoteModule("https://api.github.com/"), repositoryModule, featureHomeModule, featureDetailModule, localModule)