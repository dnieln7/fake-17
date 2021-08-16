package com.dnieln7.fake17.domain

import com.google.gson.annotations.SerializedName

data class CatWeight(
    @SerializedName("imperial")
    val imperial: String,
    @SerializedName("metric")
    val metric: String?
)