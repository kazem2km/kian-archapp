package com.mokhtarahmadi.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.mokhtarahmadi.domain.repositories.UserRepository
import com.mokhtarahmadi.local.dao.UserDao
import com.mokhtarahmadi.model.User
import com.mokhtarahmadi.remote.UserDatasource
import com.mokhtarahmadi.domain.utils.Resource
import java.io.IOException

class UserRepositoryImpl(
    private val dataSource: UserDatasource,
    private val userDao: UserDao
) :
    UserRepository {

    /**
     * Suspended function that will get a list of top [User]
     * whether in cache (SQLite) or via network (API).
     */

    override suspend fun getTopUsersWithCache(): LiveData<Resource<List<User>>> =
        liveData {
            val disposable = emitSource(
                userDao.getTopUsers().map {
                    Resource.loading(it)
                }
            )
            try {
                val user = dataSource.fetchTopUsersAsync()
                // Stop the previous emission to avoid dispatching the updated user
                // as `loading`.
                disposable.dispose()
                // Update the database.
                userDao.save(user.items)
                // Re-establish the emission with success type.
                emitSource(
                    userDao.getTopUsers().map {
                        Resource.success(it)
                    }
                )
            } catch (exception: IOException) {
                // Any call to `emit` disposes the previous one automatically so we don't
                // need to dispose it here as we didn't get an updated value.
                emitSource(
                    userDao.getTopUsers().map {
                        Resource.error(exception, it)
                    }
                )
            }
        }


    /**
     * Suspended function that will get details of a [User]
     * whether in cache (SQLite) or via network (API).
     */

    override suspend fun getUserDetailWithCache(login: String): LiveData<Resource<User>> =
        liveData {
            val disposable = emitSource(
                userDao.getUser(login).map {
                    Resource.loading(it)
                }
            )

            try {
                val user = dataSource.fetchUserDetailsAsync(login)
                // Stop the previous emission to avoid dispatching the updated user
                // as `loading`.
                disposable.dispose()
                // Update the database.
                userDao.save(user)
                // Re-establish the emission with success type.
                emitSource(
                    userDao.getUser(login).map {
                        Resource.success(it)
                    }
                )
            } catch (exception: IOException) {
                // Any call to `emit` disposes the previous one automatically so we don't
                // need to dispose it here as we didn't get an updated value.
                emitSource(
                    userDao.getUser(login).map {
                        Resource.error(exception, it)
                    }
                )
            }
        }
}