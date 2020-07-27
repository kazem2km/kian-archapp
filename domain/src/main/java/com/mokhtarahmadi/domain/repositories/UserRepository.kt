package com.mokhtarahmadi.domain.repositories

import androidx.lifecycle.LiveData
import com.mokhtarahmadi.model.User
import com.mokhtarahmadi.domain.utils.Resource

interface UserRepository {
    suspend fun getTopUsersWithCache(): LiveData<Resource<List<User>>>
    suspend fun getUserDetailWithCache(login: String): LiveData<Resource<User>>
}