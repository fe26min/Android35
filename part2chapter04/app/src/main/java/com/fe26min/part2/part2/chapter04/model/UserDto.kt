package com.fe26min.part2.part2.chapter04.model

import com.google.gson.annotations.SerializedName

data class UserDto (
    @SerializedName("total_count")
    val totalCount: Int,
    @SerializedName("items")
    val items: List<User>
)