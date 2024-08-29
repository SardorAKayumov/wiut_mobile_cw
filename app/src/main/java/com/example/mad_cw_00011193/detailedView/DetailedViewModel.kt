package com.example.mad_cw_00011193.detailedView


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mad_cw_00011193.models.Product
import com.example.mad_cw_00011193.network.RetrofitInstance
import com.example.mad_cw_00011193.network.myResponse.MyItemResponse
import com.example.mad_cw_00011193.network.product.ProductResponse
import com.example.mad_cw_00011193.utils.Constants
import kotlinx.coroutines.launch

class DetailedViewModel(productId: String) : ViewModel() {

    val productLiveData: MutableLiveData<Product> by lazy {
        MutableLiveData<Product>()
    }

    init {
        getProductByIdFromRemoteDb(productId)
        //deleteOneProductById(productId)
    }

    private fun getProductByIdFromRemoteDb(productId: String) {
        viewModelScope.launch {
            try {
                val response: MyItemResponse<ProductResponse> =
                    RetrofitInstance.productService.getOneProductById(productId, Constants.STUDENT_ID)
                val productFromResponse = response.data

                if (productFromResponse != null) {
                    productLiveData.value = Product(
                        productFromResponse.id,
                        productFromResponse.name,
                        productFromResponse.description,
                        productFromResponse.price
                    )
                }


            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

//    fun editProductById(productId: String, productRequest: ProductRequest) {
//        viewModelScope.launch {
//            try {
//
//                val response: MyResponse = RetrofitInstance.productService.updateOneProductById(
//                    productId,
//                    Constants.STUDENT_ID,
//                    productRequest
//                )
//
//                Log.d("Update_response", response.toString())
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//        }
//    }


//    fun deleteOneProductById(productId: String) {
//        viewModelScope.launch {
//            try {
//
//                val response: MyResponse = RetrofitInstance.productService.deleteOneProductById(
//                    productId,
//                    Constants.STUDENT_ID
//                )
//
//                Log.d("Delete_response", response.message)
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//        }
//    }
}