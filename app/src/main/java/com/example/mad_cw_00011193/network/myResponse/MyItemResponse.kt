package com.example.mad_cw_00011193.network.myResponse

import com.google.gson.annotations.SerializedName

class MyItemResponse<T> (@SerializedName("data")
                         val data: T?) : MyResponse()