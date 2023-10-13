package com.fe26min.part2.chapter03

import com.google.gson.annotations.SerializedName

data class Message (
    @SerializedName("message")
    val message: String
)
