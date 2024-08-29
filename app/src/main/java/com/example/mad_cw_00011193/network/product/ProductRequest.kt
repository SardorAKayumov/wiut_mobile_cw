package com.example.mad_cw_00011193.network.product

import com.google.gson.annotations.SerializedName

data class ProductRequest(
    @SerializedName("title")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("price")
    val price: String
)
