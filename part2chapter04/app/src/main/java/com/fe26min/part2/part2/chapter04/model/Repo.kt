package com.fe26min.part2.part2.chapter04.model

import com.google.gson.annotations.SerializedName

data class Repo (
    @SerializedName("id")
    val id : Long,

    @SerializedName("name")
    val name : String,

    @SerializedName("description")
    val language: String?,

    @SerializedName("stargazers_count")
    val startCount: Int,

    @SerializedName("forks_count")
    val forkCount: Int,

    @SerializedName("html_url")
    val htmlUrl : String,

)