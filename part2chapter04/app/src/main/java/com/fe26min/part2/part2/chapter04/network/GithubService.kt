package com.fe26min.part2.part2.chapter04.network

import com.fe26min.part2.part2.chapter04.model.Repo
import com.fe26min.part2.part2.chapter04.model.UserDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubService {
    @Headers("Authorization: Bearer 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")
    @GET("users/{username}/repos")
    fun listRepos(@Path("username") username: String) : Call<List<Repo>>

    @GET("search/users")
    fun searchUsers(@Query("q") query: String): Call<UserDto>
}