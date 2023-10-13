package com.fe26min.part2.part2.chapter04

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.fe26min.part2.part2.chapter04.model.Repo
import com.fe26min.part2.part2.chapter04.model.UserDto
import com.fe26min.part2.part2.chapter04.network.GithubService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding : MainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val githubService = retrofit.create(GithubService::class.java)
        githubService.listRepos("square").enqueue(object: Callback<List<Repo>> {
            override fun onResponse(call: Call<List<Repo>>, response: Response<List<Repo>>) {
                Log.e("MainActivity","List Repo : ${response.body().toString()}")
            }

            override fun onFailure(call: Call<List<Repo>>, t: Throwable) {

            }

        })

        githubService.searchUsers("square").enqueue(object: Callback<UserDto> {
            override fun onResponse(call: Call<UserDto>, response: Response<UserDto>) {
                Log.e("MainActivity", "Search User : ${response.body().toString()}")
            }

            override fun onFailure(call: Call<UserDto>, t: Throwable) {

            }

        })

    }
}