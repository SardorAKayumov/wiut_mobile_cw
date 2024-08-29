package com.example.mad_cw_00011193.addNew

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mad_cw_00011193.network.RetrofitInstance
import com.example.mad_cw_00011193.network.myResponse.MyResponse
import com.example.mad_cw_00011193.network.product.ProductRequest
import com.example.mad_cw_00011193.utils.Constants
import kotlinx.coroutines.launch

class AddNewViewModel : ViewModel() {

    val productInsertResponse: MutableLiveData<MyResponse> by lazy {
        MutableLiveData<MyResponse>()
    }

    fun saveNewProductToRemoteDb(product: ProductRequest) {

        viewModelScope.launch {
            try {

                val response: MyResponse = RetrofitInstance.productService.insertNewProduct(
                    Constants.STUDENT_ID,
                    product
                )

                productInsertResponse.value = response

                Log.d("Update_response", response.toString())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }
}