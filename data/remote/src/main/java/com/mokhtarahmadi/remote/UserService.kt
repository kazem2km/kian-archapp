package com.mokhtarahmadi.remote

import com.mokhtarahmadi.model.ApiResult
import com.mokhtarahmadi.model.User
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UserService {

    @GET("search/users")
    suspend fun fetchTopUsersAsync(@Query("q") query: String = "mokhtar",
                           @Query("sort") sort: String = "followers"): ApiResult<User>

    @GET("users/{login}")
    suspend fun fetchUserDetailsAsync(@Path("login") login: String): User
}