package com.study.network.model

import com.google.gson.annotations.SerializedName

data class Launch(
    @SerializedName("launch_id")
    val launchId: String,
    val provider: String
)
