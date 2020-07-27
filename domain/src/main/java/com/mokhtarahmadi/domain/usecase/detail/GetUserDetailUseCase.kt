package com.mokhtarahmadi.domain.usecase.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.mokhtarahmadi.domain.repositories.UserRepository
import com.mokhtarahmadi.domain.utils.Resource
import com.mokhtarahmadi.model.User

/**
 * Use case that gets a [Resource] [User] from [UserRepository]
 * and makes some specific logic actions on it.
 *
 * In this Use Case, I'm just doing nothing... ¯\_(ツ)_/¯
 */
class GetUserDetailUseCase(private val repository: UserRepository) {

    suspend operator fun invoke(login: String): LiveData<Resource<User>> {
        return Transformations.map(repository.getUserDetailWithCache(login)) {
            it // Place here your specific logic actions (if any)
        }
    }
}