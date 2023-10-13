package com.fe26min.part2.part2.chapter04.network

import com.fe26min.part2.part2.chapter04.model.Repo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {
    @GET("users/{username}/repos")
    fun listRepos(@Path("username") username: String) : Call<List<Repo>>

}