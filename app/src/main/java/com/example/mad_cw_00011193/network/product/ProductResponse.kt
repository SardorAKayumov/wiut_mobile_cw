package com.example.mad_cw_00011193.network.product

import com.google.gson.annotations.SerializedName

data class ProductResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("price")
    val price: String
)
