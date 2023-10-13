package com.fe26min.part2.part2.chapter04.model

import com.google.gson.annotations.SerializedName

data class User (
    @SerializedName("id")
    val id: Int,

    @SerializedName("login")
    val username: String,
)