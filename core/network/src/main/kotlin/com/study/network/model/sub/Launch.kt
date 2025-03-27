package com.study.network.model.sub

import com.google.gson.annotations.SerializedName

data class Launch(
    @SerializedName("launch_id")
    val launchId: String,
    val provider: String
)
